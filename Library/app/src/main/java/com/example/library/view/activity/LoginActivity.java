package com.example.library.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.library.R;
import com.example.library.utils.AuthorUtils;
import com.example.library.view.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        String token = AuthorUtils.getInstance(this).getToken();
        if(token != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);   //???第二个是什么意思
            startActivity(intent);
        }else{
            //??? add是往container容器里堆加fragment View；replace是将container容器里之前添加的View全部清除，然后再添加当前fragment View
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_fragment, new LoginFragment(),null)
                    .commit();
        }
    }
}