package com.forest.guessthegame;

import android.content.Context;
import android.util.Log;

import com.forest.guessthegame.dbSQLite.DataBase_SQLite;

import java.util.ArrayList;
import java.util.List;

public class DB_games_info {
    private Context context;
    private DataBase_SQLite dataBase_sqLite;

     public List<Short> listOfIds;

    public DB_games_info(Context context) {
        this.context = context;
        this.dataBase_sqLite = new DataBase_SQLite(context);
        this.listOfIds = dataBase_sqLite.getListIds();
    }

    private String answer = "";
    private short indexOfID = 0;


    public void firstStartRightQuestion(){
        indexOfID = listOfIds.get(randomNameOfGame()); // random id from list
        answer = dataBase_sqLite.getEngName(indexOfID); // if get from DB need to add 1+randomNameOfGame()
        Log.e("Answer", "answer = "+answer);
        Log.e("index id", "index = "+indexOfID);
    }


    public String getAnswer() {
        return answer;
    }

    public short randomNameOfGame(){
        return (short) (Math.random()*(listOfIds.size())); // [0...21]
    }

    public void reset(){
        listOfIds = new ArrayList<>(dataBase_sqLite.getListIds());
    }

    public List<Short> getIdsListOfDB() {
        return listOfIds;
    }

    public String getNameOfPic(){
        return dataBase_sqLite.getJpgName(indexOfID);
    }
    public String getName(){
        return dataBase_sqLite.getEngName(indexOfID);
    }
    public String getName(short id){
        return dataBase_sqLite.getEngName(id);
    }
    public short getID(){
        return indexOfID;
    }
    public short getYear(){
        return dataBase_sqLite.getYear(indexOfID);
    }



}
