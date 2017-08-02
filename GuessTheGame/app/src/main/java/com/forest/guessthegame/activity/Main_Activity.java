package com.forest.guessthegame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
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


    private Button btn_startGame = null;
    private ImageButton btn_aboutApp = null;
    private ImageButton btn_soundSwitcher = null;

    //audio
    private SharedPreferences sharedPreferences;
    private AudioManager audioManager;
    private boolean sound;
    private int streamVolume;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        sharedPreferences = this.getSharedPreferences(SP_SOUND_OFF_ON, Context.MODE_PRIVATE);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        sound = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) != 0;

        //take stream volume from beginning
        sharedPreferences.edit().putInt(STREAM_VOLUME, audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)).apply();


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
            sound = false;
        }else {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_off);
            sound = true;
        }
        sharedPreferences.edit().putBoolean("sound_on_off", sound).apply();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

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
}
