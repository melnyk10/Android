package com.forest.hackernews.DBservice;

import java.util.List;

/**
 * Created by forest on 17.07.2017.
 */

public interface ISQLDataBase {
    void addTitleAndUrl(String title, String url);
    List<String> getAllTitles();
    List<String> getAllUrls();
    void clean();

}
