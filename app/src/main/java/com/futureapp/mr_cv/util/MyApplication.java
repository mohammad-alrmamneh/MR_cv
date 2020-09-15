package com.futureapp.mr_cv.util;

import android.app.Application;
import android.content.Context;

import com.futureapp.mr_cv.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MyApplication extends Application {

    public static MyApplication sInstance;

    public static Context context;
    public static TinyDB tinydb;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        context = getApplicationContext();

        tinydb = new TinyDB(this);

        changeDefaultFont();

    }

    public static void changeDefaultFont() {

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/" + "Poppins-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
