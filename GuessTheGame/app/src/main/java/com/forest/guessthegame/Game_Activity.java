package com.forest.guessthegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game_Activity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private Button btn_top_left = null;
    private Button btn_top_right = null;
    private Button btn_bottom_left = null;
    private Button btn_bottom_right = null;

    //private
    List<Button> buttonsList = new ArrayList<>();

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
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn_top_left = (Button) findViewById(R.id.iBtn_top_left);
        btn_top_right = (Button) findViewById(R.id.iBtn_top_right);
        btn_bottom_left = (Button) findViewById(R.id.iBtn_bottom_left);
        btn_bottom_right = (Button) findViewById(R.id.iBtn_bottom_right);

        buttonsList.add(btn_top_left);
        buttonsList.add(btn_top_right);
        buttonsList.add(btn_bottom_left);
        buttonsList.add(btn_bottom_right);

        mBackgroundImage = (ImageSwitcher) findViewById(R.id.gameFrameSwitcher);
        mBackgroundImage.setFactory(this);

        Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(2000);
        Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(2000);

        mBackgroundImage.setInAnimation(inAnimation);
        mBackgroundImage.setOutAnimation(outAnimation);

        game_hashMap.firstStartRightQuestion();

        randomSetGameNameTOButtonText();
        fillRestTextView();


        // 3 поля яких міняє картинку
        String mDrawableName = game_hashMap.getMapOfAllGame().get(game_hashMap.getAnswer()); // файл cat1.png в папке drawable
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        mBackgroundImage.setImageResource(resID); //картинка при першому запуску


        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void rightAndWrongAnswers(Button button) {
                //if wrong answer
                if (!(button.getText().toString().equals(game_hashMap.getAnswer()))) {
                    button.setBackgroundResource(R.drawable.wrong_btn);//if answer is wrong change color of button to red
                    onClick2();
//                    Intent intent=new Intent(getApplicationContext(), game_over.class);
//                    startActivity(intent);
                } else if (button.getText().toString().equals(game_hashMap.getAnswer())) {
                    button.setBackgroundResource(R.drawable.correct_btn);
                    //тимчасовий if
                    if (game_hashMap.getArrayOfKeyHashMap().size() < 4) {
                        reset();
                    }

                    changeImg();
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
                }
            }
        };

        btn_top_left.setOnClickListener(onClickListener);
        btn_top_right.setOnClickListener(onClickListener);
        btn_bottom_left.setOnClickListener(onClickListener);
        btn_bottom_right.setOnClickListener(onClickListener);
    }

    //тимчасовий метод
    public void onClick2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Game_Activity.this);
        builder.setTitle("GAME OVER")
                .setCancelable(false)
                .setNegativeButton("RESTART",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                reset();
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
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

        System.out.println("Size in IF = " + game_hashMap.getArrayOfKeyHashMap().size());
        game_hashMap.setArrayOfKeyHashMap(game_hashMap.copyKeyMapToStringListArray());
        changeImg();
    }
}
