package com.example.niragmehta.mineseeker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Warren Ho Kin on 2/15/2018.
 */

public class ConfigOptions {
    private int row;
    private int col;
    private int mineCount;
    private int sizeRadioIndex;
    private int minesRadioIndex;
    private int gamesPlayed;
    private List<Integer> gameScore;
    private List<String>  gamesPlayedAndScore;
    private static ConfigOptions ourInstance;

    public static ConfigOptions getInstance() {
        if(ourInstance == null)
        {
             ourInstance = new ConfigOptions();
        }
        return ourInstance;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesPlayed() {

        return gamesPlayed;
    }

    public List<Integer> getGameScore() {
        return gameScore;
    }

    private ConfigOptions() {
        row = 4;
        col = 6;
        mineCount = 6;
        sizeRadioIndex = 0;
        minesRadioIndex = 0;
        gamesPlayed=0;
        gameScore=new ArrayList<>();
        gamesPlayedAndScore=new ArrayList<>();
        gamesPlayedAndScore.add("Id\t\tScore");

    }

    public void cleargameScoreAndPlayedAndScore()
    {
        gameScore.clear();
        gamesPlayedAndScore.clear();
    }

    public void updategamesPlayedAndScore()
    {
        gamesPlayedAndScore.add(""+gameScore.size()+"\t\t"+Integer.toString(gameScore.get(gameScore.size()-1)));
    }

    public List<String> getGamesPlayedAndScore() {
        return gamesPlayedAndScore;
    }

    public int getSizeRadioIndex() {
        return sizeRadioIndex;
    }

    public void setSizeRadioIndex(int sizeRadioIndex) {
        this.sizeRadioIndex = sizeRadioIndex;
    }

    public int getMinesRadioIndex() {
        return minesRadioIndex;
    }

    public void setMinesRadioIndex(int minesRadioIndex) {
        this.minesRadioIndex = minesRadioIndex;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }
}
