package com.example.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.library.viewModel.LoginViewModel;

public class AuthorUtils {
    public static AuthorUtils authorUtils;

    //一个轻量级的存储类，用来保存应用程序的各种配置信息，其本质是一个以“键-值”对的方式保存数据的xml文件
    private SharedPreferences preferences;

    private Activity activity;

    public AuthorUtils(Activity activity){
        this.activity = activity;
        init();
    }

    public static AuthorUtils getInstance(Activity activity){
        if(authorUtils == null){
            synchronized (AuthorUtils.class){   //同步线程
                if(authorUtils ==null){
                    authorUtils = new AuthorUtils(activity);
                }
            }
        }
        return authorUtils;
    }

    public void init(){
        preferences = activity.getSharedPreferences(LoginViewModel.USER_FILE, Context.MODE_PRIVATE);
    }

    public String getUserId(){
        return preferences.getString(LoginViewModel.USER_ID, null);
    }

    public String getToken(){
        return preferences.getString(LoginViewModel.USER_TOKEN, null);
    }

    public void setUserId(String userId){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LoginViewModel.USER_ID, userId);
        editor.apply();
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LoginViewModel.USER_TOKEN, token);
        editor.apply();
    }
}

