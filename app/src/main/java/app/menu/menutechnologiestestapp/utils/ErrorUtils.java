package app.menu.menutechnologiestestapp.utils;

import android.content.Context;

import app.menu.menutechnologiestestapp.R;

public final class ErrorUtils {

    private ErrorUtils() {}

    public static String getStringResourceByName(Context context, String resourceName) {
        String packageName = context.getPackageName();
        int resourceId = context.getResources().getIdentifier(resourceName, "string", packageName);
        if(resourceId != 0) {
            return context.getString(resourceId);
        }
        else {
            return context.getString(R.string.unexpected_error);
        }
    }
}
