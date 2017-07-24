package com.forest.guessthegame;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game_HashMap {
    private static Map<String, String> mapOfAllGame = new HashMap<>();
    private List<String> arrayOfKeyHashMap = copyKeyMapToStringListArray();
    private String answer = null;
    private int indexOfArrayOfKey = 0;


    static {
        mapOfAllGame.put("Earthworm Jim", "earthworm_jim.png");
        mapOfAllGame.put("Duck Hunt", "duckhunt.jpg");
        mapOfAllGame.put("Battletoads", "battletoads.jpg");
        mapOfAllGame.put("Pro Evolution Soccer 2017", "pro_evolution_soccer_2017.jpg");
        mapOfAllGame.put("Assassins Creed Unity", "assassins_creed_unity.jpg");
        mapOfAllGame.put("Counter-Strike GO", "counter_strike_go.jpg");
        mapOfAllGame.put("Crysis", "crysis.jpg");
        mapOfAllGame.put("Dark Soul", "dark_soul.jpg");
        mapOfAllGame.put("Fallout 4", "fallout_4.jpg");
        mapOfAllGame.put("GTA San Andreas", "gta_san_andreas.png");
        mapOfAllGame.put("Battlefield 3", "battlefield_3.jpg");
        mapOfAllGame.put("The Sims 4", "the_sims_4.jpg");
        mapOfAllGame.put("GTA Vice City", "gta_vice_city.jpg");
        mapOfAllGame.put("Overwatch", "overwatch.jpg");
        mapOfAllGame.put("The Elder Scrolls V: Skyrim", "the_elder_scrolls_v_skyrim.jpg");
        mapOfAllGame.put("Sonic The Hedgehog", "sonic_the_hedgehog.png");
        mapOfAllGame.put("Super Mario", "super_mario.png");
        mapOfAllGame.put("Crysis", "crysis.jpg");
        mapOfAllGame.put("Need for Speed Underground 2", "nfs_underground_2.jpg");
        mapOfAllGame.put("Dead Space 3", "dead_space_3.jpg");
        mapOfAllGame.put("The Last of Us", "the_last_of_us.jpg");
        mapOfAllGame.put("Mass Effect 3", "mass_effect_3.jpg");
    }

    public void firstStartRightQuestion(){
        indexOfArrayOfKey = randomNameOfGame();
        answer = arrayOfKeyHashMap.get(indexOfArrayOfKey);
        Log.e("Answer", "answer = "+answer);
    }

    public ArrayList<String> copyKeyMapToStringListArray(){
        return new ArrayList<>(mapOfAllGame.keySet());
    }

    public void setArrayOfKeyHashMap(List<String> arrayOfKeyHashMap) {
        this.arrayOfKeyHashMap = arrayOfKeyHashMap;
    }



    public int randomNameOfGame(){
        return (int) (Math.random()*(arrayOfKeyHashMap.size()-1));
    }



    public Map<String, String> getMapOfAllGame() {
        return mapOfAllGame;
    } //відпадає

    public String getAnswer() {
        return answer;
    }

    public List<String> getNameListOfKeyHashMap() {
        return arrayOfKeyHashMap;
    }



    public int getIndexOfArrayOfKey() {
        return indexOfArrayOfKey;
    }
}
