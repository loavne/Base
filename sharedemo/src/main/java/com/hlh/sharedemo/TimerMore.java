package com.hlh.sharedemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-31
 * Time: 10:27
 */
public class TimerMore extends AppCompatActivity {

    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timermore);
        Button bt = (Button) findViewById(R.id.start);
        final TextView tv = (TextView) findViewById(R.id.tv);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //开始定时器，一分钟烧卖一次
        am.setRepeating(AlarmManager.RTC, 0, 60 * 1000, pendingIntent);
        mSp = getSharedPreferences("abc", Activity.MODE_PRIVATE);

        final View view = getLayoutInflater().inflate(R.layout.alarm, null);
        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.setTitle("设置提醒时间")
                        .setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取时间
                        String timeStr = String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
                        //将时间放到tv中
                        tv.setText(tv.getText().toString() + "\n" + timeStr);
                        //保存到sp中
                        mSp.edit().putString(timeStr, timeStr).commit();
                    }
                })
                        .setNegativeButton("取消", null).show();
            }
        });
    }


}
