package com.example.library.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.library.sharedata.Book;

import java.util.ArrayList;

public class BookTableHelper {
    //添加图书
    public boolean addBook(DatabaseHelper dbHelper,Book book){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",book.getName());
        values.put("author",book.getAuthor());
        values.put("state",book.getState());
        values.put("publish",book.getPublish());
        values.put("introduction",book.getIntroduction());
        values.put("theme",book.getTheme());
        values.put("ISBN",book.getISBN());
        values.put("cover",book.getCover());
        values.put("location",book.getLocation());
        long id=db.insert("Book",null,values);
        db.close();
        boolean flag;
        if(id!=-1){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    //图书grid展示
    public ArrayList<Book> bookGrid(DatabaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",new String[]{"id","name","author","state","cover"},
                null,null,null,null,"id desc");
        ArrayList<Book> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String author = cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range")
                String state = cursor.getString(cursor.getColumnIndex("state"));
                @SuppressLint("Range")
                String cover = cursor.getString(cursor.getColumnIndex("cover"));
                Book book = new Book(id,name,author,state,cover);
                list.add(book);
            }
        }
        db.close();
        return list;
    }


    //图书详情页
    public ArrayList<Book> getBookDetails(DatabaseHelper dbHelper,String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",new String[]{"ISBN","name","author","publish",
                        "location","theme","introduction","state","cover"},"id = ?",
                new String[]{id + ""},null,null,null);
        ArrayList<Book> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String ISBN = cursor.getString(cursor.getColumnIndex("ISBN"));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String author = cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range")
                String publish = cursor.getString(cursor.getColumnIndex("publish"));
                @SuppressLint("Range")
                String location = cursor.getString(cursor.getColumnIndex("location"));
                @SuppressLint("Range")
                String theme = cursor.getString(cursor.getColumnIndex("theme"));
                @SuppressLint("Range")
                String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
                @SuppressLint("Range")
                String state = cursor.getString(cursor.getColumnIndex("state"));
                @SuppressLint("Range")
                String cover = cursor.getString(cursor.getColumnIndex("cover"));
                Book book = new Book(name,author,publish,location,cover,state,ISBN,theme,introduction);
                list.add(book);
            }
        }
        db.close();
        return list;
    }

    //搜索图书功能
    public ArrayList<Book> searchBook(DatabaseHelper dbHelper,String searcher){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("book",new String[]{"id","name","author","state","cover"},
                "name like ? or author like ?",new String[]{"%"+searcher+"%","%"+searcher+"%"},
                null,null,null);
        ArrayList<Book> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                @SuppressLint("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String author = cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range")
                String state = cursor.getString(cursor.getColumnIndex("state"));
                @SuppressLint("Range")
                String cover = cursor.getString(cursor.getColumnIndex("cover"));
                Book book = new Book(id,name,author,state,cover);
                list.add(book);
            }
        }
        db.close();
        return list;
    }

    //借书
    public void borrowBook(DatabaseHelper dbHelper,String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state","已被借阅");
        db.update("Book",values,"id=?",new String[]{id});
        db.close();
    }

    //还书
    public void returnBook(DatabaseHelper dbHelper,String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state","在馆内");
        db.update("Book",values,"id=?",new String[]{id});
        db.close();
    }


}
