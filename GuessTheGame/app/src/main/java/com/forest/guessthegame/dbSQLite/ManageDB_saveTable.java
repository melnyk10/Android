package com.forest.guessthegame.dbSQLite;

import android.widget.Button;

public interface ManageDB_saveTable {
    void saveScore(int score);
    int getScore();

    void saveRightAnswer(String answer);
    String getRightAnswer();


    void saveListOfIds(String listOfIds);
    String getListOfIds();

    void saveBtnText(Button button, String KEY_BTN);
    String getBtnText(String KEY_BTN);

    String showAll();

    void delete();

    void insert();
}
