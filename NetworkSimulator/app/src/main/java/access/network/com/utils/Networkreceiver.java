package access.network.com.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by infoobjects on 14-06-2017.
 */

public class Networkreceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("on action ", intent.getAction());

        if (intent.getAction().equals(AppConstants.ACTION_CONNECTIVITY_CHANGE)) {

            //Observed a change in metwork connectivity

            ConnectivityManager manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {

                processInfo(info);

            } else {

                //no network found
                EventBus.getDefault().post(new MessageEvent(AppConstants.NETWORK_DISCONNECTED));

            }
        }

    }

    private void processInfo(NetworkInfo info) {
        switch (info.getState()) {
            case CONNECTED:
                EventBus.getDefault().post(new MessageEvent(AppConstants.NETWORK_CONNECTED));
                break;
            case CONNECTING:
                break;
            case DISCONNECTED:
                EventBus.getDefault().post(new MessageEvent(AppConstants.NETWORK_DISCONNECTED));
                break;
            case DISCONNECTING:
                break;
        }
    }
}
