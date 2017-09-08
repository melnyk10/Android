package com.forest.guessthegame.service.impl;

import android.content.Context;
import android.util.Log;

import com.forest.guessthegame.dbSQLite.impl.DataBase_SQLite;
import com.forest.guessthegame.service.DataBase_Service;

import java.util.ArrayList;
import java.util.List;

public class DataBase_Service_impl implements DataBase_Service {
    private Context context;
    private DataBase_SQLite dataBase_sqLite;

    public List<Short> listOfIds;
    private String answer = "";
    private short indexOfID = 0;

    public DataBase_Service_impl(Context context) {
        this.context = context;
        this.dataBase_sqLite = new DataBase_SQLite(context);
        //dataBase_sqLite.onUpgrade(dataBase_sqLite.getReadableDatabase(),1,1);
        this.listOfIds = dataBase_sqLite.getListIds();
    }


    public void getNewIndexForImg() {
        indexOfID = listOfIds.get(randomNameOfGame()); // random id from list
        answer = dataBase_sqLite.getEngName(indexOfID); // if get from DB need to add 1+randomNameOfGame()
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
        listOfIds = new ArrayList<>(dataBase_sqLite.getListIds());
    }

    public List<Short> getIdsListOfDB() {
        return listOfIds;
    }

    @Override
    public String getPicturesName() {
        return dataBase_sqLite.getJpgName(indexOfID);
    }

    @Override
    public String getGamesName() {
        return dataBase_sqLite.getEngName(indexOfID);
    }

    @Override
    public String getGamesNameById(short id) {
        return dataBase_sqLite.getEngName(id);
    }

    @Override
    public short getYearOfGame() {
        return dataBase_sqLite.getYear(indexOfID);
    }


}
