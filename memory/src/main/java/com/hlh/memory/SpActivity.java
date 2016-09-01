package com.hlh.memory;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hlh";
    private Button mWrite;
    private Button mClear;
    private Button mRead;
    private EditText mSId;
    private EditText mSName;
    private SharedPreferences mSharedPreferences;
    private Student mStudent;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mWrite = (Button) findViewById(R.id.write);
        mClear = (Button) findViewById(R.id.clear);
        mRead = (Button) findViewById(R.id.read);
        mSId = (EditText) findViewById(R.id.sId);
        mSName = (EditText) findViewById(R.id.sName);
        mImg = (ImageView) findViewById(R.id.img);
        mWrite.setOnClickListener(this);
        mClear.setOnClickListener(this);
        mRead.setOnClickListener(this);

        mSharedPreferences = getSharedPreferences(TAG, Activity.MODE_PRIVATE);
        mStudent = new Student();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //保存对象
                //将文本框写入student中
                mStudent.setId(mSId.getText().toString());
                mStudent.setName(mSName.getText().toString());
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    //将student放入oos
                    oos.writeObject(mStudent);
                    //将student对象转换为byte数组，并Base64编码
                    String studentBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
                    //存储student
                    mSharedPreferences.edit().putString("student", studentBase64).commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //图像存储
                ((BitmapDrawable) mImg.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 50, baos);
                String imageBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
                //保存由图像字节流转化成的Base64字符串
                mSharedPreferences.edit().putString("img", imageBase64).commit();
                break;
            case R.id.clear:
                mSId.setText("");
                mSName.setText("");
                mImg.setImageDrawable(null);
                break;
            //读取对象
            case R.id.read:
                String studentBase64 = mSharedPreferences.getString("student", "");
                //对Base64解码
                byte[] base64Bytes = Base64.decodeBase64(studentBase64.getBytes());
                ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
                try {
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    mStudent = (Student) ois.readObject();
                    mSId.setText(mStudent.getId());
                    mSName.setText(mStudent.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //图像解析
                String imgBase64 = mSharedPreferences.getString("img", "");
                base64Bytes = Base64.encodeBase64(imgBase64.getBytes());
                bais = new ByteArrayInputStream(base64Bytes);
                mImg.setImageDrawable(Drawable.createFromStream(bais, "ss"));
                break;
        }
    }
}
