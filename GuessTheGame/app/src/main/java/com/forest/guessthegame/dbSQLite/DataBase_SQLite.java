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

        db.execSQL("INSERT INTO info_about_game VALUES (1,'Earthworm Jim',1994,'earthworm_jim.png')");
        db.execSQL("INSERT INTO info_about_game VALUES (2,'Battletoads',1991,'battletoads.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (3,'Duck Hunt',1984,'duckhunt.png')");
        db.execSQL("INSERT INTO info_about_game VALUES (4,'Dark Souls',2001,'dark_soul_1.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (5,'Mortal Kombat X',2015,'mortal_kombat_x.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (6,'The Last of Us',2013,'the_last_of_us.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (7,'Assassins Creed II',2009,'assassins_creed_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (8,'Fallout 3',2008,'fallout_3.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (9,'GTA: San Andreas',2004,'gta_san_andreas.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (10,'Super Mario',1986,'super_mario.png')");
        db.execSQL("INSERT INTO info_about_game VALUES (11,'Crysis',2007,'crysis_1.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (12,'Mass Effect 3',2012,'mass_effect_3.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (13,'Battlefield 3',2011,'battlefield_3.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (14,'Dead Space 3',2013,'dead_space_3.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (15,'The Sims 4',2013,'the_sims_4.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (16,'GTA: Vice City',2002,'gta_vice_city.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (17,'Overwatch',2016,'overwatch.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (18,'The Elder Scrolls V: Skyrim',2011,'the_elder_scrolls_v_Skyrim.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (19,'Sonic the Hedgehog',1991,'sonic_the_hedgehog.png')");
        db.execSQL("INSERT INTO info_about_game VALUES (20,'Need for Speed: Underground 2',2004,'nfs_underground_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (21,'CS: Global Offensive',2012,'counter_strike_go.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (22,'Pro Evolution Soccer 2017',2016,'pro_evolution_soccer_2017.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (23,'Half-Life 2',2004,'half_life_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (24,'Mafia II',2010,'mafia_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (25,'Batman: Arkham City',2011,'batman_arkham_city.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (26,'Portal 2',2011,'portal2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (27,'Slender: The Eight Pages',2012,'slender_the_eight_pages.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (28,'Silent Hill 2',2001,'silent_hill_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (29,'Tomb Raider: Anniversary',2007,'tomb_raider_anniversary_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (30,'Call of Duty: MW 2',2009,'cof_mw_2.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (31,'Shadow of the Colossus',2005,'shadow_of_the_colossus.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (32,'Red Dead Redemption',2010,'red_dead_redemption.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (33,'BioShock Infinite',2013,'bioshock_infinite.jpg')");
        db.execSQL("INSERT INTO info_about_game VALUES (34,'Uncharted 4: A Thief’s End',2016,'uncharted_4.jpg')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES_INFO);
    }

    @Override
    public String getEngName(short id) {
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_ENG_NAME + " FROM " + TABLE_GAMES_INFO + " WHERE " + KEY_ID + " = " + String.valueOf(id), null);
        int nameIndex = cursor.getColumnIndex(KEY_ENG_NAME);
        if (cursor.moveToFirst()) {
            name = cursor.getString(nameIndex);
        }
        db.close();
        return name;
    }

    @Override
    public String getJpgName(short id) {
        String imgName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_IMG_NAME + " FROM " + TABLE_GAMES_INFO + " WHERE " + KEY_ID + " = " + String.valueOf(id), null);
        int nameIndex = cursor.getColumnIndex(KEY_IMG_NAME);
        if (cursor.moveToFirst()) {
            imgName = cursor.getString(nameIndex);
        }
        db.close();
        return imgName;
    }

    @Override
    public short getYear(short id) {
        short year = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_YEAR + " FROM " + TABLE_GAMES_INFO + " WHERE " + KEY_ID + " = " + String.valueOf(id), null);
        int yearIndex = cursor.getColumnIndex(KEY_YEAR);
        if (cursor.moveToFirst()) {
            year = cursor.getShort(yearIndex);
        }
        db.close();
        return year;
    }

    @Override
    public short getIDbyName(String nameOfGame) {
        short id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + TABLE_GAMES_INFO + " WHERE " + KEY_ENG_NAME + " = '" + nameOfGame + "'", null);
        int idIndex = cursor.getColumnIndex(KEY_ID);
        if (cursor.moveToFirst()) {
            id = cursor.getShort(idIndex);
        }
        db.close();
        return id;
    }

    @Override
    public List<Short> getListIds() {
        List<Short> idSet = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + TABLE_GAMES_INFO, null);

        int idIndex = cursor.getColumnIndex(KEY_ID);

        if (cursor.moveToFirst()) {
            do {
                idSet.add(cursor.getShort(idIndex));
            } while (cursor.moveToNext());
        }
        db.close();
        return idSet;
    }

    public List<String> getListEngNames() {
        List<String> idList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_ENG_NAME + " FROM " + TABLE_GAMES_INFO, null);

        int idIndex = cursor.getColumnIndex(KEY_ENG_NAME);

        if (cursor.moveToFirst()) {
            do {
                idList.add(cursor.getString(idIndex));
            } while (cursor.moveToNext());
        }
        db.close();
        return idList;
    }
}

//insert into info_about_game (id, eng_name, year, img_name) values (1, 'Earthworm Jim', 1994, 'earthworm_jim.jpg')
