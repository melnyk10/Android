package com.forest.guessthegame;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.forest.guessthegame.dbSQLite.impl.DB_saveTable;

import java.util.ArrayList;
import java.util.List;

public class DB_games_info {
    private Context context;
    private DB_saveTable dataBase_sqLite;

    public List<Short> listOfIds;
    private String answer = "";
    private short indexOfID = 0;

    public DB_games_info(Context context) {
        this.context = context;
        this.dataBase_sqLite = new DB_saveTable(context);
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


    public String getAnswer() {
        return answer;
    }

    private short randomNameOfGame() {
        return (short) (Math.random() * (listOfIds.size())); // [0...21]
    }

    public void reset() {
        listOfIds = new ArrayList<>(dataBase_sqLite.getListIds());
    }

    public List<Short> getIdsListOfDB() {
        return listOfIds;
    }

    public String getNameOfPic() {
        return dataBase_sqLite.getJpgName(indexOfID);
    }

    public String getName() {
        return dataBase_sqLite.getEngName(indexOfID);
    }

    public String getName(short id) {
        return dataBase_sqLite.getEngName(id);
    }

    public short getID() {
        return indexOfID;
    }

    public short getYear() {
        return dataBase_sqLite.getYear(indexOfID);
    }

    public void setListOfIds(List<Short> listOfIds) {
        this.listOfIds = listOfIds;
    }




    public void saveScore(int score){
        dataBase_sqLite.saveScore(score);
    }

    public void saveNameOfPic(){
        dataBase_sqLite.saveNameOfPicFromDB(getNameOfPic());
    }

    public void saveListOfIDS(String ids){
        dataBase_sqLite.saveListOfIds(ids);
    }

    public void saveBtnText(Button btn, String KEY_OF_BTN){
        dataBase_sqLite.saveBtnText(btn, KEY_OF_BTN);
    }

    public int getScore(){
        return dataBase_sqLite.getScore();
    }

    public String getNameOfPicFromDB(){
        return dataBase_sqLite.getNameOfPicFromDB();
    }

    public String getListOfIDS(){
        return dataBase_sqLite.getListOfIds();
    }

    public String getBtnText(String KEY_OF_BTN){
        return dataBase_sqLite.getBtnText(KEY_OF_BTN);
    }

    public void insert(){dataBase_sqLite.insert();}
    public void delete(){dataBase_sqLite.delete();}
    public String printAll(){return dataBase_sqLite.showAll();}

}
