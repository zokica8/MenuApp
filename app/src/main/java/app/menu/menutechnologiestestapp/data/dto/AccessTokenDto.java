package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessTokenDto {

    @SerializedName("value")
    @Expose
    private String token;

    @SerializedName("ttl")
    @Expose
    private long accessTokenValidTo;

    @SerializedName("refresh_ttl")
    @Expose
    private long refreshTokenValidTo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getAccessTokenValidTo() {
        return accessTokenValidTo;
    }

    public void setAccessTokenValidTo(long accessTokenValidTo) {
        this.accessTokenValidTo = accessTokenValidTo;
    }

    public long getRefreshTokenValidTo() {
        return refreshTokenValidTo;
    }

    public void setRefreshTokenValidTo(long refreshTokenValidTo) {
        this.refreshTokenValidTo = refreshTokenValidTo;
    }
}
