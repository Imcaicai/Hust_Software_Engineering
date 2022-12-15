package com.example.library.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.model.adapter.BookGridAdapter;
import com.example.library.controller.BookTableHelper;
import com.example.library.controller.DatabaseHelper;
import com.example.library.model.Book;

import java.util.ArrayList;

public class BookSearchResultActivity extends AppCompatActivity implements View.OnClickListener{
    private GridView gridView;
    private String search;
    private ImageView emptyImg;
    private TextView searchText;
    private ImageView backIcon;
    private ArrayList<Book> bookArrayList;
    private BookTableHelper bookTableHelper = new BookTableHelper();
    final DatabaseHelper dbHelper=new DatabaseHelper(this,"LibraryStore.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_result);

        initView();
        Intent intent = getIntent();
        search = intent.getStringExtra("searchText");
        if (!search.equals("")){
            InitBookGrid();
        }else {
            emptyImg.setImageResource(R.drawable.result_null);
            emptyImg.setVisibility(View.VISIBLE);
        }
        SpannableString spannableString = new SpannableString(search);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#278b69")),0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        searchText.append("|"+spannableString);
    }


    private void initView(){
        gridView=findViewById(R.id.book_gridview);
        searchText=findViewById(R.id.search_text);
        emptyImg=findViewById(R.id.empty_image);
        backIcon=findViewById(R.id.back_btn);
        backIcon.setOnClickListener(this);

        //设置顶部框透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void InitBookGrid(){
        bookArrayList = bookTableHelper.searchBook(dbHelper,search);
        BookGridAdapter adapter = new BookGridAdapter(this,bookArrayList);
        GridView book = findViewById(R.id.book_gridview);
        book.setAdapter(adapter);
        book.setOnItemClickListener(new BookOnClickListener());
        if (bookArrayList.isEmpty()){
            emptyImg.setImageResource(R.drawable.result_null);
            emptyImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }


    private class BookOnClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String bid = bookArrayList.get(position).getId();
            Intent intent = new Intent(BookSearchResultActivity.this, BookActivity1.class);
            intent.putExtra("bid",bid);
            intent.putExtra("startActivityCode","search");
            startActivity(intent);
        }
    }
}