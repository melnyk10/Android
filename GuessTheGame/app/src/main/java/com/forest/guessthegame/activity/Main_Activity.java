package com.forest.guessthegame.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.forest.guessthegame.R;

public class Main_Activity extends Activity {

    private Button btn_startGame = null;
    private ImageButton btn_changeLanguage = null;
    private ImageButton btn_aboutApp = null;
    private ImageButton btn_soundSwitcher = null;

     ObjectAnimator textColorAnim = null;


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



        btn_startGame = (Button) findViewById(R.id.iBtn_start_game);
        //btn_changeLanguage = (ImageButton) findViewById(R.id.iImgBtn_changeLanguage);
        btn_aboutApp = (ImageButton) findViewById(R.id.iImgBtn_info_about_game);
        btn_soundSwitcher = (ImageButton) findViewById(R.id.iImgBtn_sound_switch);

        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.iBtn_start_game:
                    startActivity(new Intent(v.getContext(), Game_Activity.class));
                    break;
//                case R.id.iImgBtn_changeLanguage:
//                    break;
                case R.id.iImgBtn_info_about_game:
                    startActivity(new Intent(v.getContext(), Info_app_Activity.class));
                    break;
                case R.id.iImgBtn_sound_switch:
                    break;
            }
        };



        btn_startGame.setOnClickListener(onClickListener);
//        btn_changeLanguage.setOnClickListener(onClickListener);
        btn_aboutApp.setOnClickListener(onClickListener);
        btn_soundSwitcher.setOnClickListener(onClickListener);

    }

    public void btnOnClick(View v){

    }
}
