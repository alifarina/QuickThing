package access.network.com.networksimulator;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import access.network.com.utils.AppConstants;
import access.network.com.utils.MessageEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkConnected(MessageEvent event) {

        switch (event.getEventStatus()) {

            case AppConstants.NETWORK_CONNECTED:
                showToast(getString(R.string.available));
                break;

            case AppConstants.NETWORK_DISCONNECTED:
                showToast(getString(R.string.offline));
                break;
        }


    }
}
