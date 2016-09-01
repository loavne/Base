package com.hlh.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //1.使用URL封装url
            URL url = new URL("http://www...");

            //2.使用openConnection() 获取 HttpUrlConnection对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //3.设置访问方式
            conn.setRequestMethod("POST");

            //4.设置输入输出以及其他权限。下载或者上传
            //下载HTTP资源，需设置为true
            conn.setDoInput(true);
            //上传
            conn.setDoOutput(true);

            //5.设置请求头
            conn.setRequestProperty("Charset", "UTF-8");

            //读取，写入
            InputStream is = conn.getInputStream();
            OutputStream os = conn.getOutputStream();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
