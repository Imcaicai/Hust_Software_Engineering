package com.example.library.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class MWindowsUtils {
    @SuppressLint("StaticFieldLeak")
    private static Activity ac;

    public static void init(Activity activity){
        ac = activity;
    }

    public static int getHeight(){
        WindowManager manager = ac.getWindowManager();
        return manager.getDefaultDisplay().getHeight();
    }

    public static int getWidth(int dip) {
        WindowManager manager = ac.getWindowManager();
        float density = ac.getResources().getDisplayMetrics().density;
        return (manager.getDefaultDisplay().getWidth() - (3 * dpToPx(dip))) / 2;
    }

    public static int getScreenWidth() {
        WindowManager manager = ac.getWindowManager();
        return manager.getDefaultDisplay().getWidth();
    }

    private static int dpToPx(int dip) {
        return (int) ((float) dip * ac.getResources().getDisplayMetrics().density + 0.5f);
    }
}
