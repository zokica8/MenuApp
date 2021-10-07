package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerDataDto {

    @SerializedName("token")
    @Expose
    private AccessTokenDto accessToken;

    public AccessTokenDto getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessTokenDto accessToken) {
        this.accessToken = accessToken;
    }
}
