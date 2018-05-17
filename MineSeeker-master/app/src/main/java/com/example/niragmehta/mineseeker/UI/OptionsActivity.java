package com.example.niragmehta.mineseeker.UI;
import com.example.niragmehta.mineseeker.model.*;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.niragmehta.mineseeker.R;

public class OptionsActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsActivity.class);
    }

    private ConfigOptions configOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        configOptions=ConfigOptions.getInstance();

        final RadioGroup radioSizeGroup=findViewById(R.id.radioSize);
        final RadioGroup radioMineGroup=findViewById(R.id.radioMines);

        RadioButton currentRadioSize;
        currentRadioSize = (RadioButton)radioSizeGroup.getChildAt(configOptions.getSizeRadioIndex());

        RadioButton currentRadioMines;
        currentRadioMines = (RadioButton)radioMineGroup.getChildAt(configOptions.getMinesRadioIndex());

        currentRadioSize.setChecked(true);
        currentRadioMines.setChecked(true);

        Button startButton = findViewById(R.id.btnConfirm);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioSizeIndex=radioSizeGroup.indexOfChild(findViewById(radioSizeGroup.getCheckedRadioButtonId()));
                int mineRadioIndex=radioMineGroup.indexOfChild(findViewById(radioMineGroup.getCheckedRadioButtonId()));

                passCol(radioSizeIndex);
                passRow(radioSizeIndex);
                passMineCount(mineRadioIndex);
                passIndexSizeRadio(radioSizeIndex);
                passIndexMinesRadio(mineRadioIndex);

                finish();
            }
        });

        Button clearScoreButton=findViewById(R.id.btnClearScores);
        clearScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configOptions.setGamesPlayed(0);
                configOptions.cleargameScoreAndPlayedAndScore();
            }
        });

    }

    private void passRow(int radioIndex)
    {
        int[] row= {4,5,6};
        configOptions.setRow(row[radioIndex]);
    }

    private void passCol(int radioIndex)
    {
        int[] col = {6, 10, 15};
        configOptions.setCol(col[radioIndex]);
    }

    private  void passMineCount(int mineCount){
        int[] mineNum = {6, 10, 15, 20};
        configOptions.setMineCount(mineNum[mineCount]);
    }

    private  void passIndexSizeRadio(int sizeRadioIndex){
        configOptions.setSizeRadioIndex(sizeRadioIndex);
    }

    private void passIndexMinesRadio(int mineRadioIndex) {
        configOptions.setMinesRadioIndex(mineRadioIndex);
    }

}
