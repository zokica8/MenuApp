package app.menu.menutechnologiestestapp.domain.model;

public class AccessToken {

    private String token;
    private long accessTokenValidTo;
    private long refreshTokenValidTo;

    public AccessToken() {
    }

    public AccessToken(String token, long accessTokenValidTo, long refreshTokenValidTo) {
        this.token = token;
        this.accessTokenValidTo = accessTokenValidTo;
        this.refreshTokenValidTo = refreshTokenValidTo;
    }

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
