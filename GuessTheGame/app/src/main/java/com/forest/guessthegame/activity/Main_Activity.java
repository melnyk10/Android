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
import android.widget.TextView;
import android.widget.Toast;

import com.forest.guessthegame.R;

public class Main_Activity extends Activity {

    private Button btn_startGame = null;
    //private ImageButton btn_changeLanguage = null;
    private ImageButton btn_aboutApp = null;
    private ImageButton btn_soundSwitcher = null;
    boolean sound = true;

    //SharedPreferences sharedPreferences;

    AudioManager audioManager;
    int streamVolume;
    MediaPlayer mplayer;

    public void playAudio() {mplayer.start();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //sharedPreferences = this.getSharedPreferences("audio_off_on", Context.MODE_PRIVATE);

        Toast.makeText(getApplicationContext(), "Act onCreated", Toast.LENGTH_LONG).show();


        btn_startGame = (Button) findViewById(R.id.iBtn_start_game);
        //btn_changeLanguage = (ImageButton) findViewById(R.id.iImgBtn_changeLanguage);
        btn_aboutApp = (ImageButton) findViewById(R.id.iImgBtn_info_about_game);
        btn_soundSwitcher = (ImageButton) findViewById(R.id.iImgBtn_sound_switch);

        mplayer = MediaPlayer.create(this, R.raw.clicks7);

        //sound = sharedPreferences.getBoolean("sound", false);
        //btn_soundSwitcher.setBackgroundResource(sound?R.drawable.btn_sound_on:R.drawable.btn_sound_off);
        //soundOffOn();


        //setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public void btnOnClick(View v){
        switch (v.getId()) {
            case R.id.iBtn_start_game:
                startActivity(new Intent(v.getContext(), Game_Activity.class));
                break;
//                case R.id.iImgBtn_changeLanguage:
//                    break;
            case R.id.iImgBtn_info_about_game:
                playAudio();

                startActivity(new Intent(v.getContext(), Info_app_Activity.class));
                break;
            case R.id.iImgBtn_sound_switch:
                playAudio();

                soundOffOn();

                break;
        }
    }


    private void soundOffOn(){
        if(sound){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 80, 80);
            btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_on);
            sound = false;
            //sharedPreferences.edit().putBoolean("sound", sound).apply();
        }else {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_off);
            sound = true;
            //sharedPreferences.edit().putBoolean("sound", sound).apply();
        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            if(streamVolume > 0){
                btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_on);
            }
            Log.i("Volume", String.valueOf(streamVolume));
            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            if(streamVolume == 0) {
                btn_soundSwitcher.setBackgroundResource(R.drawable.btn_sound_off);
            }
            Log.i("Volume", String.valueOf(streamVolume));
            return true;
        }
        return false;
    }


}
