package com.hlh.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-19
 * Time: 15:08
 */
public class MsgPushService extends Service{
    private static final String TAG = "MsgPushService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate complete...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand complete... ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
