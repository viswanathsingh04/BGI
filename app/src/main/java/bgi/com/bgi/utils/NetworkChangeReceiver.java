package bgi.com.bgi.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static bgi.com.bgi.Login.dialog;

/**
 * Created by VPS on 12-12-2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {
                dialog(true);
                Log.e("IsOnline", "Online Connect Intenet ");
            } else {
                dialog(false);
                Log.e("No Internet", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
