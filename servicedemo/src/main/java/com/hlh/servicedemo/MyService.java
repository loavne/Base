package com.hlh.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-24
 * Time: 11:25
 */
public class MyService extends Service {

    private static final String TAG = "MyService";

    private MyBinder myBinder = new MyBinder();

    //成功绑定后调用该方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return myBinder;
    }

    //重新绑定时调用该方法
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    //解除绑定时调用该方法
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    //当服务第一次创建时调用该方法
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    //当服务开始启动时调用
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: ");
    }

    //当服务销毁时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

}
