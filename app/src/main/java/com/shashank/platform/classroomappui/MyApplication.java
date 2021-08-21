package com.shashank.platform.classroomappui;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    private static MyApplication singleton;

    public MyApplication() {
        singleton = this;
    }

    public static MyApplication getInstance() {
        return singleton;
    }

    public static Context getAppContext() {
        if (singleton == null) {
            singleton = new MyApplication();
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
