package com.example.library.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.controller.DatabaseHelper;
import com.example.library.controller.UserTableHelper;
import com.example.library.controller.TipsDialog;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView registerBtn;
    private ImageView loginBtn;
    private EditText accountEdit;
    private EditText nameEdit;
    private EditText passwordEdit1;
    private EditText passwordEdit2;
    final DatabaseHelper dbHelper=new DatabaseHelper(this,"LibraryStore.db",null,1);
    UserTableHelper userTableHelper=new UserTableHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        InitView();

    }


    private void InitView(){
        accountEdit=findViewById(R.id.account);
        nameEdit=findViewById(R.id.name);
        passwordEdit1=findViewById(R.id.password_1);
        passwordEdit2=findViewById(R.id.password_2);
        loginBtn=findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        registerBtn = findViewById(R.id.login_register_btn);
        registerBtn.setOnClickListener(this);

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
            case R.id.login_register_btn:
                //dialog基本设置
                final TipsDialog tipsDialog =new TipsDialog(this);
                tipsDialog.setCancelable(true);
                tipsDialog.setSurebtnVisible(true);
                tipsDialog.setSurebtnVisible(false);
                tipsDialog.setTile("提示");

                //检查信息是否输入完整
                if(!nameEdit.getText().toString().isEmpty() && !accountEdit.getText().toString().isEmpty()
                        && !passwordEdit1.getText().toString().isEmpty() && !passwordEdit2.getText().toString().isEmpty()){
                    String password1=passwordEdit1.getText().toString();
                    String password2=passwordEdit2.getText().toString();
                    String name=nameEdit.getText().toString();
                    String account=accountEdit.getText().toString();
                    //检查两次输入密码是否一致
                    if(password1.equals(password2)){    //两次密码输入一致
                        //检查学号是否已存在
                        boolean accountCheck=userTableHelper.CheckUserAccount(dbHelper,account);
                        if(accountCheck){   //学号未被注册
                            userTableHelper.Register(dbHelper,name,account,password1);
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            finish();
                        }else { //学号已被注册
                            tipsDialog.setMsg("该学号已被注册！");
                            tipsDialog.show();
                        }
                    }else { //两次密码输入不一致
                        tipsDialog.setMsg("两次密码输入不一致！");
                        tipsDialog.show();
                    }
                }else { //信息输入不完整
                    tipsDialog.setMsg("请输入完整信息！");
                    tipsDialog.show();
                }
                break;

            case R.id.login_btn:
                finish();
        }
    }
}
