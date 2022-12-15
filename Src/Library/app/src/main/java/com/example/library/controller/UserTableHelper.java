package com.example.library.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.library.model.User;

import java.util.ArrayList;

public class UserTableHelper {

    /**
     * 将注册的个人信息插入User表中
     * @param dbHelper
     * @param name
     * @param account
     * @param password
     */
    public void Register(DatabaseHelper dbHelper,String name,String account,String password){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("account",account);
        values.put("password",password);
        db.insert("User",null,values);
        db.close();
    }

    /**
     * 检测注册时输入的学号是否有重复
     * @param dbHelper
     * @param account
     * @return boolean
     */
    public boolean CheckUserAccount(DatabaseHelper dbHelper,String account){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor = db.query("User",new String[]{"account"},"account=?",
                new String[]{account + ""},null,null,null);
        int count = cursor.getCount();
        boolean result;
        if(count==0){
            result=true;
        }else {
            result=false;
        }
        db.close();
        return result;
    }

    /**
     * 检查登录时输入的学号和密码是否正确
     * @param dbHelper
     * @param account
     * @param password
     * @return boolean
     */
    public boolean Login(DatabaseHelper dbHelper,String account,String password){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("User",new String[]{"account,password"},
                "account=? and password=?",new String[]{account+"",password+""},
                null,null,null);
        int count=cursor.getCount();
        boolean result;
        if(count!=0){
            result=true;
        }else{
            result=false;
        }
        db.close();
        return result;
    }

    //用户管理详情页
    public ArrayList<User> userDetails(DatabaseHelper dbHelper,String uid){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("User",new String[]{"id","name","account","password"},
                "id=?",new String[]{uid+""},null,null,null);
        ArrayList<User> list=new ArrayList<>();
        if(cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String account = cursor.getString(cursor.getColumnIndex("account"));
                User user=new User(id,name,account);
                list.add(user);
            }
        }
        db.close();
        return list;
    }

    //用户登录数据
    public ArrayList<User> UserList(DatabaseHelper dbHelper,String account){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("User",new String[]{"id","name","account"},
                "account=?",new String[]{account+""},null,null,null);
        ArrayList<User> list=new ArrayList<>();
        if(cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                User user=new User(id,name,account);
                list.add(user);
            }
        }
        db.close();
        return list;
    }

    //收藏图书
    public void CollectBook(DatabaseHelper dbHelper,String uid,String collection){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("collection",collection);
        db.update("User",values,"id=?",new String[]{uid});
        db.close();
    }

    //查询已收藏的书
    public String FindCollectionBooks(DatabaseHelper dbHelper,String uid){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("User",new String[]{"collection"},
                "id = ?",new String[]{uid+""},null,null,null);

        String result="null";
        if (cursor !=null && cursor.getCount() >0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String collections = cursor.getString(cursor.getColumnIndex("collection"));
                result=collections;
            }
        }

        db.close();
        return result;
    }
}
