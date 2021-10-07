package app.menu.menutechnologiestestapp.domain.repository;

import java.util.Map;

import app.menu.menutechnologiestestapp.data.dto.LoginResponseDto;
import app.menu.menutechnologiestestapp.data.dto.ResponseDataDto;
import retrofit2.Call;

public interface Repository {

    // Endpoint to login the user to the application
    Call<LoginResponseDto> login(Map<String, Object> params);

    // Endpoint to search all venues via search request
    Call<ResponseDataDto> getVenues(Map<String, Object> params);
}
