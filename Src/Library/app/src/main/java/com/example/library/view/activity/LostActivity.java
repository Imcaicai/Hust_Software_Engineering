package com.example.library.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class LostActivity extends AppCompatActivity {
    private ImageView lostImage;
    private TextView lostName;
    private TextView lostTime;
    private TextView lostLocation;
    private TextView lostRemark;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lost);
        Intent intent = getIntent();
        lostImage = findViewById(R.id.lost_info_img);
        lostName = findViewById(R.id.lost_info_name);
        lostTime = findViewById(R.id.lost_info_time);
        lostLocation = findViewById(R.id.lost_info_location);
        lostRemark = findViewById(R.id.lost_info_remark);
        backIcon = findViewById(R.id.icon_lost_back);
        lostName.setText(intent.getStringExtra("lostName"));
        lostTime.setText("捡到时间：" + intent.getStringExtra("lostTime"));
        lostLocation.setText("捡到地点：" + intent.getStringExtra("lostLocation"));
        lostRemark.setText("备注：" + intent.getStringExtra("lostRemark"));
        lostImage.setImageResource(intent.getIntExtra("lostImage",1));

        backIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        //设置顶部框透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
