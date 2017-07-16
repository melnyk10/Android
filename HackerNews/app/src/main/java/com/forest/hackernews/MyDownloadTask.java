package com.forest.hackernews;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyDownloadTask extends AsyncTask<String, Void, String> {
    private Context context;

    public MyDownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

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

            return result;
        } catch (Exception e) {
            Toast.makeText(context, "Could not find news", Toast.LENGTH_LONG); }


        return "false";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);

            String nameOfNews = jsonObject.getString("");

            Log.i("Weather content", nameOfNews);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
