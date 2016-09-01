package com.hlh.memory.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-06-01
 * Time: 16:38
 */
public class FileBrowser extends ListView implements AdapterView.OnItemClickListener {
    private final String namespace = "http://mobile.blogjava.net";
    private String sdcardDirectory;
    private List<File> fileList = new ArrayList<File>();
    private Stack<String> dirStack = new Stack<String>();
    private FileListAdapter fileListAdapter;
    private OnFileBrowserListener onFileBrowserListener;
    private int folderImageResId;  //目录头像图片
    private int otherFileImageResId;    //其他文件头像图片
    private Map<String, Integer> fileImageResIdMap = new HashMap<String, Integer>(); //文件图片集合
    private boolean onlyFolder = false; //是否只有文件夹

    public FileBrowser(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnItemClickListener(this);

        /**
         * 显示SD卡根目录所有子目录以及文件
         */
        //1.获取SD卡目录
        sdcardDirectory = Environment.getExternalStorageDirectory().toString();
        setBackgroundColor(Color.WHITE);

        folderImageResId = attrs.getAttributeResourceValue(namespace, "folderImage", 0);
        otherFileImageResId = attrs.getAttributeResourceValue(namespace, "otherFileImage", 0);
        onlyFolder = attrs.getAttributeBooleanValue(namespace, "onlyFolder", false);
        int index = 1;

        while (true) {
            String extName = attrs.getAttributeValue(namespace, "extName" + index);
            int fileImageResId = attrs.getAttributeResourceValue(namespace, "fileImage" + index, 0);
            if ("".equals(extName) || extName == null || fileImageResId == 0)
                break;
            fileImageResIdMap.put(extName, fileImageResId);
            index++;
        }

        dirStack.push(sdcardDirectory);
        addFiles();

        fileListAdapter = new FileListAdapter(getContext());
        setAdapter(fileListAdapter);
    }

    private void addFiles() {
        fileList.clear();
        String currentPath = getCurrentPath();
        File[] files = new File(currentPath).listFiles();
        if (dirStack.size() > 1)
            fileList.add(null);
        for (File file : files) {
            if (onlyFolder) {
                if (file.isDirectory())
                    fileList.add(file);
            } else {
                fileList.add(file);
            }
        }
    }

    private String getCurrentPath() {
        String path = "";
        for (String dir : dirStack) {
            path += dir + "/";
        }
        path = path.substring(0, path.length() - 1);
        return path;
    }

    private String getExtName(String fileName) {
        int position = fileName.lastIndexOf(".");
        if (position >= 0)
            return fileName.substring(position + 1);
        else
            return "";
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (fileList.get(position) == null) {
            dirStack.pop();
            addFiles();
            fileListAdapter.notifyDataSetChanged();
            if (onFileBrowserListener != null) {
                onFileBrowserListener.onDirItemClick(getCurrentPath());
            }
        } else if (fileList.get(position).isDirectory()) {
            dirStack.push(fileList.get(position).getName());
            addFiles();
            fileListAdapter.notifyDataSetChanged();
            if (onFileBrowserListener != null) {
                onFileBrowserListener.onDirItemClick(getCurrentPath());
            }
        } else {
            if (onFileBrowserListener != null) {
                String filename = getCurrentPath() + "/"
                        + fileList.get(position).getName();
                onFileBrowserListener.onFileItemClick(filename);
            }
        }
    }


    /**
     * 提供当前目录中的子目录以及文件的名称列表
     */
    private class FileListAdapter extends BaseAdapter {
        private Context context;

        public FileListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return fileList.size();
        }

        @Override
        public Object getItem(int position) {
            return fileList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout fileLayout = new LinearLayout(context);
            fileLayout.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            fileLayout.setOrientation(LinearLayout.HORIZONTAL);
            fileLayout.setPadding(5, 10, 0, 10);
            ImageView ivFile = new ImageView(context);
            ivFile.setLayoutParams(new LayoutParams(48, 48));
            TextView tvFile = new TextView(context);
            tvFile.setTextColor(android.graphics.Color.WHITE);
            tvFile.setTextAppearance(context,
                    android.R.style.TextAppearance_Large);

            tvFile.setPadding(5, 5, 0, 0);
            if (fileList.get(position) == null) {

                if (folderImageResId > 0)
                    ivFile.setImageResource(folderImageResId);
                tvFile.setText(". .");
            } else if (fileList.get(position).isDirectory()) {
                if (folderImageResId > 0)
                    ivFile.setImageResource(folderImageResId);
                tvFile.setText(fileList.get(position).getName());
            } else {
                tvFile.setText(fileList.get(position).getName());
                Integer resId = fileImageResIdMap.get(getExtName(fileList.get(
                        position).getName()));
                int fileImageResId = 0;
                if (resId != null) {
                    if (resId > 0) {
                        fileImageResId = resId;
                    }

                }
                if (fileImageResId > 0)
                    ivFile.setImageResource(fileImageResId);
                else if (otherFileImageResId > 0)
                    ivFile.setImageResource(otherFileImageResId);
            }

            tvFile.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            fileLayout.addView(ivFile);
            fileLayout.addView(tvFile);
            return fileLayout;
        }
    }

    public void setOnFileBrowserListener(OnFileBrowserListener onFileBrowserListener) {
        this.onFileBrowserListener = onFileBrowserListener;
    }
}
