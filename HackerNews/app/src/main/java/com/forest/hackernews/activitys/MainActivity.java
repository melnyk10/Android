package com.forest.hackernews.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.forest.hackernews.DBservice.SQDataBase;
import com.forest.hackernews.DownloadTask;
import com.forest.hackernews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQDataBase sqDataBase = new SQDataBase(this);
    ProgressBar progressBar = null;
    TextView loadingTV = null;
    DownloadTask downloadTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.iPB_loadNews);
        loadingTV = (TextView) findViewById(R.id.iTV_loading);
        progressBar.setMax(100);

        downloadTask = new DownloadTask(this, sqDataBase, progressBar, loadingTV);



        //downloadTask = new DownloadTask(this, sqDataBase, progressBar, loadingTV);

        sqDataBase.clean();

        downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
    }
}

