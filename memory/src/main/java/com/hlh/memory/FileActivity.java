package com.hlh.memory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 文件存储
 * User: Hlh(tatian91@163.com)
 * Date: 2016-06-01
 * Time: 15:48
 */
public class FileActivity extends AppCompatActivity{

    private EditText mEt;
    private TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mEt = (EditText) findViewById(R.id.et);
        mTv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //写入
                try {
                    //1.获取写入流对象
                    OutputStream os = openFileOutput("a.txt", Activity.MODE_PRIVATE);
                    String a = mEt.getText().toString();
                    //2.字符串变成字节数组写入流对象中
                    os.write(a.getBytes("utf-8"));
                    //3.刷新os
                    os.flush();
                    //4.关闭os
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读出
                try {
                    //1.获取读取流对象
                    InputStream is = openFileInput("a.txt");
                    //定义字节数组
                    byte[] buffer = new byte[1024];
                    int count = is.read(buffer);
                    String b = new String(buffer, 0, count, "utf-8");
                    is.close();
                    mTv.setText(b);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
