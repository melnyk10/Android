package com.forest.hackernews;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listOfNews = null;

    private SQLiteDatabase eventsDB ;

   // private MyDownloadTask downloadTask = new MyDownloadTask();

    List<String> titlesOfNews = new ArrayList<>();
    List<String> contentOfNews = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    //int id = 14779881;
    //String url = "https://hacker-news.firebaseio.com/v0/item/"+id+".json?print=pretty";


    DownloadTask downloadTask = new DownloadTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listOfNews = (ListView) findViewById(R.id.iLV_news);
//
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlesOfNews);
//        listOfNews.setAdapter(arrayAdapter);
//
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
//        downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");



        downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
    }

    public void myOnClick(View v){

    }

    public void updateLV(){
        Cursor c = eventsDB.rawQuery("SELECT * FROM newsHacker", null);

        int titleIndex = c.getColumnIndex("title");
        int contentIndex = c.getColumnIndex("content");

        if(c.moveToFirst()) {
            titlesOfNews.clear();
            contentOfNews.clear();
            do {

                titlesOfNews.add(c.getString(titleIndex));
                contentOfNews.add(c.getString(contentIndex));

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }
    }


    class MyDownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            /////////// download all id index /////////////
            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read(); }
                //////////////////////////////////////////////////

                JSONArray jsonArray = new JSONArray(result);
//////////// download all JSON obj ////////////

                eventsDB.execSQL("DELETE FROM newsHacker");

                for (int i = 0; i < 20; i++) {

                    String idIndex = jsonArray.getString(i);

                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+idIndex+".json?print=pretty");

                    urlConnection = (HttpURLConnection) url.openConnection();

                    in = urlConnection.getInputStream();

                    reader = new InputStreamReader(in);

                    data = reader.read();

                    String articleInfo = "";

                    while (data != -1) {
                        char current = (char) data;

                        articleInfo += current;

                        data = reader.read();
                    }
                    ///////////////////////////////////////////

                    JSONObject jsonObject = new JSONObject(articleInfo);

                    if (!jsonObject.isNull("title") && !jsonObject.isNull("url")) {

                        String articleTitle = jsonObject.getString("title");

                        String articleURL = jsonObject.getString("url");

                        url = new URL(articleURL);

                        urlConnection = (HttpURLConnection) url.openConnection();

                        in = urlConnection.getInputStream();

                        reader = new InputStreamReader(in);

                        data = reader.read();

                        String articleContent = "";

                        while (data != -1) {
                            char current = (char) data;

                            articleContent += current;

                            data = reader.read();
                        }

                        String sql = "INSERT INTO newsHacker (articleId, title, content) VALUES(?,?,?) ";

                        SQLiteStatement statement = eventsDB.compileStatement(sql);

                        // передаємо дані з JSONObject в sql
                        statement.bindString(1, idIndex);      // 1ий параметр це 1 значення в VALUES (?, ?, ?). 2 параметр це параметр з JSONObject
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleContent);

                        statement.execute();  // приміняєм всі команди
                    }


                }


                return result;
            } catch (Exception e) {e.printStackTrace();}


            return "false";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            updateLV();
        }
    }
}

