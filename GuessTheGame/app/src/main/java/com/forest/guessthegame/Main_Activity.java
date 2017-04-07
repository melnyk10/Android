package com.forest.guessthegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_Activity extends AppCompatActivity {

    private Button go3thAct = null;

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

        go3thAct = (Button) findViewById(R.id.btn_goTo3thAct);


        go3thAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go3thAct.setBackgroundColor(0x80FD0004); //if answer is wrong change color of button to red
                Intent intent = new Intent(v.getContext(), Game_Activity.class);
                //startActivity(intent);
            }
        });
    }
}
