package com.forest.guessthegame;

import android.content.Context;
import android.util.Log;

import com.forest.guessthegame.dbSQLite.DataBase_SQLite;

import java.util.ArrayList;
import java.util.List;


public class DB_Hash_temp {
    private Context context;
    DataBase_SQLite dataBase_sqLite;


    public DB_Hash_temp(Context context) {
        this.context = context;
        this.dataBase_sqLite = new DataBase_SQLite(context);
    }
    private List<Short> listOfIds;

    private String answer = "";
    private short indexOfID = 0;


    public void firstStartRightQuestion(){
        listOfIds = resetAll_ID();
        indexOfID = randomNameOfGame();
        answer = dataBase_sqLite.getEngName(indexOfID);
        Log.e("Answer", "answer = "+answer);
    }





    public short randomNameOfGame(){
        return (short) (Math.random()*(listOfIds.size()-1));
    }

    public List<Short> resetAll_ID(){
        return  new ArrayList<>(dataBase_sqLite.getListIds());
    }

    public List<Short> getNameListOfDB() {
        return listOfIds;
    }

    public String getNameOfPic(){
        return dataBase_sqLite.getJpgName(indexOfID);
    }
    public String getName(){
        return dataBase_sqLite.getEngName(indexOfID);
    }
    public short getID(){
        return indexOfID;
    }
    public short getYear(){
        return dataBase_sqLite.getYear(indexOfID);
    }


}
