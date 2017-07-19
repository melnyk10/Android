package com.forest.guessthegame.dbSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by forest on 19.07.2017.
 */

public class DataBase_SQLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GuessTheGame";
    private static final String TABLE_HACKERS_NEWS = "info_about_game";
    private static final String KEY_ID = "id";
    private static final String KEY_ENG_NAME = "eng_name";
    private static final String KEY_RUS_NAME = "rus_name";
    private static final String KEY_UKR_NAME = "ua_name";
    private static final String KEY_YEAR = "year";
    private static final String KEY_IMG_NAME = "img_name";
    private static final String KEY_URL = "url";


    public DataBase_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
