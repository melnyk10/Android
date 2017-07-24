package com.forest.guessthegame;

import android.app.Application;

import com.forest.guessthegame.utils.TypefaceUtil;

/**
 * Created by forest on 24.07.2017.
 */

public class ChangeFontForAllApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/ubuntu_b.ttf");
    }
}