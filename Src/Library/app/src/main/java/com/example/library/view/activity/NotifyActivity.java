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

public class NotifyActivity extends AppCompatActivity {
    private ImageView notifyCover;
    private ImageView notifyLabel;
    private TextView notifyStart;
    private TextView notifyEnd;
    private TextView notifyLocation;
    private TextView notifyName;
    private TextView notifyIntroduction;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notify);
        Intent intent = getIntent();
        notifyCover = findViewById(R.id.notify_cover_info);
        notifyLabel = findViewById(R.id.notify_label_info);
        notifyName = findViewById(R.id.notify_name_info);
        notifyStart = findViewById(R.id.start_time_info);
        notifyEnd = findViewById(R.id.end_time_info);
        notifyLocation = findViewById(R.id.notify_location_info);
        notifyIntroduction = findViewById(R.id.notify_introduction_info);
        backIcon = findViewById(R.id.icon_back);
        notifyName.setText(intent.getStringExtra("notifyName"));
        notifyStart.setText(intent.getStringExtra("notifyStart"));
        notifyEnd.setText(intent.getStringExtra("notifyEnd"));
        notifyLocation.setText(intent.getStringExtra("notifyLocation"));
        notifyIntroduction.setText(intent.getStringExtra("notifyIntroduction"));
        notifyCover.setImageResource(intent.getIntExtra("notifyCover",1));
        notifyLabel.setImageResource(intent.getIntExtra("notifyLabel",1));

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
