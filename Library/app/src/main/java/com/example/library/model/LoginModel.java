package com.example.library.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.library.bean.ChangePasswordBean;
import com.example.library.bean.ChangePasswordBodyBean;
import com.example.library.bean.CreateUserBean;
import com.example.library.bean.LoginBean;
import com.example.library.bean.RegisterBodyBean;
import com.example.library.inter.RetrofitInterface;
import com.example.library.utils.RetrofitUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel {
    private RetrofitInterface request;

    public LoginModel(){
        request = (RetrofitInterface) RetrofitUtils.getInstance().create(RetrofitInterface.class);
    }

    public MutableLiveData<CreateUserBean> registerUser(RegisterBodyBean bean){
        MutableLiveData<CreateUserBean> data = new MutableLiveData<>();
        JSONObject root = new JSONObject();
        try{
            root.put("user_name", bean.getUserName());
            root.put("password", bean.getPassword());
            root.put("user_major", bean.getUserMajor());
            root.put("user_id", bean.getUserId());
        }catch (JSONException e){
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), root.toString());
        Call<CreateUserBean> call = request.registerUser(body);
        call.enqueue(new Callback<CreateUserBean>() {
            @Override
            public void onResponse(Call<CreateUserBean> call, Response<CreateUserBean> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CreateUserBean> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<LoginBean> login(String userId, String password){
        MutableLiveData<LoginBean> data = new MutableLiveData<>();
        Call<LoginBean> call = request.login(userId, password);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.postValue(response.body());
                }
                Log.i("","success");
                Log.i("", "okHttpPost enqueue: \n onResponse:" + response.toString() + "\n body:" + response.body());
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<ChangePasswordBean> changPassword(ChangePasswordBodyBean bean){
        MutableLiveData<ChangePasswordBean> data = new MutableLiveData<>();
        JSONObject body = new JSONObject();
        try{
            body.put("user_id", bean.getUserId());
            body.put("password", bean.getPassword());
        }catch (JSONException e){
            e.printStackTrace();
        }
        RequestBody bodyData = RequestBody.create(MediaType.parse("application/json"), body.toString());
        Call<ChangePasswordBean> call = request.changePassword(bodyData);
        call.enqueue(new Callback<ChangePasswordBean>() {
            @Override
            public void onResponse(Call<ChangePasswordBean> call, Response<ChangePasswordBean> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordBean> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }
}
