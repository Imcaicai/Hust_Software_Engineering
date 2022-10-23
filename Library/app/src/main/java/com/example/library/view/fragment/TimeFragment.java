package com.example.library.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.Objects;

public class TimeFragment extends Fragment {

    private View view;
    private TextView gold;
    private ImageView timeBtn;
    private ImageView cityName;
    private ImageView cityImg;
    private TextView sumTime;
    private TextView thisTime;

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
                cityName.setImageResource(R.drawable.city_wuhan_name);
                cityImg.setImageResource(R.drawable.city_wuhan_img);
                timeBtn.setImageResource(R.drawable.btn_stop);
                sumTime.setText("06时 06分 08秒");
                thisTime.setText("00:06");
            }
        });
        return view;
    }

}
