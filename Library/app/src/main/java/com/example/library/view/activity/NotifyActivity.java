package com.example.library.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    }
}
