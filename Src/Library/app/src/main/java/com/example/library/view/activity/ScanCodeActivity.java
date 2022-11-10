package com.example.library.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.library.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScanCodeActivity extends AppCompatActivity {

    private ImageView borrowBook;
    private ImageView returnBook;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

        borrowBook = findViewById(R.id.borrow_book_btn);
        borrowBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                IntentIntegrator intentIntegrator = new IntentIntegrator(ScanCodeActivity.this);
                // 开始扫描
                intentIntegrator.initiateScan();
            }
        });

        returnBook = findViewById(R.id.return_book_btn);
        returnBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                IntentIntegrator intentIntegrator = new IntentIntegrator(ScanCodeActivity.this);
                intentIntegrator.setPrompt("这里是二维码扫描界面");
                // 开始扫描
                intentIntegrator.initiateScan();
            }
        });


        backIcon=findViewById(R.id.back_btn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "取消扫描", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描内容:" + result.getContents(), Toast.LENGTH_LONG).show();
                String bid=result.getContents();
                Intent intent = new Intent(ScanCodeActivity.this,BookActivity.class);
                intent.putExtra("bid",bid);
                startActivity(intent);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}