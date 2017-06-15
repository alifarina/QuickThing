package access.network.com.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

/**
 * Created by Farina Ali
 */
public class NetworkStatusTimer extends TimerTask {
    private Context context;

    public NetworkStatusTimer(Context mContext) {
        context = mContext;
    }

    long startCon;
    Process p;
    String s;
    String res = "";
    BufferedReader stdInput;
    long connectionLatency;
    Intent localIntent;
    boolean isSlowAtleastOnce, hasStarted;

    @Override
    public void run() {

        hasStarted = true;


        executeCmd("ping -c 1 -w 1 google.com", false);
    }


    public void executeCmd(String cmd, boolean sudo) {
        startCon = System.currentTimeMillis();

        try {

            if (!sudo)
                p = Runtime.getRuntime().exec(cmd);
            else {
                p = Runtime.getRuntime().exec(new String[]{"su", "-c", cmd});
            }
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));


            while ((s = stdInput.readLine()) != null) {
                res += s + "\n";
            }
            p.destroy();
            connectionLatency = System.currentTimeMillis() - startCon;
            Log.e("Connect latency", connectionLatency + "");

            // return connectionLatency;

            // Todo : checking latency for greater than 700 = poor
            //Todo : can double it to 1400 as app.certain.com time is double that bing.com
            if (connectionLatency > 1000) {

                //detecting slow connection
                isSlowAtleastOnce = true;

                EventBus.getDefault().post(new MessageEvent(AppConstants.NETWORK_CONNECTION_WEEK));

            } else if ((hasStarted && isSlowAtleastOnce) || AppUtils.wasNetDisconnected(context)) {

                isSlowAtleastOnce = false;
                AppUtils.setDisconnection(context, false);

                EventBus.getDefault().post(new MessageEvent(AppConstants.NETWORK_CONNECTED));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return connectionLatency;

    }

    private final int MSG_UPDATE_CONNECTION_TIME = 1;


}
