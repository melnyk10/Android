package com.forest.guessthegame.dbSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;


public class DB_saveTable extends DataBase_SQLite implements ManageDB_saveTable {
    private static final String TABLE_SAVE_INFO = "save_info";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_IDS = "list_of_ids";
    private static final String KEY_BTN_TEXT_1 = "btn_text_1";
    private static final String KEY_BTN_TEXT_2 = "btn_text_2";
    private static final String KEY_BTN_TEXT_3 = "btn_text_3";
    private static final String KEY_BTN_TEXT_4 = "btn_text_4";

    ContentValues values = new ContentValues();

    public DB_saveTable(Context context) {
        super(context);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SAVE_INFO_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SAVE_INFO + " ("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCORE + " INT,"
                + KEY_ANSWER + " VARCHAR, " + KEY_IDS + " VARCHAR, " + KEY_BTN_TEXT_1 + " VARCHAR, " + KEY_BTN_TEXT_2 + " VARCHAR, "
                +KEY_BTN_TEXT_3 + " VARCHAR, " +KEY_BTN_TEXT_4 + " VARCHAR" + ")";

        db.execSQL(CREATE_SAVE_INFO_TABLE);
    }


    @Override
    public void saveScore(int score) {
        values.put(KEY_SCORE, score);
    }

    @Override
    public int getScore() {
        int score = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_SCORE + " FROM " + TABLE_SAVE_INFO + " WHERE " + KEY_ID + " = 1", null);
        int listIdIndex = cursor.getColumnIndex(KEY_SCORE);
        if (cursor.moveToFirst()) {
            score = cursor.getInt(listIdIndex);
        }

        db.close();
        return score;
    }

    @Override
    public void saveRightAnswer(String answer) {
        values.put(KEY_ANSWER, answer);
    }

    @Override
    public String getRightAnswer() {
        String answer = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_ANSWER + " FROM " + TABLE_SAVE_INFO + " WHERE " + KEY_ID + " = 1", null);
        int listIdIndex = cursor.getColumnIndex(KEY_ANSWER);
        if (cursor.moveToFirst()) {
            answer = cursor.getString(listIdIndex);
        }
        db.close();
        return answer;
    }

    @Override
    public void saveListOfIds(String listOfIds) {
        values.put(KEY_IDS, listOfIds);
    }

    @Override
    public String getListOfIds() {
        String listOfIds = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_IDS + " FROM " + TABLE_SAVE_INFO + " WHERE " + KEY_ID + " = 1", null);
        int listIdIndex = cursor.getColumnIndex(KEY_IDS);
        if (cursor.moveToFirst()) {
            listOfIds = cursor.getString(listIdIndex);
        }
        db.close();

        return listOfIds;
    }

    @Override
    public void saveBtnText(Button button, String KEY_BTN) {
        String btnText = button.getText().toString();
        values.put(KEY_BTN, btnText);
    }

    @Override
    public String getBtnText(String KEY_BTN) {
        String btnText = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_BTN + " FROM " + TABLE_SAVE_INFO + " WHERE " + KEY_ID + " = 1", null);
        int listIdIndex = cursor.getColumnIndex(KEY_BTN);
        if (cursor.moveToFirst()) {
            btnText = cursor.getString(listIdIndex);
        }
        db.close();
        return btnText;
    }

    @Override
    public String showAll(){
        StringBuilder textBuilder = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SAVE_INFO, null);
        if (cursor.moveToFirst()) {
            do {
                textBuilder.append("id: "+ cursor.getString(0)+"\n"
                        +"score: "+cursor.getString(1)+"\n"
                        +"answer: "+cursor.getString(2)+"\n"
                        +"all ids: "+cursor.getString(3)+"\n"
                        +"btn_1: "+cursor.getString(4)+"\n"
                        +"btn_2: "+cursor.getString(5)+"\n"
                        +"btn_3: "+cursor.getString(6)+"\n"
                        +"btn_4: "+cursor.getString(7)+"\n");
            }while (cursor.moveToNext());
        }
        db.close();
        return textBuilder.toString();
    }

    @Override
    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE_SAVE_INFO);
        db.close();
    }

    @Override
    public void insert(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SAVE_INFO, null, values);
        db.close();
    }
}
