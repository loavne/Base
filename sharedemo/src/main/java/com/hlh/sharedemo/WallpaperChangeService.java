package com.hlh.sharedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-31
 * Time: 10:04
 */
public class WallpaperChangeService extends Service {
    private static int index = 0;
    private int[] ab = new int[]{R.raw.a, R.raw.b, R.raw.c, R.raw.d};

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if(index == 5)
            index = 0;
        InputStream inputStream = getResources().openRawResource(ab[index++]);
        try {
            setWallpaper(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStart(intent, startId);
    }
}
