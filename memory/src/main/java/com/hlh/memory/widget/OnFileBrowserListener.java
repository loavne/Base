package com.hlh.memory.widget;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-06-01
 * Time: 16:35
 */
public interface OnFileBrowserListener {
    //单击文件列表时调用，fileName点击的文件名
    public void onFileItemClick(String fileName);
    //单击目录列表时调用，path目录完整路径
    public void onDirItemClick(String path);
}
