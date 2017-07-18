package com.forest.hackernews.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.forest.hackernews.DBservice.SQDataBase;
import com.forest.hackernews.DownloadTask;
import com.forest.hackernews.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listOfNews = null;

    List<String> titlesOfNews = new ArrayList<>();
    List<String> urlsOfNews = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;

    SQDataBase sqDataBase = new SQDataBase(this);

    DownloadTask downloadTask = new DownloadTask(this, sqDataBase);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listOfNews = (ListView) findViewById(R.id.iLV_news);

        //downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");



        titlesOfNews = sqDataBase.getAllTitles();
//
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlesOfNews);
        listOfNews.setAdapter(arrayAdapter);

//        listOfNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
//                intent.putExtra("content", contentOfNews.get(i));
//
//                startActivity(intent);
//            }
//        });
//
//        eventsDB = this.openOrCreateDatabase("NewsHacker", MODE_PRIVATE, null);
//        eventsDB.execSQL("CREATE TABLE IF NOT EXISTS newsHacker (id INTEGER PRIMARY KEY, articleId INTEGER, title VARCHAR, content VARCHAR)");
//        updateLV();
//


        //downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

        //Log.i("DB", sqDataBase.getAll());
        sqDataBase.getAll();
    }
}

