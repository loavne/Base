package com.hlh.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-19
 * Time: 15:28
 */
public class NetworkStateReceiver extends BroadcastReceiver{
    private static final String TAG = "NetworkStateReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "network state changed");
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, "network disconnected error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager ctm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = ctm.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if(info[i].getState() == NetworkInfo.State.CONNECTED)
                    return true;
            }
        }
        return false;
    }
}
