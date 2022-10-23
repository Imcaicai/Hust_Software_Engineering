package com.example.library.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.sharedata.MyDatabaseHelper;

public class LoginNewActivity extends AppCompatActivity {
    private ImageView loginBtn;
    private ImageView registerBtn;
    private EditText accountEdit;
    private EditText passwordEdit;
    private MyDatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        accountEdit=findViewById(R.id.account);
        passwordEdit=findViewById(R.id.password);
        //构建一个MyDatabaseHelper对象，将数据库名指定为User.db，版本号指定为1
        dbHelper=new MyDatabaseHelper(this,"UserStore.db",null,1);

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                if(account.equals("admin")&&password.equals(toString())){
                    Intent intent = new Intent(v.getContext(),MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginNewActivity.this, "学号或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),RegisterNewActivity.class);
                startActivity(intent);

                dbHelper.getWritableDatabase();
            }
        });

    }
}
