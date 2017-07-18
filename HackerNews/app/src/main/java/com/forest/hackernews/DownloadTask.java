package com.forest.hackernews;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.forest.hackernews.DBservice.SQDataBase;

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


public class DownloadTask extends AsyncTask<String, Integer, String> {
    private Context context;
    SQDataBase db;
    ProgressBar progressBar;

    private List<String> titlesOfNews_DT = new ArrayList<>();
    private List<String> urlsOfNews = new ArrayList<>();

    public DownloadTask(Context context, SQDataBase db, ProgressBar progressBar) {
        this.context = context;
        this.db = db;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(String... urls) {
        StringBuffer idNews = new StringBuffer();
        URL url;
        HttpURLConnection httpURLConnection = null;

        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream input = new BufferedInputStream(httpURLConnection.getInputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(input);

            int data = inputStreamReader.read();
            while (data != -1) {
                char current = (char) data;
                idNews.append(current);
                data = inputStreamReader.read();
            }


            String result = idNews.toString();
            JSONArray jsonArray = new JSONArray(result);

            StringBuffer apiNewsHackers = new StringBuffer();
            JSONObject jsonObject;
            for (int i = 0; i < 20; i++) {
                url = new URL("https://hacker-news.firebaseio.com/v0/item/" + jsonArray.getString(i) + ".json?print=pretty");
                httpURLConnection = (HttpURLConnection) url.openConnection();

                //clear stringBuilder
                apiNewsHackers.setLength(0);
                apiNewsHackers.trimToSize();



                input = new BufferedInputStream(httpURLConnection.getInputStream());
                inputStreamReader = new InputStreamReader(input);

                data = inputStreamReader.read();
                while (data != -1) {
                    char current = (char) data;
                    apiNewsHackers.append(current);
                    data = inputStreamReader.read();
                }

                 jsonObject = new JSONObject(apiNewsHackers.toString());

                if (!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                    String titleOfNews = jsonObject.getString("title");
                    String urlOfNews = jsonObject.getString("url");

                    db.addTitleAndUrl(titleOfNews, urlOfNews);
                }

            }

            return apiNewsHackers.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "failed";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.progressBar != null) {
            progressBar.setProgress(values[0]);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        titlesOfNews_DT = db.getAllTitles();
        urlsOfNews = db.getAllUrls();
    }

//    public List<String> getTit(){
//        return titlesOfNews_DT;
//    }
//
//    public List<String> getUrls(){
//        return urlsOfNews;
//    }

}

/*
{
  "by" : "hunglee2",
  "descendants" : 14,
  "id" : 14786509,
  "kids" : [ 14786798, 14786765, 14786791, 14786760, 14786736, 14786719, 14786794, 14786730, 14786761 ],
  "score" : 34,
  "time" : 1500278592,
  "title" : "Archiveteam are backing up SoundCloud",
  "type" : "story",
  "url" : "http://archiveteam.org/index.php?title=SoundCloud&utm_content=bufferb94ff&utm_medium=social&utm_source=linkedin.com&utm_campaign=buffer"
}
 */
