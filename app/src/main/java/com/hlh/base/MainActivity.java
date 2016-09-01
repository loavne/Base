package com.hlh.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSendBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendBroadcast = (Button) findViewById(R.id.sendBroadcast);
        mSendBroadcast.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.hlh.base");
        intent.putExtra("msg", "love Android");
        sendOrderedBroadcast(intent, "com.hlh.base.MY_BROADCASTRECEIVER_PERMISSION");
    }

}
