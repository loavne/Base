package com.hlh.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  SensorEventListener, View.OnClickListener {

    private TextView mX;
    private TextView mY;
    private TextView mZ;
    private SensorManager mSm;
    private TextView mNum;
    private Button mStart;

    private int count = 0;
    private float lastY = 0;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mX = (TextView) findViewById(R.id.x);
        mY = (TextView) findViewById(R.id.y);
        mZ = (TextView) findViewById(R.id.z);
        mNum = (TextView) findViewById(R.id.num);
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mSm = (SensorManager) getSystemService(SENSOR_SERVICE);
//
//        //定义方向传感器
//        Sensor sensor = mSm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
//
//        //注册方向传感器
//        mSm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //方向传感器上，x代表方位0北 90东 180南 270西  绕Z轴旋转
        //方向传感器上，y代表倾斜角度或者手机翘起程度    绕x轴旋转
        //方向传感器上，z代表沿Y轴滚动角度                 绕y轴旋转

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        mX.setText(x + "");
        mY.setText(y + "");
        mZ.setText(z + "");

//        if (flag) {
//            lastY = y;
//            flag = false;
//        }
//
//        //记步
//        if (Math.abs(y - lastY) > 8) {
//                lastY = y;
//            mNum.setText(++count + "");
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                //记步传感器
                Sensor s = mSm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
                mSm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
                break;
        }
    }
}
