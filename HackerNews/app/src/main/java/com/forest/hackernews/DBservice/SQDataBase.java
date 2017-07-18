package com.forest.hackernews.DBservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forest on 17.07.2017.
 */

public class SQDataBase extends SQLiteOpenHelper implements ISQLDataBase {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HackersNews";
    private static final String TABLE_HACKERS_NEWS = "haclers_news";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";


    public SQDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) { // вызывается при первом создании базы данных
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HACKERS_NEWS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " VARCHAR,"
                + KEY_URL + " VARCHAR" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //вызывается при модификации базы данных
        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HACKERS_NEWS);

        // Создаём новую таблицу
        onCreate(db);
    }

    @Override
    public void addTitleAndUrl(String title, String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_URL, url);

        db.insert(TABLE_HACKERS_NEWS, null, values);
        db.close();

        //arrayAdapter.notifyDataSetChanged();
    }


    @Override
    public List<String> getAllTitles() {
        List<String> titlesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "+KEY_TITLE+" FROM "+ TABLE_HACKERS_NEWS, null);

        int titleIndex = c.getColumnIndex(KEY_TITLE); //індекс колонки "event"

        //String result = "";

        if(c.moveToFirst()) {
            do {
                titlesList.add(c.getString(titleIndex));
            }while (c.moveToNext());
        }
        return titlesList;
    }



    @Override
    public List<String> getAllUrls() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select "+KEY_URL+" FROM "+ TABLE_HACKERS_NEWS, null);

        List<String> urlsList = new ArrayList<>();
        int urlsIndex = c.getColumnIndex(KEY_URL); //індекс колонки "event"

        if(c.moveToFirst()) {
            do {
                urlsList.add(c.getString(urlsIndex));
            }while (c.moveToNext());
        }
        return urlsList;
    }


    public String getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE_HACKERS_NEWS, null);

        int titleIndex = c.getColumnIndex(KEY_TITLE); //індекс колонки "event"
        int urlIndex = c.getColumnIndex(KEY_URL); //індекс колонки "event"

        if(c.moveToFirst()) {
            do {
                Log.i("Tag", c.getString(titleIndex)+" "+c.getString(urlIndex));
            }while (c.moveToNext());

        }
        return null;
    }

    //remove all data from DB
    @Override
    public void clean() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_HACKERS_NEWS);
        db.close();
    }
}
