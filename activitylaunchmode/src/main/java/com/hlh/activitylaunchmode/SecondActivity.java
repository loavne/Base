package com.hlh.activitylaunchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-23
 * Time: 20:05
 */
public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("序列号" +this.toString() +"---current task id: " + this.getTaskId());
        Button button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
    }
}
