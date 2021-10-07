package app.menu.menutechnologiestestapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import javax.inject.Inject;

public final class NetworkUtils {

    private final Context context;

    @Inject
    public NetworkUtils(Context context) {
        this.context = context;
    }

    public boolean hasInternetAccess(boolean ignoreWiFiOnly) {
        boolean hasInternet = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            hasInternet = networkInfo.isConnectedOrConnecting();
            if(hasInternet && !ignoreWiFiOnly) {
                // check is WIFI
                hasInternet = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            }
        }
        return hasInternet;
    }

    public boolean hasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        else {
            return hasInternetAccess(true);
        }
    }
}
