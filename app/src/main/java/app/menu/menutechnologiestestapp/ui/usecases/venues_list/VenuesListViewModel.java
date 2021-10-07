package app.menu.menutechnologiestestapp.ui.usecases.venues_list;

import static app.menu.menutechnologiestestapp.data.ErrorObject.getErrorMessage;
import static app.menu.menutechnologiestestapp.utils.ErrorConstants.INTERNET_CONNECTION;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import app.menu.menutechnologiestestapp.data.ApiResponseObject;
import app.menu.menutechnologiestestapp.data.dto.ResponseDataDto;
import app.menu.menutechnologiestestapp.data.dto.VenueDto;
import app.menu.menutechnologiestestapp.domain.mapper.VenueMapper;
import app.menu.menutechnologiestestapp.domain.model.Venue;
import app.menu.menutechnologiestestapp.domain.repository.Repository;
import app.menu.menutechnologiestestapp.utils.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class VenuesListViewModel extends ViewModel {

    private final Repository repository;

    // observable to observe the list of venues pulled from the server
    public MutableLiveData<List<Venue>> venuesLiveData = new SingleLiveEvent<>();

    // observable to follow possible responses from user interaction (from form validation)
    private final MutableLiveData<ApiResponseObject> apiResponseObjectLiveData = new SingleLiveEvent<>();

    // observable to pick up the selected venue and send that venue to the following screen (venue details screen)
    private static final MutableLiveData<Venue> venueSelectedLiveData = new SingleLiveEvent<>();

    @Inject
    public VenuesListViewModel(Repository repository) {
        this.repository = repository;
        getVenues();
    }

    public LiveData<ApiResponseObject> getApiResponseObjectLiveData() {
        return apiResponseObjectLiveData;
    }

    public LiveData<List<Venue>> getVenuesLiveData() {
        return venuesLiveData;
    }

    public LiveData<Venue> getVenueSelectedLiveData() {
        return venueSelectedLiveData;
    }

    public void getVenues() {
        makeNetworkCall();
    }

    private void makeNetworkCall() {
        Map<String, Object> params = new HashMap<>();
        params.put("latitude", "44.001783");
        params.put("longitude", "21.26907");
        apiResponseObjectLiveData.setValue(ApiResponseObject.loading());
        repository.getVenues(params).enqueue(new Callback<ResponseDataDto>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDataDto> call, @NonNull Response<ResponseDataDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setVenues(response.body().getVenuesDto().getVenues());
                    apiResponseObjectLiveData.setValue(ApiResponseObject.success(null));
                } else {
                    apiResponseObjectLiveData.setValue(ApiResponseObject.error(getErrorMessage(response)));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDataDto> call, @NonNull Throwable t) {
                apiResponseObjectLiveData.setValue(ApiResponseObject.error(INTERNET_CONNECTION));
                Log.e(VenuesListViewModel.class.getSimpleName(), t.getLocalizedMessage());
            }
        });
    }

    private void setVenues(List<VenueDto> venueDtos) {
        List<Venue> venues = new ArrayList<>();
        for (VenueDto venueDto : venueDtos) {
            if (venueDto.getVenueObjectDto() != null) {
                Venue venue = new VenueMapper().convertDtoToModel(venueDto.getVenueObjectDto());
                venues.add(venue);
            }
        }
        venuesLiveData.setValue(venues);
    }

    @BindingAdapter("setList")
    public static void setList(RecyclerView recyclerView, List<Venue> venues) {
        VenuesListAdapter adapter = new VenuesListAdapter(venues);
        adapter.setOnVenueClickListener(venueSelectedLiveData::setValue);
        recyclerView.setAdapter(adapter);
    }
}
