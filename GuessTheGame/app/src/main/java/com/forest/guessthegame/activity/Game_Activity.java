package com.forest.guessthegame.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.forest.guessthegame.Game_HashMap;
import com.forest.guessthegame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game_Activity extends Activity{

    private Game_HashMap game_hashMap = new Game_HashMap();

    private Button btn_top_left = null;
    private Button btn_top_right = null;
    private Button btn_bottom_left = null;
    private Button btn_bottom_right = null;

    private ImageSwitcher mBackgroundImage = null;

    private ImageButton imgBtn_restart = null;
    private ImageButton imgBtn_quit = null;

    private TextSwitcher score_textSwitcher = null;

    private TextView highScore_textView = null;

    public static final String HIGH_SCORE = "high_score";
    public static final String SCORE = "score";

    private int score = 0;
    private int highScore;

    private List<Button> buttonsList = new ArrayList<>();

    private Intent game_over_intent = null;

    private SharedPreferences prefs = null;

    AlertDialog.Builder builder = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(700);
        final Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(700);

        btn_top_left = (Button) findViewById(R.id.iBtn_top_left);
        btn_top_right = (Button) findViewById(R.id.iBtn_top_right);
        btn_bottom_left = (Button) findViewById(R.id.iBtn_bottom_left);
        btn_bottom_right = (Button) findViewById(R.id.iBtn_bottom_right);

        imgBtn_restart = (ImageButton) findViewById(R.id.iImgBtn_restart_game);
        imgBtn_quit = (ImageButton) findViewById(R.id.iImgBtn_quit_game);

        highScore_textView = (TextView) findViewById(R.id.iHighScore_game_activity);

        mBackgroundImage = (ImageSwitcher) findViewById(R.id.iImage_game_switcher);
        mBackgroundImage.setFactory(()->{
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundColor(0xFF000000);
            return imageView;
        });

        score_textSwitcher = (TextSwitcher) findViewById(R.id.iScore);
        score_textSwitcher.setInAnimation(inAnimation);
        score_textSwitcher.setOutAnimation(outAnimation);
        score_textSwitcher.setFactory(() -> {
            TextView textView = new TextView(Game_Activity.this);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setShadowLayer(2, 1, 1, Color.BLACK);
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            return textView;
        });


        buttonsList.add(btn_top_left);
        buttonsList.add(btn_top_right);
        buttonsList.add(btn_bottom_left);
        buttonsList.add(btn_bottom_right);

        mBackgroundImage.setInAnimation(inAnimation);
        mBackgroundImage.setOutAnimation(outAnimation);


        prefs = this.getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);
        highScore = prefs.getInt(HIGH_SCORE, 0);
        highScore_textView.setText(String.valueOf(highScore));


        game_over_intent = new Intent(Game_Activity.this, Game_over_activity.class);

        builder = new AlertDialog.Builder(Game_Activity.this);


        changeImg();

    }

    public void btnClick(View v){
        switch (v.getId()) {
            case R.id.iBtn_bottom_left:
                rightAndWrongAnswers(btn_bottom_left);
                break;
            case R.id.iBtn_bottom_right:
                rightAndWrongAnswers(btn_bottom_right);
                break;
            case R.id.iBtn_top_left:
                rightAndWrongAnswers(btn_top_left);
                break;
            case R.id.iBtn_top_right:
                rightAndWrongAnswers(btn_top_right);
                break;
            case R.id.iImgBtn_restart_game:
                // забрати спливаючі кнопки блеать
                youSure(v);
                break;
            case R.id.iImgBtn_quit_game:
                youSure(v);
        }
    }

    private void rightAndWrongAnswers(Button button) {
        //if wrong answer
        if (!(button.getText().toString().equals(game_hashMap.getAnswer()))) {
            button.setBackgroundResource(R.drawable.wrong_btn);

            for(Button btn:buttonsList){
                if(btn.getText().equals(game_hashMap.getAnswer())){
                    btn.setBackgroundResource(R.drawable.correct_btn);
                }
            }


            highScore_condition();

            game_over_intent.putExtra(SCORE, String.valueOf(score));
            game_over_intent.putExtra(HIGH_SCORE, String.valueOf(highScore));
            startActivity(game_over_intent);

        } else if (button.getText().toString().equals(game_hashMap.getAnswer())) {
            score += 25;
            score_textSwitcher.setText(String.valueOf(score));

            changeImg();

            //тимчасовий if
            if (game_hashMap.getArrayOfKeyHashMap().size() < 4) {
                reset();
            }


        }
    }

    private void randomSetGameNameTOButtonText() {
        int rand = ((int) (Math.random() * 4));
        buttonsList.get(rand).setText(game_hashMap.getQuestion_NameOfGame());
        game_hashMap.getArrayOfKeyHashMap().remove(game_hashMap.getIndexOfArrayOfKey());
    }

    private void fillRestTextView() {
        Collections.shuffle(game_hashMap.getArrayOfKeyHashMap());
        for (Button btn : buttonsList) {
            if (btn.getText().equals("")) {
                btn.setText(game_hashMap.getArrayOfKeyHashMap().get(0));
                game_hashMap.getArrayOfKeyHashMap().remove(0);
            }
            System.out.println(btn.getText().toString());
        }
    }

    private String returnAnswer() {
        return game_hashMap.getMapOfAllGame().get(game_hashMap.getAnswer());
    }

    private void changeText() {
        btn_top_left.setText("");
        btn_top_right.setText("");
        btn_bottom_left.setText("");
        btn_bottom_right.setText("");

        game_hashMap.firstStartRightQuestion();
        randomSetGameNameTOButtonText();
        fillRestTextView();
    }

    private void changeImg() {
        changeText();

        String picName = returnAnswer();
        int resID2 = getResources().getIdentifier(picName, "drawable", getPackageName());
        mBackgroundImage.setImageResource(resID2);
    }

    private void reset() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        score = 0;
        score_textSwitcher.setText(String.valueOf(0));

        game_hashMap.setArrayOfKeyHashMap(game_hashMap.copyKeyMapToStringListArray());
        changeImg();
    }

    private void youSure(View v){
        String question = (v.getId()==R.id.iImgBtn_restart_game) ? "Restart game?" : "Quit game?";
            builder.setMessage(question)
                    .setPositiveButton("yes", (dialog, id) -> {
                        // FIRE ZE MISSILES!
                        if(v.getId() == R.id.iImgBtn_restart_game){
                            highScore_condition();
                            reset();// якщо ресет то очки не зараховуються ?
                        }
                        else if(v.getId() == R.id.iImgBtn_quit_game){
                            highScore_condition();
                            game_over_intent.putExtra(SCORE, String.valueOf(score));
                            game_over_intent.putExtra(HIGH_SCORE, String.valueOf(highScore));
                            startActivity(game_over_intent);
                        }
                    })
                    .setNegativeButton("no", (dialog, id) -> {
                        // User cancelled the dialog
                    });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void highScore_condition(){
        if (highScore > score) {
            highScore_textView.setText(String.valueOf(highScore));
        } else {
            highScore = score;
            highScore_textView.setText(String.valueOf(highScore));
            prefs.edit().putInt(HIGH_SCORE, highScore).apply();
            Log.e("HighScore", highScore+"");
        }
    }
}
