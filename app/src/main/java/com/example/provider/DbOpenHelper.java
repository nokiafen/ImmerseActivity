package com.example.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenhailin on 2017/9/4.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;//版本

    private static final String DB_NAME = "people.db";//数据库名

    public static final String STUDENT_TABLE = "student";//表名

    public static final String _ID = "_id";//表中的列名

    public static final String NAME = "name";//表中的列名

    //创建数据库语句，STUDENT_TABLE，_ID ，NAME的前后都要加空格

    private static final String CREATE_TABLE = "create table " + STUDENT_TABLE + " ( " + _ID + " Integer primary key autoincrement," + NAME + " text)";



    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);

    }



    //数据库第一次被创建时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }



    //版本升级时被调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
