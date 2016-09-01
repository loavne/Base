package com.hlh.memory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hlh.memory.widget.FileBrowser;
import com.hlh.memory.widget.OnFileBrowserListener;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-06-01
 * Time: 16:32
 */
public class FileBrowerActivity extends AppCompatActivity implements OnFileBrowserListener {

    private FileBrowser mFileBrowser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filebrower);
        mFileBrowser = (FileBrowser) findViewById(R.id.fileBrowser);
        mFileBrowser.setOnFileBrowserListener(this);
    }

    @Override
    public void onFileItemClick(String fileName) {
        setTitle(fileName);
    }

    @Override
    public void onDirItemClick(String path) {
        setTitle(path);
    }
}
