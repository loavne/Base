package com.hlh.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-07-19
 * Time: 11:51
 */
public class TextInputLayoutActivity extends AppCompatActivity {

    @Bind(R.id.userName)
    EditText mUserName;
    @Bind(R.id.userNameWrapper)
    TextInputLayout mUserNameWrapper;
    @Bind(R.id.pd)
    EditText mPd;
    @Bind(R.id.pdWrapper)
    TextInputLayout mPdWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textinputlayout);
        ButterKnife.bind(this);

//        //textinputlayout设置了setHint才能显示上浮
//        mUserNameWrapper.setHint(getString(R.string.username));
//        mPdWrapper.setHint(getString(R.string.password));

//        mPd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            //检测错误输入，当输入错误时，hint会变成红色并提醒
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length()>16) {
//                    mPdWrapper.setError("密码必须大于6位小于16位");
//                } else {
//                    mPdWrapper.setError("");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }
}
