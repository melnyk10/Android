package com.forest.guessthegame;

import android.app.Activity;
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
import android.widget.SlidingDrawer;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game_Activity extends Activity implements ViewSwitcher.ViewFactory {


    private Button btn_top_left = null;
    private Button btn_top_right = null;
    private Button btn_bottom_left = null;
    private Button btn_bottom_right = null;

    private ImageButton imgBtn_resume = null;
    private ImageButton imgBtn_restart = null;
    private ImageButton imgBtn_quit = null;

    private SlidingDrawer slidingDrawer = null;

    private TextSwitcher score_textSwitcher = null;
    private TextView highScore_textView = null;

    public static final String HIGH_SCORE = "highScore";
    private int score = 0;
    private int highScore;

    private List<Button> buttonsList = new ArrayList<>();

    private ImageSwitcher mBackgroundImage = null;

    private Game_HashMap game_hashMap = new Game_HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);
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

        imgBtn_resume = (ImageButton) findViewById(R.id.iImgBtn_resume_game);
        imgBtn_restart = (ImageButton) findViewById(R.id.iImgBtn_restart_game);
        imgBtn_quit = (ImageButton) findViewById(R.id.iImgBtn_quit_game);

        highScore_textView = (TextView) findViewById(R.id.iHighScore_game_activity);

        slidingDrawer = (SlidingDrawer) findViewById(R.id.gameOptionsSlidingDrawer);

        score_textSwitcher = (TextSwitcher) findViewById(R.id.iScore);
        score_textSwitcher.setInAnimation(inAnimation);
        score_textSwitcher.setOutAnimation(outAnimation);
        score_textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(Game_Activity.this);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setShadowLayer(2, 1, 1, Color.BLACK);
                textView.setTextSize(20);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        });


        buttonsList.add(btn_top_left);
        buttonsList.add(btn_top_right);
        buttonsList.add(btn_bottom_left);
        buttonsList.add(btn_bottom_right);

        mBackgroundImage = (ImageSwitcher) findViewById(R.id.iImage_game_switcher);
        mBackgroundImage.setFactory(this);
        mBackgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slidingDrawer.isOpened()) {
                    slidingDrawer.close();
                }
            }
        });


        mBackgroundImage.setInAnimation(inAnimation);
        mBackgroundImage.setOutAnimation(outAnimation);

        game_hashMap.firstStartRightQuestion();

        randomSetGameNameTOButtonText();
        fillRestTextView();


        // 3 поля яких міняє картинку
        String mDrawableName = game_hashMap.getMapOfAllGame().get(game_hashMap.getAnswer()); // файл cat1.png в папке drawable
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        mBackgroundImage.setImageResource(resID); //картинка при першому запуску


        final SharedPreferences prefs = this.getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);
        highScore = prefs.getInt("high_score", 0);

        highScore_textView.setText(String.valueOf(highScore));



        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void rightAndWrongAnswers(Button button) {
                //if wrong answer
                if (!(button.getText().toString().equals(game_hashMap.getAnswer()))) {
                    button.setBackgroundResource(R.drawable.wrong_btn);
                    if (slidingDrawer.isOpened()) {
                        slidingDrawer.close();
                    }
                    for(Button btn:buttonsList){
                        if(btn.getText().equals(game_hashMap.getAnswer())){
                            btn.setBackgroundResource(R.drawable.correct_btn);
                        }
                    }


                    if (highScore > score) {
                        highScore_textView.setText(String.valueOf(highScore));
                    } else {
                        highScore = score;
                        highScore_textView.setText(String.valueOf(highScore));
                        prefs.edit().putInt("high_score", highScore).apply();
                        Log.e("HifhScore", highScore+"");
                    }

                    Intent game_over_intent = new Intent(Game_Activity.this, Game_over_activity.class);
                    game_over_intent.putExtra("score", String.valueOf(score));
                    game_over_intent.putExtra("high_score", String.valueOf(highScore));
                    startActivity(game_over_intent);
                } else if (button.getText().toString().equals(game_hashMap.getAnswer())) {
                    score += 25;
                    score_textSwitcher.setText(String.valueOf(score));
                    //тимчасовий if
                    if (game_hashMap.getArrayOfKeyHashMap().size() < 4) {
                        reset();
                    }
                    changeImg();
                    if (slidingDrawer.isOpened()) {
                        slidingDrawer.close();
                    }
                }
            }


            @Override
            public void onClick(View v) {
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
                    case R.id.iImgBtn_resume_game:
                        slidingDrawer.close();
                        break;
                    case R.id.iImgBtn_restart_game:
                        reset();
                        slidingDrawer.close();
                        break;
                    case R.id.iImgBtn_quit_game:
                        Intent game_over_intent = new Intent(Game_Activity.this, Game_over_activity.class);
                        game_over_intent.putExtra("score", String.valueOf(score));
                        startActivity(game_over_intent);
                        break;
                }
            }
        };






        btn_top_left.setOnClickListener(onClickListener);
        btn_top_right.setOnClickListener(onClickListener);
        btn_bottom_left.setOnClickListener(onClickListener);
        btn_bottom_right.setOnClickListener(onClickListener);
        imgBtn_resume.setOnClickListener(onClickListener);
        imgBtn_restart.setOnClickListener(onClickListener);
        imgBtn_quit.setOnClickListener(onClickListener);

    }


    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setBackgroundColor(0xFF000000);
        return imageView;
    }


    private void randomSetGameNameTOButtonText() {
        int rand = ((int) (Math.random() * 4));
        buttonsList.get(rand).setText(game_hashMap.getQuestion_NameOfGame());
        game_hashMap.getArrayOfKeyHashMap().remove(game_hashMap.getIndexOfArrayOfKey());
    }

    public void fillRestTextView() {
        Collections.shuffle(game_hashMap.getArrayOfKeyHashMap());
        for (Button btn : buttonsList) {
            if (btn.getText().equals("")) {
                btn.setText(game_hashMap.getArrayOfKeyHashMap().get(0));
                game_hashMap.getArrayOfKeyHashMap().remove(0);
            }
            System.out.println(btn.getText().toString());
        }
        System.out.println("size !!" + game_hashMap.getArrayOfKeyHashMap().size());
    }


    public String returnAnswer() {
        return game_hashMap.getMapOfAllGame().get(game_hashMap.getAnswer()); // файл cat1.png в папке drawable
    }

    // метод в якому міняється текст
    public void changeText() {
        //всім кномпкам-текстам призначаємо пусті поля
        btn_top_left.setText("");
        btn_top_right.setText("");
        btn_bottom_left.setText("");
        btn_bottom_right.setText("");

        game_hashMap.firstStartRightQuestion();
        randomSetGameNameTOButtonText();
        fillRestTextView();
    }

    public void changeImg() {
        changeText();

        String temp = returnAnswer();
        int resID2 = getResources().getIdentifier(temp, "drawable", getPackageName());
        mBackgroundImage.setImageResource(resID2);
        Log.e("Change img", "in change img method == " + temp);
        System.out.println("size after press = " + game_hashMap.getArrayOfKeyHashMap().size());
    }


    public void reset() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        score = 0;
        score_textSwitcher.setText(String.valueOf(0));

        System.out.println("Size in IF = " + game_hashMap.getArrayOfKeyHashMap().size());
        game_hashMap.setArrayOfKeyHashMap(game_hashMap.copyKeyMapToStringListArray());
        changeImg();
    }
}
