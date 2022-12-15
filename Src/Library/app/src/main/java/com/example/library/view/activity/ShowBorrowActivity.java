package com.example.library.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.library.R;
import com.example.library.model.adapter.BorrowListAdapter;
import com.example.library.controller.BorrowTableHelper;
import com.example.library.controller.DatabaseHelper;
import com.example.library.model.Borrow;

import java.util.ArrayList;

public class ShowBorrowActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView isnull;
    private ImageView backIcon;
    private ListView listView;
    private String uid;
    private ArrayList<Borrow> arrayList;
    private BorrowTableHelper borrowTableHelper=new BorrowTableHelper();
    final DatabaseHelper dbHelper=new DatabaseHelper(this,"LibraryStore.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_borrow);
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","-1");
        initView();
        InitList();
    }


    public void initView(){
        listView=findViewById(R.id.borrow_list);
        backIcon=findViewById(R.id.back_btn);
        backIcon.setOnClickListener(this);
        isnull=findViewById(R.id.isnull);

        //设置顶部框透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }


    private void InitList(){
        arrayList = borrowTableHelper.ShowBorrow(dbHelper,uid);
        if (!arrayList.isEmpty()){
            isnull.setVisibility(View.INVISIBLE);
        }
        BorrowListAdapter adapter = new BorrowListAdapter(this,arrayList);
        ListView book = findViewById(R.id.borrow_list);
        book.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }
}