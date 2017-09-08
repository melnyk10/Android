package com.forest.guessthegame.service;


public interface DataBase_Service {
    void reset();
    String getPicturesName();
    String getGamesName();
    String getGamesNameById(short id);
    short getYearOfGame();
}
