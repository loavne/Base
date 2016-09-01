package com.hlh.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-20
 * Time: 15:43
 */
public class OtherActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        TextView tv = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        //设置文本
        tv.setText(intent.getStringExtra("msg"));
    }
}
