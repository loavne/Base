package com.hlh.sharedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import java.util.Calendar;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-31
 * Time: 11:13
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp = context.getSharedPreferences("abc", Activity.MODE_PRIVATE);
        String hour = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
        //从xml文件中获取
        String time = sp.getString(hour + ":" + minute, null);
        if (time != null) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.ring);
            mp.start();
        }
    }
}
