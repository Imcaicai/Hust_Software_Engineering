package com.example.library.controller;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.library.R;

import androidx.annotation.NonNull;

public class TipsDialog extends Dialog {
    Context context;
    private TextView btnSure;
    private TextView btnCancle;
    private TextView title;
    private TextView tips;


    public TipsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        initView();
    }

    //初始化
    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_tips, null);
        title = (TextView) view.findViewById(R.id.title);
        tips = (TextView) view.findViewById(R.id.tips);
        btnSure = (TextView) view.findViewById(R.id.dialog_confirm_sure);
        btnCancle = (TextView) view.findViewById(R.id.dialog_confirm_cancle);
        super.setContentView(view);
    }

    public TipsDialog setSurebtnVisible(boolean b){
        if (b == false){
            btnSure.setVisibility(View.GONE);
        }
        return this;
    }

    public TipsDialog setCancelbtnVisible(boolean b){
        if (b == false){
            btnCancle.setVisibility(View.GONE);
        }
        return this;
    }

    public TipsDialog setTile(String s) {
        title.setText(s);
        return this;
    }
    public TipsDialog setMsg(String s) {
        tips.setText(s);
        return this;
    }

    //确定键监听器
    public void setOnSureListener(View.OnClickListener listener) {
        btnSure.setOnClickListener(listener);
    }

    //取消键监听器
    public void setOnCanlceListener(View.OnClickListener listener) {
        btnCancle.setOnClickListener(listener);
    }
}
