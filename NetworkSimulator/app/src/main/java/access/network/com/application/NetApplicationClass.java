package access.network.com.application;

import android.app.Application;
import android.util.Log;

import java.util.Timer;

import access.network.com.utils.AppUtils;
import access.network.com.utils.NetworkStatusTimer;

/**
 * Created by Farina Ali
 */

public class NetApplicationClass extends Application {
    private Timer timer;
    private NetworkStatusTimer statusTimer;

    @Override
    public void onCreate() {
        super.onCreate();

        //starting connection timer for regular network checkups
        if (AppUtils.isConnectivityAvailable(getApplicationContext())) {
            startConnectionTask();
        }
    }

    public void stopConnectionTask() {
        if (timer != null) {
            timer.cancel();
            Log.d("-----timer stopped----", "");
        }
    }

    public void startConnectionTask() {
        stopConnectionTask();
        NetworkStatusTimer statusTimer = new NetworkStatusTimer(getApplicationContext());

        timer = new Timer();
        timer.schedule(statusTimer, 1000, 5000);
        Log.d("-----timer started-----", "");
    }
    public void restartTimer() {

        if (timer != null) {
            timer.cancel();
        }

        //  if (statusTimer == null)
        statusTimer = new NetworkStatusTimer(getApplicationContext());

//        if (timer == null)
        timer = new Timer();
        timer.schedule(statusTimer, 1000, 5000);
        Log.d("---timer Restarted----", "");
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        stopConnectionTask();
    }

}
