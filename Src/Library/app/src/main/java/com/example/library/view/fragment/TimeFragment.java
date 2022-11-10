package com.example.library.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.R;
import com.example.library.view.activity.BookActivity;
import com.example.library.view.activity.GoldActivity;
import com.example.library.view.activity.MainActivity;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class TimeFragment extends Fragment {

    private View view;
    private TextView gold;
    private ImageView timeBtn;
    private ImageView cityName;
    private ImageView cityImg;
    private TextView sumTime;
    private TextView thisTime;
    private boolean flag=false; //false代表现在没暂停，true代表现在计时中
    public int m_var; //需要在消息处理中访问的成员变量，一定要声明成public
    MyHandler handler = new MyHandler(Looper.myLooper(),this);//获取Looper并传递



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_time, container, false);
        gold = view.findViewById(R.id.gold_text);
        timeBtn = view.findViewById(R.id.time_btn);
        cityName = view.findViewById(R.id.city_name);
        cityImg = view.findViewById(R.id.city_img);
        sumTime = view.findViewById(R.id.sum_time);
        thisTime = view.findViewById(R.id.this_time);


        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(context, GoldActivity.class);
                context.startActivity(intent);
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    if(thisTime.getText().equals("00:00")){
                        cityName.setImageResource(R.drawable.city_wuhan_name);
                        cityImg.setImageResource(R.drawable.city_wuhan_img);
                    }

                    //转为开始
                    flag=true;
                    timeBtn.setImageResource(R.drawable.btn_stop);

                    new Thread(){
                        @Override
                        public void run(){
                            super.run();
                            //时间不停增长
                            //时间处理不在子线程中
                            int i = 1;
                            while (flag){
                                try {
                                    sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Message mesg = new Message();
                                mesg.arg1 = i;
                                handler.sendMessage(mesg);
                                //时间增加
                                i++;
                            }

                        }
                    }.start();

                }else {
                    flag=false;
                    timeBtn.setImageResource(R.drawable.btn_start);
                }

            }
        });
        return view;
    }


    //新的handler类要声明成静态类
    static class MyHandler extends Handler {
        WeakReference<TimeFragment> mfragment;

        //构造函数，传来的是外部类的this
        public MyHandler(@NonNull Looper looper, TimeFragment fragment) {
            super(looper);//调用父类的显式指明的构造函数
            mfragment = new WeakReference<TimeFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            TimeFragment nfragment = mfragment.get();
            if (nfragment == null)
                return;


            //启动
            int min = msg.arg1/60;
            int sec = msg.arg1 % 60;
            //00:00
            String timestr = (min <10 ? "0" +min:""+min)+":"+(sec<10 ? "0" +sec:""+sec);
            nfragment.thisTime.setText(timestr);
            nfragment.sumTime.setText(timestr);
            if (nfragment.flag == false){
                nfragment.timeBtn.setImageResource(R.drawable.btn_start);
            }
        }

    }

}
