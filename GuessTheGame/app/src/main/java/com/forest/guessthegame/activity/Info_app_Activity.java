package com.forest.guessthegame.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.forest.guessthegame.R;

public class Info_app_Activity extends Activity {

    private Button btnRateApp = null;
    private Button btnFeedback= null;
    private String[] arrayOfEmail = {"temp@gmail.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_app_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        btnRateApp = (Button) findViewById(R.id.iBtn_rate_app);
        btnFeedback = (Button) findViewById(R.id.iBtn_feedback);
    }

    public void btnOnClick_infoAct(View v){
        switch (v.getId()){
            case R.id.iBtn_rate_app:
                break;
            case R.id.iBtn_feedback:
                composeEmail(arrayOfEmail, "Feedback", "I'm email body.");
                break;
        }
    }

    public void composeEmail(String[] addresses, String subjectOfEmail, String emailBody) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectOfEmail);
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Info_app_Activity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
