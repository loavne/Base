package com.hlh.sharedemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Chronometer.OnChronometerTickListener {

    private static final String TAG = "Hlh";

    private Chronometer mCnm;
    private TextView mTv;
    private ProgressBar mPb;

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //更新pb的进度
                    int currentProgress = mPb.getProgress() + 5;
                    //超过了pb的最大值。设为0，重新来
                    if(currentProgress > mPb.getMax())
                        currentProgress = 0;
                    mPb.setProgress(currentProgress);
                    break;
            }
        }
    };
    private AlarmManager mAm;
    private PendingIntent mPIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCnm = (Chronometer) findViewById(R.id.cnm);
        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        Button reset = (Button) findViewById(R.id.reset);
        mTv = (TextView) findViewById(R.id.nowTime);
        mPb = (ProgressBar) findViewById(R.id.pb);
        mCnm.setOnChronometerTickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);
        mCnm.setFormat("love TIME"+"%s");
        Log.d(TAG, "onCreate: ------------");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mCnm.start();

                mAm = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(this, MainActivity.class);
                mPIntent = PendingIntent.getActivity(this, 0, intent, 0);
                mAm.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, mPIntent);
//                Timer timer = new Timer();
//                //一秒执行一次
//                timer.schedule(mTimerTask, 0, 1000);

                break;
            case R.id.stop:
                mCnm.stop();
                mAm.cancel(mPIntent);
                break;
            case R.id.reset:
                mCnm.setBase(SystemClock.elapsedRealtime());
                break;

        }
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy:mm:dd HH:mm:ss");
        mTv.setText(sdf.format(new Date()));
    }
}
