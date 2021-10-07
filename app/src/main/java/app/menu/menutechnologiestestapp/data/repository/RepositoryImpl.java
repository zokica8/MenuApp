package app.menu.menutechnologiestestapp.data.repository;

import java.util.Map;

import javax.inject.Inject;

import app.menu.menutechnologiestestapp.data.dto.LoginResponseDto;
import app.menu.menutechnologiestestapp.data.dto.ResponseDataDto;
import app.menu.menutechnologiestestapp.data.network.ApiService;
import app.menu.menutechnologiestestapp.domain.repository.Repository;
import retrofit2.Call;

public class RepositoryImpl implements Repository {

    private final ApiService apiService;

    @Inject
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Call<LoginResponseDto> login(Map<String, Object> params) {
        return apiService.login(params);
    }

    @Override
    public Call<ResponseDataDto> getVenues(Map<String, Object> params) {
        return apiService.getVenues(params);
    }


}
