package com.forest.guessthegame.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.forest.guessthegame.R;
import com.forest.guessthegame.utils.BaseActivity;


public class Main_Activity extends BaseActivity {

    private static final String STREAM_VOLUME = "stream_volume";
    private static final String SP_SOUND_OFF_ON = "audio_off_on";
    private static final String SOUND_BOOL = "sound_bool";


    private Button btn_startGame = null;
    private ImageButton btn_aboutApp = null;
    private ImageButton btn_soundSwitcher = null;

    //audio
    private SharedPreferences sharedPreferences;
    private AudioManager audioManager;
    private boolean sound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        sharedPreferences = this.getSharedPreferences(SP_SOUND_OFF_ON, Context.MODE_PRIVATE);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //sound = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) != 0;


        btn_startGame = (Button) findViewById(R.id.iBtn_start_game);
        btn_aboutApp = (ImageButton) findViewById(R.id.iImgBtn_info_about_game);
        btn_soundSwitcher = (ImageButton) findViewById(R.id.iImgBtn_sound_switch);

    }

    public void btnOnClick(View v){
        playSound();

        switch (v.getId()) {
            case R.id.iBtn_start_game:
                startActivity(new Intent(v.getContext(), Game_Activity.class));
                break;
            case R.id.iImgBtn_info_about_game:
                startActivity(new Intent(v.getContext(), Info_app_Activity.class));
                break;
            case R.id.iImgBtn_sound_switch:
                soundOffOn();
                break;
        }

    }


    private void soundOffOn(){
        int volume = sharedPreferences.getInt(STREAM_VOLUME, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)/2);
        if(sound){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, volume);
            btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_on);
            sound = true;
        }else {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_off);
            sound = false;
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            if(streamVolume > 0){
                btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_on);
            }
            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            if(streamVolume == 0) {
                btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_off);
            }
            return true;
        }
        return false;
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE || audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
//            btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_off);
//            sound = false;
//        }
//        boolean temp = sharedPreferences.getBoolean(SOUND_BOOL, false);
//        btn_soundSwitcher.setBackgroundResource(temp?R.drawable.btn_sound_on:R.drawable.btn_sound_off);
//        Log.i("status", "onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        sharedPreferences.edit().putBoolean(SOUND_BOOL, sound).apply();
//        Log.i("status", "onPause");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("status", "onDestroy");
//    }
}
