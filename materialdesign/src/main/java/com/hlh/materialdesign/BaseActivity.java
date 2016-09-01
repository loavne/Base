package com.hlh.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-07-19
 * Time: 11:55
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * activity切换
     * @param mClass
     */
    public void openActivity(Class<?> mClass){
        openActivity(mClass, null, 0);
    }

    public void openActivity(Class<?> mClass, int requestCode){
        openActivity(mClass, null, requestCode);
    }

    public void openActivity(Class<?> mClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, mClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (requestCode == 0) {
            IntentUtils.startPreviewActivity(this, intent, 0);
        } else {
            IntentUtils.startPreviewActivity(this, intent, requestCode);
        }
    }
}
