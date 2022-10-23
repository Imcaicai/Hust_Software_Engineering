package com.example.library.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.library.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GoldActivity extends AppCompatActivity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold);

        back = findViewById(R.id.icon_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}