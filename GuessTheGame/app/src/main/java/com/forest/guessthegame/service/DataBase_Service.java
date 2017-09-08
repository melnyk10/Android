package com.forest.guessthegame.service;

import java.util.List;

/**
 * Created by forest on 08.09.2017.
 */

public interface DataBase_Service {
     //String getAnswer();
     //short randomNameOfGame();
     void reset();
     //List<Short> getIdsListOfDB();
     String getNameOfPic();
     String getName();
     String getName(short id);
     //short getID();
     short getYear();
     void setListOfIds(List<Short> listOfIds);
}
