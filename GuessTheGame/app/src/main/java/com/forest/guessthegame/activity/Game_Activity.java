package com.forest.guessthegame.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.forest.guessthegame.DB_games_info;
import com.forest.guessthegame.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game_Activity extends Activity {
    DB_games_info db_gamesInfo;

    private Button btn_top_left = null;
    private Button btn_top_right = null;
    private Button btn_bottom_left = null;
    private Button btn_bottom_right = null;

    private ImageSwitcher mBackgroundImage = null;

    private ImageButton imgBtn_restart = null;
    private ImageButton imgBtn_quit = null;

    private TextSwitcher iTS_score = null;

    public static final String HIGH_SCORE = "high_score";
    public static final String SCORE = "score";
    public static final String LIST_IDS = "list_of_ids";
    public static final String RIGHT_ANSWER = "right_answer";

    private int score = 0;
    private int highScore;
    int randCorrectBtn = 0;

    private List<Button> buttonsList = new ArrayList<>();

    private Intent game_over_intent = null;

    private SharedPreferences prefs = null;

    AlertDialog.Builder builder = null;

    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;
    private MediaPlayer clickSound;

    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        db_gamesInfo = new DB_games_info(this);

        final Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(700);
        final Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(700);

        correctSound = MediaPlayer.create(this, R.raw.correct_answer);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_answer);
        clickSound = MediaPlayer.create(this, R.raw.btn_click);


        btn_top_left = (Button) findViewById(R.id.iBtn_top_left);
        btn_top_right = (Button) findViewById(R.id.iBtn_top_right);
        btn_bottom_left = (Button) findViewById(R.id.iBtn_bottom_left);
        btn_bottom_right = (Button) findViewById(R.id.iBtn_bottom_right);

        imgBtn_restart = (ImageButton) findViewById(R.id.iImgBtn_restart_game);
        imgBtn_quit = (ImageButton) findViewById(R.id.iImgBtn_quit_game);

        //highScore_textView = (TextView) findViewById(R.id.iHighScore_game_activity);

        mBackgroundImage = (ImageSwitcher) findViewById(R.id.iImage_game_switcher);
        mBackgroundImage.setFactory(() -> {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundColor(0xFF000000);
            return imageView;
        });

        iTS_score = (TextSwitcher) findViewById(R.id.iTS_score);
        iTS_score.setInAnimation(inAnimation);
        iTS_score.setOutAnimation(outAnimation);
        iTS_score.setFactory(() -> {
            TextView textView = new TextView(Game_Activity.this);
            //textView.setBackgroundResource(R.drawable.small_points);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER);
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
        //highScore_textView.setText(String.valueOf(highScore));


        game_over_intent = new Intent(Game_Activity.this, Game_over_activity.class);

        builder = new AlertDialog.Builder(Game_Activity.this);

        changeImg();
    }


    public void btnClick(View v) {
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
                playSound(clickSound);
                youSure(v);
                break;
            case R.id.iImgBtn_quit_game:
                playSound(clickSound);
                youSure(v);
                break;
        }
    }

    private void rightAndWrongAnswers(Button button) {
        //if wrong answer
        if (!(button.getText().toString().equals(db_gamesInfo.getAnswer()))) {
            button.setBackgroundResource(R.drawable.wrong_btn);
            playSound(wrongSound);
            buttonsList.get(randCorrectBtn).setBackgroundResource(R.drawable.correct_btn);

            highScore_condition();

            game_over_intent.putExtra(SCORE, String.valueOf(score));
            game_over_intent.putExtra(HIGH_SCORE, String.valueOf(highScore));
            startActivity(game_over_intent);

        } else if (button.getText().toString().equals(db_gamesInfo.getAnswer())) {
            if (correctSound.isPlaying()) {
                correctSound.stop();
                correctSound = MediaPlayer.create(this, R.raw.correct_answer);
            }
            playSound(correctSound);
            timerForRightBtn();
            score += 25;

            changeImg();
        }

        //temp if
        if (db_gamesInfo.getIdsListOfDB().size() < 4) {
            reset();
        }
    }

    private void changeText() {
        btn_top_left.setText("");
        btn_top_right.setText("");
        btn_bottom_left.setText("");
        btn_bottom_right.setText("");

        db_gamesInfo.getNewIndexForImg();
        fillTextView();
    }

    private void fillTextView() {
        //pic random btn for correct answer
        randCorrectBtn = ((int) (Math.random() * 4));
        buttonsList.get(randCorrectBtn).setText(db_gamesInfo.getAnswer());
        db_gamesInfo.getIdsListOfDB().remove(Short.valueOf(db_gamesInfo.getID())); // remove Short obj. not index !!

        //pic random img and remove from ArrayList. than they don't repeat them self
        Collections.shuffle(db_gamesInfo.getIdsListOfDB());
        for (Button btn : buttonsList) {
            if (btn.getText().equals("")) {
                btn.setText(db_gamesInfo.getName(db_gamesInfo.getIdsListOfDB().get(0)));
                db_gamesInfo.getIdsListOfDB().remove(0);
            }
        }
    }

    private Drawable getDrawableFromAsset(String strName) {
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(inputStream, null);

        return d;
    }

    private void changeImg() {
        changeText();

        String picName = db_gamesInfo.getNameOfPic(); // getJpgName(short id);
        mBackgroundImage.setImageDrawable(getDrawableFromAsset("pic_of_game/" + picName));
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
        iTS_score.setText(String.valueOf(0));

        db_gamesInfo.reset();

        changeImg();
    }

    private void youSure(View v) {
        String question = (v.getId() == R.id.iImgBtn_restart_game) ? "restart" : "quit";
        builder.setMessage("Do you definitely want to " + question + " the game?")
                .setPositiveButton("yes", (dialog, id) -> {
                    playSound(clickSound);
                    if (v.getId() == R.id.iImgBtn_restart_game) {
                        highScore_condition();
                        reset();// якщо ресет то очки не зараховуються ?
                    } else if (v.getId() == R.id.iImgBtn_quit_game) {
                        highScore_condition();
                        game_over_intent.putExtra(SCORE, String.valueOf(score));
                        game_over_intent.putExtra(HIGH_SCORE, String.valueOf(highScore));
                        startActivity(game_over_intent);
                    }
                })
                .setNegativeButton("no", null)
                .show();
    }

    private void highScore_condition() {
        if (highScore > score) {
        } else {
            highScore = score;
            prefs.edit().putInt(HIGH_SCORE, highScore).apply();
        }
    }

    private void timerForRightBtn() {
        Drawable g = iTS_score.getBackground();
        new CountDownTimer(1000, 600) {
            public void onTick(long millisecondsUntilDone) {
                iTS_score.setText("Right");
                iTS_score.setBackgroundResource(R.drawable.right);
            }

            public void onFinish() {
                iTS_score.setBackground(g);
                iTS_score.setText(String.valueOf(score));
            }
        }.start();
    }

    private void playSound(MediaPlayer player) {
        player.start();
    }

    private void pauseSound(MediaPlayer player) {
        player.stop();
    }

    private void save(){
        String toJson = gson.toJson(db_gamesInfo.getIdsListOfDB());
        prefs.edit().putString(LIST_IDS, toJson).apply();

        prefs.edit().putInt(SCORE, score).apply(); // save score

        String rightAnswer = db_gamesInfo.getAnswer();
        prefs.edit().putString(RIGHT_ANSWER, rightAnswer).apply(); // save correct answer

        String btnTopLeft = btn_top_left.getText().toString();
        String btnTopRight = btn_top_right.getText().toString();
        String btnBottonLeft = btn_bottom_left.getText().toString();
        String btnBottonRight = btn_bottom_right.getText().toString();
        // запхати в JSON і потім foreach присвоїти текст
        //якщо запхати JSON в базу даних ?
    }

    private void load(){
        String fromJson = prefs.getString(LIST_IDS, null);
        Type type = new TypeToken<List<Short>>() {}.getType();
        ArrayList<Short> idsList = gson.fromJson(fromJson, type);

        Log.i("JSON", "from db: "+db_gamesInfo.getIdsListOfDB().toString());
        Log.i("JSON", "from json: "+idsList.toString());
    }
}
