package com.com.blog.bean;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.com.blog.MainActivity;
import com.com.blog.util.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SessionInterceptor implements Interceptor {
//    private LoginUser loginUser;
//    PreferenceManager preferenceManager;
//    Context applicationContext = MainActivity.getContextOfApplication();
//
//    public  SessionInterceptor() {
//        preferenceManager = new PreferenceManager(applicationContext);
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();// 체인 개념
        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader("Authorization", Token.token);

        return chain.proceed(requestBuilder.build());
    }
}
