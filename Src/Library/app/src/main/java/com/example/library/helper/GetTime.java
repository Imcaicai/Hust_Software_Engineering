package com.example.library.helper;

import java.util.Calendar;

public class GetTime {
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int date = c.get(Calendar.DATE);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int second = c.get(Calendar.SECOND);
    String time;
    public String BorrowTime(){
        return time = year+"-"+month+"-"+date;
    }
}
