package com.example.library.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class MapActivity extends AppCompatActivity {
    private ImageView slogan;
    private TextView name;
    private ImageView map;
    private ImageView backIcon;
    private ImageView rotateBtn;
    private int mapId_0;
    private int mapId_1;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        slogan = findViewById(R.id.floor_slogan);
        name = findViewById(R.id.floor_name);
        map = findViewById(R.id.map_img);
        rotateBtn = findViewById(R.id.rotate_btn);
        backIcon = findViewById(R.id.icon_back);
        mapId_0=intent.getIntExtra("mapId_0",1);
        mapId_1=intent.getIntExtra("mapId_1",1);
        name.setText(intent.getStringExtra("floorName"));
        map.setImageResource(mapId_0);
        slogan.setImageResource(intent.getIntExtra("sloganId",1));

        backIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        rotateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag == 0){
                    map.setImageResource(mapId_1);
                    flag = 1;
                }else{
                    map.setImageResource(mapId_0);
                    flag = 0;
                }
            }
        });

    }
}
