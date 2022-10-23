package com.example.library.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class BookActivity extends AppCompatActivity {
    private ImageView collect;
    private ImageView cover;
    private TextView name;
    private TextView author;
    private TextView publish;
    private TextView location;
    private TextView id;
    private TextView state;
    private TextView form;
    private TextView ISBN;
    private TextView theme;
    private TextView introduction;
    private ImageView backIcon;
    private ImageView collectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);
        Intent intent = getIntent();
        cover = findViewById(R.id.book_cover);
        name = findViewById(R.id.book_name);
        author = findViewById(R.id.author);
        publish = findViewById(R.id.publish);
        location = findViewById(R.id.book_location);
        id = findViewById(R.id.book_id);
        state = findViewById(R.id.book_state);
        form = findViewById(R.id.book_form);
        ISBN = findViewById(R.id.book_ISBN);
        theme = findViewById(R.id.book_theme);
        introduction = findViewById(R.id.book_introduction);
        backIcon = findViewById(R.id.icon_back);

        name.setText(intent.getStringExtra("name"));
        author.setText(intent.getStringExtra("author"));
        publish.setText(intent.getStringExtra("publish"));
        location.setText(intent.getStringExtra("location"));
        id.setText(intent.getStringExtra("id"));
        state.setText(intent.getStringExtra("state"));
        form.setText(intent.getStringExtra("form"));
        ISBN.setText(intent.getStringExtra("ISBN"));
        theme.setText(intent.getStringExtra("theme"));
        introduction.setText(intent.getStringExtra("introduction"));
        cover.setImageResource(intent.getIntExtra("cover",1));

        backIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

    }
}
