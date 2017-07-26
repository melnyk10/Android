package com.forest.guessthegame.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.forest.guessthegame.R;

public class Info_app_Activity extends Activity {

    static final String gameVersion = "1.0";

    private Button btnRateApp = null;
    private Button btnFeedback = null;

    private static final String[] arrayOfEmail = {"temp@gmail.com"};
    private final String debugInfo ='\n'+""+'\n'
            +"Product (and Model): "+ android.os.Build.PRODUCT + " (" + android.os.Build.MODEL+")"+'\n'
            +"Android version: "+Build.VERSION.RELEASE+", OS API Level: "+android.os.Build.VERSION.SDK_INT+'\n'
            +"Game version: "+gameVersion;


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

    public void btnOnClick_infoAct(View v) {
        switch (v.getId()) {
            case R.id.iBtn_rate_app:
                rateMyApp();
                break;
            case R.id.iBtn_feedback:
                composeEmail(arrayOfEmail, "Feedback", debugInfo);
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
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.sendMail)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Info_app_Activity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void rateMyApp(){
        Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }
}

