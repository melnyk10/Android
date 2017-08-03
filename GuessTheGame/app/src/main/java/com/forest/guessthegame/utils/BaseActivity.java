package com.forest.guessthegame.utils;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.forest.guessthegame.R;

public class BaseActivity extends Activity {
    private MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        player = MediaPlayer.create(this, R.raw.btn_click);
    }

    protected void playSound(){
        player.start();
    }
    protected void stopSound(){
        player.stop();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i("status", "onPause");
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i("status", "onStop");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.i("status", "onRestart");
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i("status", "onStart");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("status", "onResume");
//    }


}
