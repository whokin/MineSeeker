package com.example.niragmehta.mineseeker.model;

import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.niragmehta.mineseeker.R;

/**
 * Created by niragmehta on 2018-02-15.
 */

public class Cell {
    Button imgButton;

    private boolean visible;
    private boolean mine;
    private boolean scanned;
    private int scannedValue;
    private int clickCount;

    public boolean isMine() {
        return mine;
    }

    public int getClickCount() {
        return clickCount;
    }

    public Cell( ){

        visible = false;
        mine = false;
        scanned = false;
        scannedValue = 0;
        clickCount=0;


    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isScanned() {
        return scanned;
    }

    public void incrementClickCount()
    {
        ++clickCount;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void setScannedValue(int scannedValue) {
        this.scannedValue = scannedValue;
    }

    public void setImgButton(Button imgButton) {
        this.imgButton = imgButton;
    }


    public void updateUI() {
        //imgButton.setBackgroundColor(Color.RED);
        imgButton.setBackgroundResource(R.drawable.pikachutwo);
    }

    public void displayScannedValue()
    {
        if(mine && !visible)
            return;
        imgButton.setText(Integer.toString(scannedValue));
    }


}
