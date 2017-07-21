package com.forest.guessthegame.dbSQLite;

/**
 * Created by forest on 19.07.2017.
 */

public interface ManageDB {
    String getEngName(short id);
    String getJpgName(short id);
    short getYear(short id);
}
