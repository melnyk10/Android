package com.forest.guessthegame.service.impl;

import android.content.Context;
import android.util.Log;

import com.forest.guessthegame.dbSQLite.impl.DB_of_games;
import com.forest.guessthegame.service.DataBase_Service;

import java.util.ArrayList;
import java.util.List;

public class DataBase_Service_impl implements DataBase_Service {
    private Context context;
    private DB_of_games DBofgames;

    public List<Short> listOfIds;
    private String answer = "";
    private short indexOfID = 0;

    public DataBase_Service_impl(Context context) {
        this.context = context;
        this.DBofgames = new DB_of_games(context);
        //DBofgames.onUpgrade(DBofgames.getReadableDatabase(),1,1);
        this.listOfIds = DBofgames.getListIds();
    }


    public void getNewIndexForImg() {
        indexOfID = listOfIds.get(randomNameOfGame()); // random id from list
        answer = DBofgames.getEngName(indexOfID); // if get from DB need to add 1+randomNameOfGame()
        Log.e("index id", "index = " + indexOfID);
        Log.e("Answer", "answer = " + answer);
        Log.e("Size of list", "size = " +  getIdsListOfDB().size());
    }


    private short randomNameOfGame() {
        return (short) (Math.random() * (listOfIds.size())); // [0...21]
    }

    public String getAnswer() {
        return answer;
    }

    public short getID() {
        return indexOfID;
    }

    public void setListOfIds(List<Short> listOfIds) {
        this.listOfIds = listOfIds;
    }

    @Override
    public void reset() {
        listOfIds = new ArrayList<>(DBofgames.getListIds());
    }

    public List<Short> getIdsListOfDB() {
        return listOfIds;
    }

    @Override
    public String getPicturesName() {
        return DBofgames.getJpgName(indexOfID);
    }

    @Override
    public String getGamesName() {
        return DBofgames.getEngName(indexOfID);
    }

    @Override
    public String getGamesNameById(short id) {
        return DBofgames.getEngName(id);
    }

    @Override
    public short getYearOfGame() {
        return DBofgames.getYear(indexOfID);
    }


}
