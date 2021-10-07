package app.menu.menutechnologiestestapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public final class AuthUtils {

    // keys for shared preferences
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_TOKEN_VALID_TO = "access_token_valid_to";
    private static final String REFRESH_TOKEN_VALID_TO = "refresh_token_valid_to";

    // context to use to get the shared preferences to store authentication data
    private final Context context;

    @Inject
    public AuthUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    // methods to save and retrieve authentication data from the server and to get the shared preferences storage
    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setAccessToken(String accessToken) {
        SharedPreferences sp = getSharedPreferences();
        sp.edit().putString(ACCESS_TOKEN, accessToken).apply();
    }

    public String getAccessToken() {
        SharedPreferences sp = getSharedPreferences();
        return sp.getString(ACCESS_TOKEN, "");
    }

    public void setAccessTokenValidTo(long accessTokenValidTo) {
        SharedPreferences sp = getSharedPreferences();
        sp.edit().putLong(ACCESS_TOKEN_VALID_TO, accessTokenValidTo).apply();
    }

    public long getAccessTokenValidTo() {
        SharedPreferences sp = getSharedPreferences();
        return sp.getLong(ACCESS_TOKEN_VALID_TO, -1);
    }

    public void setRefreshTokenValidTo(long refreshTokenValidTo) {
        SharedPreferences sp = getSharedPreferences();
        sp.edit().putLong(REFRESH_TOKEN_VALID_TO, refreshTokenValidTo).apply();
    }

    public long getRefreshTokenValidTo() {
        SharedPreferences sp = getSharedPreferences();
        return sp.getLong(REFRESH_TOKEN_VALID_TO, -1);
    }
}
