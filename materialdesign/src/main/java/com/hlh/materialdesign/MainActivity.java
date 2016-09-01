package com.hlh.materialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.textInputLayout)
    Button mTextInputLayout;
    @Bind(R.id.snackbar)
    Button mSnackbar;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.textInputLayout, R.id.snackbar, R.id.floatingActionButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textInputLayout:
                openActivity(TextInputLayoutActivity.class);
                break;
            case R.id.snackbar:
                openActivity(SnackbarActivity.class);
                break;
            case R.id.floatingActionButton:
                break;
        }
    }
}
