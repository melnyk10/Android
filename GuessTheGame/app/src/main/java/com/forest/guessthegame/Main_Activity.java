package com.forest.guessthegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main_Activity extends Activity {

    private Button btn_startGame = null;
    private Button btn_about = null;
    private ImageButton btn_changeLanguage = null;
    private ImageButton btn_aboutApp = null;
    private ImageButton btn_soundSwitcher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        btn_startGame = (Button) findViewById(R.id.btn_goTo3thAct);
        btn_about = (Button) findViewById(R.id.iBtn_about);
        btn_changeLanguage = (ImageButton) findViewById(R.id.iImgBtn_changeLanguage);
        btn_aboutApp = (ImageButton) findViewById(R.id.iImgBtn_info_about_game);
        btn_soundSwitcher = (ImageButton) findViewById(R.id.iImgBtn_sound_switch);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_goTo3thAct:
                        startActivity(new Intent(v.getContext(), Game_Activity.class));
                        break;
                    case R.id.iBtn_about:
                        break;
                    case R.id.iImgBtn_changeLanguage:
                        break;
                    case R.id.iImgBtn_info_about_game:
                        break;
                    case R.id.iImgBtn_sound_switch:
                        break;
                }

            }
        };

        btn_startGame.setOnClickListener(onClickListener);
        btn_about.setOnClickListener(onClickListener);
        btn_changeLanguage.setOnClickListener(onClickListener);
        btn_aboutApp.setOnClickListener(onClickListener);
        btn_soundSwitcher.setOnClickListener(onClickListener);

    }
}
