package com.example.library.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils<T> {
    private static final String BASE_URL = "http://bookroad.bacor.cn";

    private static final int TIMEOUT = 10;

    private Retrofit retrofit;

    private static RetrofitUtils retrofitUtils;

    private OkHttpClient.Builder builder;

    public RetrofitUtils(){
        init();
    }

    public static RetrofitUtils getInstance(){
        if(retrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils ==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    private void init() {
        //设置超时时间
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    public <T> T create(Class<T> clazz){
        return create(clazz, null);
    }

    public <T> T create(Class<T> clazz, final String token){
        if(token != null){
            //添加拦截器，拦截器是OkHttp当中一个比较强大的机制，可以监视、重写和重试调用请求
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request(); //创建request对象
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization","Bearer"+token)   //重新设置指定header信息
                    .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        retrofit = new Retrofit.Builder()   //创建Retrofit实例
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .baseUrl(BASE_URL)  //设置网络请求的Url地址
                .client(builder.build())
                .build();
        return retrofit.create(clazz);
    }


}
