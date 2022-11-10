package com.example.library.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import androidx.arch.core.executor.DefaultTaskExecutor;

import com.example.library.sharedata.Borrow;

import java.util.ArrayList;

public class BorrowTableHelper {
    
    /**
     * 新增借阅信息
     * @param dbHelper
     * @param borrow
     * @return
     */
    public boolean AddBorrow(DatabaseHelper dbHelper, Borrow borrow){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("botime",borrow.getBoTime());
        values.put("bookid",borrow.getBookId());
        values.put("userid",borrow.getUserId());
        values.put("state",borrow.getState());
        long id = db.insert("Borrow",null,values);
        db.close();
        boolean flag;
        if (id != -1){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }

    //查询用户借阅记录
    public ArrayList<Borrow> ShowBorrow(DatabaseHelper dbHelper, String uid){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Borrow where userid = ? order by id desc",new String[]{uid});
        ArrayList<Borrow> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String bookId = cursor.getString(cursor.getColumnIndex("bookid"));
                @SuppressLint("Range")
                String boTime = cursor.getString(cursor.getColumnIndex("botime"));
                @SuppressLint("Range")
                String state = cursor.getString(cursor.getColumnIndex("state"));
                @SuppressLint("Range")
                String reTime = cursor.getString(cursor.getColumnIndex("retime"));
                Borrow borrow = new Borrow(boTime,bookId,uid,reTime,state);
                list.add(borrow);
            }
        }
        db.close();
        return list;
    }

    //还书
    public void ReturnBook(DatabaseHelper dbHelper, String id, String reTime){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state","已归还");
        values.put("retime",reTime);
        db.update("Borrow",values,"id=?",new String[]{id});
        db.close();
    }


}
