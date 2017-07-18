package com.forest.hackernews.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.forest.hackernews.R;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ListView listOfNews = null;

    private List<String> titlesOfNews = new ArrayList<>();
    private List<String> urlsOfNews = new ArrayList<>();

    private ArrayAdapter<String> arrayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);


        listOfNews = (ListView) findViewById(R.id.iLV_news);


        titlesOfNews = getIntent().getStringArrayListExtra("list_of_titles");
        urlsOfNews = getIntent().getStringArrayListExtra("list_of_urls");
//
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlesOfNews);
        listOfNews.setAdapter(arrayAdapter);

        listOfNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("content", urlsOfNews.get(i));

                startActivity(intent);
            }
        });
    }
}
