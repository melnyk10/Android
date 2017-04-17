package com.forest.guessthegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game_over_activity extends Activity {

    private TextView score = null;
    private TextView highScore = null;
    private ImageButton img_btn_restart = null;
    private ImageButton img_btn_goMainAct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        score = (TextView) findViewById(R.id.iScore_game_over);
        highScore = (TextView) findViewById(R.id.iHightScore);
        img_btn_restart = (ImageButton) findViewById(R.id.imgBtn_restart);
        img_btn_goMainAct = (ImageButton) findViewById(R.id.imgBtn_go_main_activity);

        Intent score_intent = getIntent();
        if(score_intent.hasExtra("score")){
            score.setText(score_intent.getExtras().getString("score"));
        }if(score_intent.hasExtra("high_score")){
            highScore.setText(score_intent.getExtras().getString("high_score"));
        }


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imgBtn_restart:
                        Intent restart_int = new Intent(Game_over_activity.this, Game_Activity.class);
                        startActivity(restart_int);
                        break;
                    case R.id.imgBtn_go_main_activity:
                        Intent goMainAct = new Intent(Game_over_activity.this, Main_Activity.class);
                        startActivity(goMainAct);
                        break;
                }
            }
        };
        img_btn_restart.setOnClickListener(onClickListener);
        img_btn_goMainAct.setOnClickListener(onClickListener);
    }
}
