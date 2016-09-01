package com.hlh.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent mIntent;

    private MyService mService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        //成功连接服务后，被调用。在该方法里获得MyService对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取MyService对象
            mService = ((MyService.MyBinder) service).getService();
            Toast.makeText(MainActivity.this, "Service Connected", Toast.LENGTH_SHORT).show();
        }

        //连接服务失败后，调用该方法。
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Toast.makeText(MainActivity.this, "Service Failed", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.startService);
        Button stop = (Button) findViewById(R.id.stopService);
        Button bind = (Button) findViewById(R.id.bindService);
        Button unbind = (Button) findViewById(R.id.unbindService);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        mIntent = new Intent(this, MyService.class);

        //通话服务管理
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        MyPhoneCallListener listener = new MyPhoneCallListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public class MyPhoneCallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //获取音频服务
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            switch (state) {
                //正常状态
                case TelephonyManager.CALL_STATE_IDLE:
                    //手机空闲状态时，将手机音频设为正常状态
                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    break;

                //通话状态
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(MainActivity.this, "通话中。。。。。", Toast.LENGTH_SHORT).show();
                    break;
                //来电状态
                case TelephonyManager.CALL_STATE_RINGING:
                    //来电时，如果号码为黑名单，则设置为静音
                    if (incomingNumber.equals("199")) {
                        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }

                    Toast.makeText(MainActivity.this, incomingNumber, Toast.LENGTH_SHORT).show();
                    break;

            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                startService(mIntent);      //启动服务
                break;
            case R.id.stopService:
                stopService(mIntent);       //停止服务
                break;
            case R.id.bindService:          //
                bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                unbindService(mServiceConnection);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
