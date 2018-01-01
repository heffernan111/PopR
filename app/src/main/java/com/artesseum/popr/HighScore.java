package com.artesseum.popr;

/**
 * Created by apoca on 01/01/2018.
 */

public class HighScore {

    public int currentHighScore;
    public String displayname;


    public HighScore (){} // needed for firebase

    public HighScore(String displayname, int currentHighScore){
        displayname = displayname;
        currentHighScore = currentHighScore;
    }

    public int getCurrentHighScore() {
        return currentHighScore;
    }

    public void setCurrentHighScore(int currentHighScore) {
        this.currentHighScore = currentHighScore;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }



}
