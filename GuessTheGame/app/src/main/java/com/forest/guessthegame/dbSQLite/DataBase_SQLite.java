package com.forest.guessthegame.dbSQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase_SQLite extends SQLiteOpenHelper implements ManageDB {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "guessthegame";
    private static final String TABLE_GAMES_INFO = "info_about_game";
    private static final String KEY_ID = "id";
    private static final String KEY_ENG_NAME = "eng_name";
    private static final String KEY_YEAR = "year";
    private static final String KEY_IMG_NAME = "img_name";


    public DataBase_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GAMES_INFO + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ENG_NAME + " VARCHAR,"
                + KEY_YEAR + " INT, " + KEY_IMG_NAME + " VARCHAR" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);

        db.execSQL("INSERT INTO info_about_game (id, eng_name, year, img_name) VALUES (1,'Earthworm Jim',1994,'earthworm_jim.png')");
        db.execSQL("INSERT INTO info_about_game (id, eng_name, year, img_name) VALUES (2,'Battletoads',1991,'battletoads.jpg')");
        db.execSQL("INSERT INTO info_about_game (id, eng_name, year, img_name) VALUES (3,'Duck Hunt',1984,'duckhunt.png')");
        db.execSQL("INSERT INTO info_about_game (id, eng_name, year, img_name) VALUES (4,'Dark Souls',2001,'dark_soul_1.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (5,'Mortal Kombat X',2015,'mortal_kombat_x.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (6,'The Last of Us',2013,'the_last_of_us.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (7,'Assassins Creed II',2009,'assassins_creed_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (8,'Fallout 3',2008,'fallout_3.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (9,'GTA: San Andreas',2004,'gta_san_andreas.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (10,'Super Mario',1986,'super_mario.png')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES_INFO);
        onCreate(db);
    }

    @Override
    public String getEngName(short id) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_ENG_NAME+" FROM "+TABLE_GAMES_INFO +" WHERE "+KEY_ID+" = "+String.valueOf(id), null);
        int nameIndex = cursor.getColumnIndex(KEY_ENG_NAME);
        if(cursor.moveToFirst()) {
            name = cursor.getString(nameIndex);
        }
        db.close();
        return name;
    }

    @Override
    public String getJpgName(short id) {
        String imgName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_IMG_NAME+" FROM "+TABLE_GAMES_INFO +" WHERE "+KEY_ID+" = "+String.valueOf(id), null);
        int nameIndex = cursor.getColumnIndex(KEY_IMG_NAME);
        if(cursor.moveToFirst()) {
            imgName = cursor.getString(nameIndex);
        }
        db.close();
        return imgName;
    }

    @Override
    public short getYear(short id) {
        short year = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_YEAR+" FROM "+TABLE_GAMES_INFO +" WHERE "+KEY_ID+" = "+String.valueOf(id), null);
        int yearIndex = cursor.getColumnIndex(KEY_YEAR);
        if(cursor.moveToFirst()) {
            year = cursor.getShort(yearIndex);
        }
        db.close();
        return year;
    }

    @Override
    public short getIDbyName(String nameOfGame) {
        short id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_ID+" FROM "+TABLE_GAMES_INFO +" WHERE "+KEY_ENG_NAME+" = '"+nameOfGame+"'", null);
        int idIndex = cursor.getColumnIndex(KEY_ID);
        if(cursor.moveToFirst()) {
            id = cursor.getShort(idIndex);
        }
        db.close();
        return id;
    }

    @Override
    public List<Short> getListIds() {
        List<Short> idList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_ID+" FROM "+TABLE_GAMES_INFO, null);

        int idIndex = cursor.getColumnIndex(KEY_ID);

        if(cursor.moveToFirst()) {
            do {
                idList.add(cursor.getShort(idIndex));
            }while (cursor.moveToNext());
        }
        db.close();
        return idList;
    }
}

//insert into info_about_game (id, eng_name, year, img_name) values (1, 'Earthworm Jim', 1994, 'earthworm_jim.jpg')
