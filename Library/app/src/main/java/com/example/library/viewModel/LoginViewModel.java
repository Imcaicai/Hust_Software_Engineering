package com.example.library.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.library.R;
import com.example.library.bean.ChangePasswordBean;
import com.example.library.bean.ChangePasswordBodyBean;
import com.example.library.bean.CreateUserBean;
import com.example.library.bean.LoginBean;
import com.example.library.bean.RegisterBodyBean;
import com.example.library.databinding.FragmentLoginBinding;
import com.example.library.model.LoginModel;
import com.example.library.utils.PasswordUtil;
import com.example.library.view.activity.MainActivity;
import com.example.library.view.fragment.LoginFragment;

public class LoginViewModel {
    public final static String USER_FILE = "user_id";
    public final static String USER_TOKEN = "token";
    public final static String USER_ID = "user_id";
    private final FragmentLoginBinding binding;
    private final LoginModel model;
    private final Activity activity;
    private final Context context;
    private LoginFragment fragment;
    private final SharedPreferences preferences;
    private CountDownTimer timer;   //验证码实现倒计时


    //初始化构造函数
    public LoginViewModel(@NonNull FragmentLoginBinding binding, Activity activity, LoginFragment fragment){
        this.binding = binding;
        this.activity = activity;
        this.fragment = fragment;
        model = new LoginModel();
        context = binding.getRoot().getContext();
        preferences = activity.getSharedPreferences(USER_FILE, Context.MODE_PRIVATE);
    }

    //向后端传递验证码
    /*
    public void sendEmailCaptcha(String personID, Boolean isAlumni){
        model.sendEmailCaptchaBean(personID, isAlumni).observe(fragment, new Observer<SendEmailCaptchaBean>() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onChanged(SendEmailCaptchaBean sendEmailCaptchaBean) {
                if (sendEmailCaptchaBean != null){
                    if (sendEmailCaptchaBean.getSuccess()){
                        Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                        binding.captchaEditor.setBtDrawable(activity.getResources().getDrawable(R.drawable.edit_text_btn_bkg));
                        binding.captchaEditor.setBtEnabled(false);
                        timer.start();
                    }
                }
            }
        });
    }*/

    public void register(String userName, String userId, String password, String userMajor){
        model.registerUser(new RegisterBodyBean(userName, userId, password, userMajor)).observe(fragment, new Observer<CreateUserBean>() {
            @Override
            public void onChanged(CreateUserBean createUserBean) {
                if(createUserBean != null){
                    if(createUserBean.getFlag()){
                        Toast.makeText(context,"注册成功", Toast.LENGTH_SHORT).show();
                        binding.titleText.setText("登录");
                        binding.captchaEditor.setVisibility(View.GONE);
                        binding.nameEditor.setVisibility(View.GONE);
                        binding.majorEditor.setVisibility(View.GONE);
                        binding.forgetPasswordBtn.setVisibility(View.GONE);
                        binding.registerBtn.setVisibility(View.GONE);
                        binding.captchaEditor.setText("");
                        Intent intent = new Intent(fragment.requireActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                    }
                }
            }
        });
    }

    public void login(String userId, String password){
        model.login(userId, password).observe(fragment, new Observer<LoginBean>() {
            @Override
            public void onChanged(LoginBean loginBean) {
                if(loginBean != null){
                    if(loginBean.getFlag()){
                        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(USER_ID, loginBean.getData().getId());
                        editor.putString(USER_TOKEN, loginBean.getData().getToken());
                        editor.apply();
                        Intent intent = new Intent(fragment.requireActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                    }else {
                        Toast.makeText(context, "学号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void changePassword(String userId, String password){
        model.changPassword(new ChangePasswordBodyBean(userId, password)).observe(fragment, new Observer<ChangePasswordBean>() {
            @Override
            public void onChanged(ChangePasswordBean changePasswordBean) {
                if(changePasswordBean != null){
                    if(changePasswordBean.getFlag()){
                        Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                        binding.captchaEditor.setVisibility(View.GONE);
                        binding.forgetPasswordBtn.setVisibility(View.GONE);
                        binding.captchaEditor.setText("");
                        binding.titleText.setText("登录");
                    }else{
                        Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init() {
        binding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (binding.titleText.getText().toString()) {
                    case "注册": {
                        String userId = binding.idEditor.getText().toString();
                        String password = binding.passwordEditor.getText().toString();
                        String captcha = binding.captchaEditor.getText().toString();
                        String userName = binding.nameEditor.getText().toString();
                        String userMajor = binding.majorEditor.getText().toString();
                        if (captcha.isEmpty()) {
                            Toast.makeText(context, "验证码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!isRegisterEmpty()) {
                            register(userName, userId, PasswordUtil.encipherPassword(password), userMajor);
                        }
                        break;
                    }
                    case "登录": {
                        String userId = binding.idEditor.getText().toString();
                        String password = binding.passwordEditor.getText().toString();
                        if (!isLoginEmpty(userId, password)) {
                            login(userId, PasswordUtil.encipherPassword(password));
                        }
                        break;
                    }
                    case "忘记密码": {
                        String userId = binding.idEditor.getText().toString();
                        String password = binding.passwordEditor.getText().toString();
                        String captcha = binding.captchaEditor.getText().toString();
                        if (captcha.length() != 6) {
                            Toast.makeText(context, "验证码不规范", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        });

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.captchaEditor.setBtText(millisUntilFinished / 1000 + "秒后重新获取");
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFinish() {
                binding.captchaEditor.setBtText("获取验证码");
                binding.captchaEditor.setBtEnabled(true);
            }
        };


        binding.forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.passwordEditor.setHint("新密码");
                binding.nameEditor.setVisibility(View.GONE);
                binding.majorEditor.setVisibility(View.GONE);
                binding.forgetPasswordBtn.setVisibility(View.GONE);
                binding.registerBtn.setVisibility(View.GONE);
                binding.titleText.setText("忘记密码");
            }
        });

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.nameEditor.setVisibility(View.VISIBLE);
                binding.majorEditor.setVisibility(View.VISIBLE);
                binding.captchaEditor.setVisibility(View.VISIBLE);
                binding.forgetPasswordBtn.setVisibility(View.GONE);
                binding.registerBtn.setVisibility(View.GONE);
                binding.titleText.setText("注册");
            }
        });


        binding.captchaEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (binding.titleText.getText().toString()){
                    case "注册":{
                        String userName = binding.nameEditor.getText().toString();
                        if(userName.length() > 12){
                            Toast.makeText(context, "用户名最多20个字", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        break;
                    }
                    case "忘记密码":{
                        String password = binding.passwordEditor.getText().toString();
                        String userId = binding.idEditor.getText().toString();

                    }
                }
            }
        });
    }

    private boolean isRegisterEmpty(){
        String userName = binding.nameEditor.getText().toString();
        String password = binding.passwordEditor.getText().toString();
        String userId = binding.idEditor.getText().toString();
        String userMajor = binding.majorEditor.getText().toString();
        if(userName.isEmpty()){
            Toast.makeText(context, "昵称不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }else if(password.isEmpty()){
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }else if(userId.isEmpty()){
            Toast.makeText(context, "学号不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }else if(userMajor.isEmpty()){
            Toast.makeText(context, "学院不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean isLoginEmpty(String userId, String password){
        if(userId.isEmpty()){
            Toast.makeText(context, "学号不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }else if(password.isEmpty()){
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean isChangeEmpty(String userId, String password){
        if(userId.isEmpty()){
            Toast.makeText(context, "学号不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }else if(password.isEmpty()){
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

}
