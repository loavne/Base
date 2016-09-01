package com.hlh.sharedemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-31
 * Time: 09:48
 */
public class Other extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //获取警报器服务
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(Other.this, 0, new Intent(this, WallpaperChangeService.class), 0);
        switch (v.getId()) {

            case R.id.start:
                am.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, pendingIntent);

                break;
            case R.id.stop:
                am.cancel(pendingIntent);
                break;
        }
    }
}
