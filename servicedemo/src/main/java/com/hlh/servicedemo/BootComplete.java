package com.hlh.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-30
 * Time: 10:01
 */
public class BootComplete extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        //启动服务
        Log.i("Hlh", "onReceive: --------------");
        Intent serviceIntent = new Intent(context, MyService.class);
        context.startService(serviceIntent);
    }
}
