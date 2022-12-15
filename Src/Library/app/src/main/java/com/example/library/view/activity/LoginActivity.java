package com.example.library.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.controller.DatabaseHelper;
import com.example.library.controller.UserTableHelper;
import com.example.library.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView loginBtn;
    private ImageView registerBtn;
    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox save;
    private ArrayList<User> userArrayList;
    final DatabaseHelper dbHelper=new DatabaseHelper(this,"LibraryStore.db",null,1);
    UserTableHelper userTableHelper=new UserTableHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        initView();
        initAccount();

    }


    //初始化控件
    private void initView(){
        accountEdit=findViewById(R.id.account);
        passwordEdit=findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(this);
        save=findViewById(R.id.save_user);

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
            case R.id.login_btn:
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                //用户名和密码不能为空
                if(!TextUtils.isEmpty(account)&&!TextUtils.isEmpty(password)){
                    boolean loginResult=userTableHelper.Login(dbHelper,account,password);
                    if(loginResult){    //学号密码正确
                        userArrayList=userTableHelper.UserList(dbHelper,account);
                        String name=userArrayList.get(0).getName();
                        String uid=userArrayList.get(0).getId();
                        saveUp(uid,account,name,password);
                        Toast.makeText(this,"登陆成功！",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "学号或密码错误", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this, "学号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.register_btn:
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }


    /**
     * 保存用户登录状态
     * @param id
     * @param account
     * @param name
     * @param password
     */
    private void saveUp(String id,String account,String name,String password){
        SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("uid",id);
        editor.putString("name",name);
        editor.putString("account",account);
        editor.putString("password",password);
        editor.commit();
        if(save.isChecked()){
            editor.putBoolean("saveState",true);
        }
    }

    /**
     * 初始化用户登录状态
     */
    private void initAccount(){
        SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        String account=sharedPreferences.getString("account","null");
        String password=sharedPreferences.getString("password","null");
        boolean saveState=sharedPreferences.getBoolean("saveState",false);

        if(saveState){
            accountEdit.setText(account);
            passwordEdit.setText(password);
            save.setChecked(true);
        }else {
            save.setChecked(false);
        }

    }
}
