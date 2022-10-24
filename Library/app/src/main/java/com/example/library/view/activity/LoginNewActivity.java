package com.example.library.view.activity;

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
import com.example.library.sharedata.User;

import java.util.ArrayList;

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
        dbHelper=new MyDatabaseHelper(this,"LibraryStore.db",null,1);
        dbHelper.getWritableDatabase(); //创建数据库和表

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                //用户名和密码不能为空
                if(!TextUtils.isEmpty(account)&&!TextUtils.isEmpty(password)){
                    ArrayList<User> userData=dbHelper.getAllUser();
                    boolean flag=false;

                    //查询，后续可改成更优秀的算法
                    for(int i=0;i<userData.size();i++){
                        User user=userData.get(i);
                        //用户名和密码可被查询到
                        if(account.equals(user.getAccount())&&password.equals(user.getPassword())){
                            flag=true;
                            break;
                        }
                    }

                    //判断用户名、密码是否正确
                    if(flag){
                        Toast.makeText(LoginNewActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginNewActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginNewActivity.this, "学号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginNewActivity.this, "学号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),RegisterNewActivity.class);
                startActivity(intent);
            }
        });

    }
}
