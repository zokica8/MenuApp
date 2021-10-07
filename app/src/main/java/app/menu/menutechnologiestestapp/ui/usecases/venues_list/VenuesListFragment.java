package app.menu.menutechnologiestestapp.ui.usecases.venues_list;

import static app.menu.menutechnologiestestapp.utils.ErrorUtils.getStringResourceByName;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import app.menu.menutechnologiestestapp.R;
import app.menu.menutechnologiestestapp.data.ApiResponseObject;
import app.menu.menutechnologiestestapp.databinding.FragmentVenuesListBinding;
import app.menu.menutechnologiestestapp.utils.ConnectionLiveData;
import app.menu.menutechnologiestestapp.utils.NetworkUtils;
import app.menu.menutechnologiestestapp.utils.ViewUtils;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VenuesListFragment extends Fragment {

    // attributes
    private ProgressDialog progressDialog;
    private NavController navController;
    private FragmentVenuesListBinding binding;
    private VenuesListViewModel viewModel;

    @Inject
    Context context;
    @Inject
    ConnectionLiveData connectionLiveData;
    @Inject
    NetworkUtils networkUtils;

    public VenuesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_venues_list, container, false);
        viewModel = new ViewModelProvider(this).get(VenuesListViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        navController = NavHostFragment.findNavController(this);

        checkNetworkConnectionLiveData();

        viewModel.getApiResponseObjectLiveData().observe(getViewLifecycleOwner(), this::consumeResponse);
        viewModel.getVenueSelectedLiveData().observe(getViewLifecycleOwner(), venue -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("venue", venue);
            navController.navigate(R.id.action_venuesListFragment_to_venueDetailsFragment, bundle);
            connectionLiveData.removeObservers(getViewLifecycleOwner());
        });
        return binding.getRoot();
    }

    private void consumeResponse(ApiResponseObject apiResponseObject) {
        switch (apiResponseObject.getStatus()) {
            case LOADING:
                progressDialog = new ProgressDialog(context);
                ViewUtils.showProgressDialog(progressDialog);
                break;
            case SUCCESS:
                ViewUtils.hideProgressDialog(progressDialog);
                break;
            case ERROR:
                ViewUtils.hideProgressDialog(progressDialog);
                Toast.makeText(context, getStringResourceByName(context, apiResponseObject.getError()), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void checkNetworkConnectionLiveData() {
        if(!networkUtils.hasNetwork()) {
            binding.venuesRV.setVisibility(View.GONE);
            binding.noInternetConnectionTV.setVisibility(View.VISIBLE);
        }
        Observer<Boolean> observer = networkAvailable -> {
            if (networkAvailable) {
                binding.venuesRV.setVisibility(View.VISIBLE);
                binding.noInternetConnectionTV.setVisibility(View.GONE);
//                viewModel.getVenues();
            } else {
                binding.venuesRV.setVisibility(View.GONE);
                binding.noInternetConnectionTV.setVisibility(View.VISIBLE);
            }
        };
        connectionLiveData.observe(getViewLifecycleOwner(), observer);
    }
}