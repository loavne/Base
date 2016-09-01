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
public class SecondReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(MyConfig.LOG, "SecondReceiver " + getResultExtras(true).getString("msg"));

        Bundle bundle = new Bundle();
        bundle.putString("msg", getResultExtras(true).getString("msg") + " @SecondReceiver");
        setResultExtras(bundle);
    }
}
