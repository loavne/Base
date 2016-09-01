package com.hlh.memory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-06-02
 * Time: 11:02
 */
public class DBHelper extends SQLiteOpenHelper{

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "test";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
