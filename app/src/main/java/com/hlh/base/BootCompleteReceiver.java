package com.hlh.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-19
 * Time: 15:05
 */
public class BootCompleteReceiver extends BroadcastReceiver{
    private static final String MSG = "BootCompleteReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //启动消息推送服务
        Intent service = new Intent(context, MsgPushService.class);
        context.startService(service);
        Log.d(MSG, "Boot Complete , Starting MsgPushService...");
    }
}
