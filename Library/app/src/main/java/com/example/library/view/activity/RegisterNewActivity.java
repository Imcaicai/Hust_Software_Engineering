package com.example.library.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.sharedata.MyDatabaseHelper;

public class RegisterNewActivity extends AppCompatActivity {
    private ImageView login;
    private EditText accountEdit;
    private EditText nameEdit;
    private EditText passwordEdit;
    private MyDatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        dbHelper=new MyDatabaseHelper(this,"LibraryStore.db",null,1);

        accountEdit=findViewById(R.id.account);
        nameEdit=findViewById(R.id.name);
        passwordEdit=findViewById(R.id.password);
        login = findViewById(R.id.login_register_btn);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account=accountEdit.getText().toString().trim();
                String name=nameEdit.getText().toString().trim();
                String password=passwordEdit.getText().toString().trim();
                if(!TextUtils.isEmpty(account)&&!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    dbHelper.addUser(account,name,password);
                    finish();
                    Toast.makeText(RegisterNewActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RegisterNewActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
