package com.forest.guessthegame.dbSQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by forest on 19.07.2017.
 */

public class DataBase_SQLite extends SQLiteOpenHelper implements ManageDB {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GuessTheGame";
    private static final String TABLE_GAMES_INFO = "info_about_game";
    private static final String KEY_ID = "id";
    private static final String KEY_ENG_NAME = "eng_name";
    private static final String KEY_RUS_NAME = "rus_name";
    private static final String KEY_UKR_NAME = "ua_name";
    private static final String KEY_YEAR = "year";
    private static final String KEY_IMG_NAME = "img_name";


    public DataBase_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GAMES_INFO + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ENG_NAME + " VARCHAR,"
                + KEY_RUS_NAME + " VARCHAR" + KEY_UKR_NAME + " VARCHAR" + KEY_YEAR + " VARCHAR"
                + KEY_IMG_NAME + " VARCHAR" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES_INFO);
        onCreate(db);
    }

    @Override
    public String getEngName(short id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "+KEY_ENG_NAME+" FROM "+ TABLE_GAMES_INFO + " WHERE id = "+id, null);
        int titleIndex = c.getColumnIndex(KEY_ENG_NAME); //індекс колонки "event"

        db.close();
        c.close();

        return c.getString(titleIndex);
    }

    @Override
    public String getRusName(short id) {
        return null;
    }

    @Override
    public String getUaName(short id) {
        return null;
    }

    @Override
    public String getJpgName(short id) {
        return null;
    }

    @Override
    public short getYear(short id) {
        return 0;
    }
}
