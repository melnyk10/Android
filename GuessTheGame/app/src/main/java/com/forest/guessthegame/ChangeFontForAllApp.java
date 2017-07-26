package com.forest.guessthegame;

import android.app.Application;

import com.forest.guessthegame.utils.TypefaceUtil;

public class ChangeFontForAllApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/ubuntu_b.ttf");
    }
}