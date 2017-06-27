package com.forest.guessthegame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.forest.guessthegame.R;

import static com.forest.guessthegame.activity.Game_Activity.HIGH_SCORE;
import static com.forest.guessthegame.activity.Game_Activity.SCORE;

public class Game_over_activity extends Activity {

    private TextView score = null;
    private TextView highScore = null;
    private ImageButton img_btn_restart = null;
    private ImageButton img_btn_goMainAct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        score = (TextView) findViewById(R.id.iScore_game_over);
        highScore = (TextView) findViewById(R.id.iHighScore_game_over_activity);
        img_btn_restart = (ImageButton) findViewById(R.id.iImgBtn_restart);
        img_btn_goMainAct = (ImageButton) findViewById(R.id.iImgBtn_go_main_activity);

        Intent score_intent = getIntent();
        if(score_intent.hasExtra(SCORE)){
            score.setText(score_intent.getExtras().getString(SCORE));
        }if(score_intent.hasExtra(HIGH_SCORE)){
            highScore.setText(score_intent.getExtras().getString(HIGH_SCORE));
        }


        View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.iImgBtn_restart:
                    startActivity(new Intent(Game_over_activity.this, Game_Activity.class));
                    break;
                case R.id.iImgBtn_go_main_activity:
                    startActivity(new Intent(Game_over_activity.this, Main_Activity.class));
                    break;
            }
        };
        img_btn_restart.setOnClickListener(onClickListener);
        img_btn_goMainAct.setOnClickListener(onClickListener);
    }

}
