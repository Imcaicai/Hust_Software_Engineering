package com.example.library.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.controller.BookTableHelper;
import com.example.library.controller.BorrowTableHelper;
import com.example.library.controller.DatabaseHelper;
import com.example.library.controller.GetTime;
import com.example.library.controller.UserTableHelper;
import com.example.library.model.Book;
import com.example.library.model.Borrow;
import com.example.library.controller.TipsDialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BookActivity1 extends AppCompatActivity implements View.OnClickListener{
    private ImageView cover;
    private TextView name;
    private TextView author;
    private TextView publish;
    private TextView location;
    private TextView state;
    private TextView ISBN;
    private TextView theme;
    private TextView introduction;
    private ImageView backIcon;
    private Button collectBtn;
    private Button collectedBtn;
    private Button borrowBtn;
    private Button borrowedBtn;
    private String bid;
    private String uid;
    private String collections;
    private BookTableHelper bookTableHelper = new BookTableHelper();
    private UserTableHelper userTableHelper =new UserTableHelper();
    private BorrowTableHelper borrowTableHelper=new BorrowTableHelper();
    private ArrayList<Book> bookArrayList;
    final DatabaseHelper dbHelper=new DatabaseHelper(this,"LibraryStore.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();

        Intent intent = getIntent();
        bid = intent.getStringExtra("bid");
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","-1");

        collectedBtn.setVisibility(View.GONE);
        collectBtn.setVisibility(View.VISIBLE);


        bookArrayList = bookTableHelper.getBookDetails(dbHelper,bid);
        name.setText(bookArrayList.get(0).getName());
        author.setText(bookArrayList.get(0).getAuthor());
        theme.setText("主题："+bookArrayList.get(0).getTheme());
        state.setText("图书状态："+bookArrayList.get(0).getState());
        introduction.setText("简介：" + bookArrayList.get(0).getIntroduction());
        publish.setText(bookArrayList.get(0).getPublish());
        ISBN.setText("ISBN："+bookArrayList.get(0).getISBN());
        location.setText("馆内位置："+bookArrayList.get(0).getLocation());
        cover.setImageBitmap(getAssetsBitmap(this,bookArrayList.get(0).getCover()));
        //cover.setImageResource(intent.getIntExtra("cover",1));
        if (bookArrayList.get(0).getState().equals("在馆内")){
            borrowBtn.setVisibility(View.VISIBLE);
            borrowedBtn.setVisibility(View.GONE);
        }else {
            borrowedBtn.setVisibility(View.VISIBLE);
            borrowBtn.setVisibility(View.GONE);
        }

    }


    public void initView(){
        //绑定控件
        cover = findViewById(R.id.book_cover);
        name = findViewById(R.id.book_name);
        author = findViewById(R.id.author);
        publish = findViewById(R.id.publish);
        location = findViewById(R.id.book_location);
        state = findViewById(R.id.book_state);
        ISBN = findViewById(R.id.book_ISBN);
        theme = findViewById(R.id.book_theme);
        introduction = findViewById(R.id.book_introduction);
        backIcon = findViewById(R.id.icon_back);
        backIcon.setOnClickListener(this);
        collectBtn=findViewById(R.id.collect);
        collectBtn.setOnClickListener(this);
        collectedBtn=findViewById(R.id.collected);
        collectedBtn.setOnClickListener(this);
        borrowBtn=findViewById(R.id.borrow);
        borrowBtn.setOnClickListener(this);
        borrowedBtn=findViewById(R.id.borrowed);

        //设置顶部框透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.borrow:
                final TipsDialog tipsDialog = new TipsDialog(this);
                tipsDialog.setTile("提示").setMsg("是否确定借阅此书。书本借阅后需在一个月之内归还。")
                        .setOnCanlceListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tipsDialog.dismiss();
                            }
                        });
                tipsDialog.setOnSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bookTableHelper.borrowBook(dbHelper,bid);
                        Borrow borrow = new Borrow();
                        borrow.setBookId(bid);
                        borrow.setUserId(uid);
                        borrow.setState("已被借阅");
                        GetTime getTime = new GetTime();
                        borrow.setBoTime(getTime.BorrowTime());
                        borrowTableHelper.AddBorrow(dbHelper,borrow);
                        tipsDialog.dismiss();
                        finish();
                    }
                });
                tipsDialog.show();
                break;
            case R.id.collected:
                String uncollection = "/"+bid+"/";
                String new_collection = collections.replace(uncollection,"");
                userTableHelper.CollectBook(dbHelper,uid,new_collection);
                collectedBtn.setVisibility(View.GONE);
                collectBtn.setVisibility(View.VISIBLE);
                Toast.makeText(BookActivity1.this,"取消收藏成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.collect:
                String collection = "/"+bid+"/";
                userTableHelper.CollectBook(dbHelper,uid,collections+collection);
                collectBtn.setVisibility(View.GONE);
                collectedBtn.setVisibility(View.VISIBLE);
                Toast.makeText(BookActivity1.this,"收藏成功",Toast.LENGTH_SHORT).show();
                break;

            case R.id.icon_back:
                finish();
                break;
        }
    }


    /**
     * 从asset文件夹中读取图片
     * @param context
     * @param path
     * @return
     */
    public static Bitmap getAssetsBitmap(Context context, String path) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();

        try {
            InputStream is = am.open(path);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(am.toString());
        }
        return image;
    }
}
