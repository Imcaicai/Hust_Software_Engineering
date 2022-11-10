package com.example.library.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.library.R;
import com.example.library.sharedata.Book;
import com.example.library.sharedata.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    //创建用户表
    public static final String CREATE_USER="create table User(" +
            "id integer primary key autoincrement," +
            "name varchar(20)," +
            "account varchar(20)," +
            "collection varchar(20),"+
            "password varchar(20))";

    //创建图书表
    public static final String CREATE_BOOK="create table Book(" +
            "id integer primary key autoincrement," +
            "name varchar(20)," +
            "author varchar(20)," +
            "publish varchar(20)," +
            "state varchar(20)," +
            "theme varchar(20)," +
            "location varchar(20)," +
            "introduction varchar(100)," +
            "ISBN varchar(20)," +
            "cover varchar(20))";

    //创建借阅表
    public static final String CREATE_BORROW="create table Borrow(" +
            "id integer primary key autoincrement," +
            "botime varchar(20)," +
            "bookid varchar(20)," +
            "userid varchar(20)," +
            "retime varchar(20)," +
            "cover varchar(20)," +
            "state varchar(20))";

    //创建收藏表
    public static final String CREATE_COLLECT="create table Collect(" +
            "id integer primary key autoincrement," +
            "bookid varchar(20)," +
            "userid varchar(20)," +
            "cover varchar(20)," +
            "state varchar(20))";

    //重写构造方法
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        this.context=context;
    }

    /**
     * 创建数据库，初始化数据表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);    //创建用户表
        db.execSQL(CREATE_BOOK);    //创建图书表
        db.execSQL(CREATE_BORROW);  //创建借书表
        db.execSQL(CREATE_COLLECT);  //创建还书表
        Toast.makeText(context, "Create succeed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 升级数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Book");
        //onCreate(db);
    }


}
