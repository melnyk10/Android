package com.forest.hackernews;

import android.os.AsyncTask;
import android.util.Log;

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

import static android.content.ContentValues.TAG;

/**
 * Created by forest on 17.07.2017.
 */

public class DownloadTask extends AsyncTask<String, Void, String> {
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
            StringBuffer apiNewsHackers = new StringBuffer();
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < 20; i++) {
                url = new URL("https://hacker-news.firebaseio.com/v0/item/" + jsonArray.getString(i) + ".json?print=pretty");
                httpURLConnection = (HttpURLConnection) url.openConnection();

                input = new BufferedInputStream(httpURLConnection.getInputStream());
                inputStreamReader = new InputStreamReader(input);

                data = inputStreamReader.read();
                while (data != -1) {
                    char current = (char) data;
                    apiNewsHackers.append(current);
                    data = inputStreamReader.read();
                }
                Log.i(TAG, "doInBackground: "+apiNewsHackers.toString());
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


}
