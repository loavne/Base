package com.hlh.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-24
 * Time: 10:13
 */
public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
}
