package com.hlh.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-19
 * Time: 10:18
 */
public class FirstReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(MyConfig.LOG, "FirstReceiver  " + intent.getStringExtra("msg"));

        Bundle bundle = new Bundle();
        bundle.putString("msg", intent.getStringExtra("msg") + " @FirstReceiver");
        setResultExtras(bundle);
        abortBroadcast();
    }
}
