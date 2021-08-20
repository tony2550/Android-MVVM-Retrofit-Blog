package com.com.blog.bean;

import android.app.Application;
import android.content.Context;

public class BlogApp extends Application {
    private static BlogApp instance;

    public static BlogApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
