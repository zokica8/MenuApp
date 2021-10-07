package app.menu.menutechnologiestestapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import androidx.core.content.ContextCompat;

import app.menu.menutechnologiestestapp.R;

public final class ViewUtils {

    private ViewUtils() {}

    public static void showProgressDialog(ProgressDialog progressDialog) {
        String message = progressDialog.getContext().getString(R.string.please_wait);
        SpannableString message1 = new SpannableString(message);
        message1.setSpan(new ForegroundColorSpan(progressDialog.getContext().getResources().getColor(R.color.black)), 0, message.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        message1.setSpan(new RelativeSizeSpan(1.2f), 0, message.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        progressDialog.setMessage(message1);
        progressDialog.setProgressStyle(ContextCompat.getColor(progressDialog.getContext(), R.color.progress_bar_color));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void hideProgressDialog(ProgressDialog progressDialog) {
        if(progressDialog != null && progressDialog.isShowing()) {
            dismissDialog(progressDialog);
        }
    }

    private static void dismissDialog(ProgressDialog progressDialog) {
        Context context = ((ContextWrapper) progressDialog.getContext()).getBaseContext();
        if (context instanceof Activity) {
            if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                progressDialog.dismiss();
            }
        } else {
            progressDialog.dismiss();
        }
    }
}
