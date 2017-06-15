package access.network.com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Farina Ali
 */

public class AppUtils {

    public static boolean isConnectivityAvailable(final Context ctx) {
        boolean isNetworkConnected = false;

        ConnectivityManager manager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null) {

            Log.d("isConnectivityAvailable", info.getState().toString());
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                isNetworkConnected = true;
            }
        }
        return isNetworkConnected;
    }

    public static void setDisconnection(Context context, boolean disconnect) {
        SharedPreferences sp = context.getSharedPreferences(AppConstants.APP_SHARED_PREFERENCE, Activity.MODE_PRIVATE);
        sp.edit().putBoolean(AppConstants.NETWORK_DISCONNECTED, disconnect).commit();
    }

    public static boolean wasNetDisconnected(Context context) {
        SharedPreferences sp = context.getSharedPreferences(AppConstants.APP_SHARED_PREFERENCE, Activity.MODE_PRIVATE);
        return sp.getBoolean(AppConstants.NETWORK_DISCONNECTED, false);
    }
}
