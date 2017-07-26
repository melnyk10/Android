package com.forest.guessthegame.dbSQLite;

import java.util.List;

public interface ManageDB {
    String getEngName(short id);
    String getJpgName(short id);
    short getYear(short id);
    short getIDbyName(String name);
    List<? extends Number> getListIds();
}
