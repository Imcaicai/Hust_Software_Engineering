package com.example.library.sharedata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //创建用户表
    public static final String CREATE_USER="create table User(" +
            "id integer primary key autoincrement," +
            "name text," +
            "account text," +
            "password)";

    private Context context;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        this.context=context;
        db=getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);    //创建用户表
        Toast.makeText(context, "Create user table", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists User");
        onCreate(db);
    }

    //创建用户
    public void addUser(String account,String name,String password){
        db.execSQL("insert into User(account,name,password)values(?,?,?)",new Object[]{account,name,password});
    }

    //获取所有用户信息
    public ArrayList<User> getAllUser(){
        ArrayList<User> list=new ArrayList<User>();
        //获取User表中的数据，按学号降序
        Cursor cursor=db.query("user",null,null,null,null,null,"account DESC");
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            String account=cursor.getString(cursor.getColumnIndex("account"));
            @SuppressLint("Range")
            String name=cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range")
            String password=cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(account,name,password));
        }
        return list;
    }


}
