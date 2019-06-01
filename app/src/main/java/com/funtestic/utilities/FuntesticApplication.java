package com.funtestic.utilities;

import android.app.Application;
import android.content.Context;
import java.lang.ref.WeakReference;

public class FuntesticApplication extends Application {

    private static WeakReference<Context> context;

    public void onCreate() {
        super.onCreate();
        FuntesticApplication.context = new WeakReference<Context>(this);
    }

    public static Context getAppContext() {
        return FuntesticApplication.context.get();
    }
}
