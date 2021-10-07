package app.menu.menutechnologiestestapp.data.network;

import java.util.Map;

import app.menu.menutechnologiestestapp.data.dto.LoginResponseDto;
import app.menu.menutechnologiestestapp.data.dto.ResponseDataDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("customers/login")
    Call<LoginResponseDto> login(@Body Map<String, Object> params);

    @POST("directory/search")
    Call<ResponseDataDto> getVenues(@Body Map<String, Object> params);
}
