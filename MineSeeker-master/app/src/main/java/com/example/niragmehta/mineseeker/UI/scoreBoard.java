package com.example.niragmehta.mineseeker.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.niragmehta.mineseeker.R;
import com.example.niragmehta.mineseeker.model.ConfigOptions;

import java.util.ArrayList;
import java.util.List;

public class scoreBoard extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context, scoreBoard.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        ConfigOptions configOptions=ConfigOptions.getInstance();

        ListView listView=findViewById(R.id.listViewScoreBoard);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.score,
                configOptions.getGamesPlayedAndScore());
        listView.setAdapter(adapter);

        int highScore=0;
        for(int i=0;i<configOptions.getGamesPlayed();i++)
        {
            if(highScore<configOptions.getGameScore().get(i))
                highScore=configOptions.getGameScore().get(i);
        }
        TextView txtHighScore=findViewById(R.id.txtHighScore);
        txtHighScore.setText(Integer.toString(highScore));


        Button btn=findViewById(R.id.btnExit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=MainMenu.makeIntent(scoreBoard.this);
                startActivity(intent);
                finish();
            }
        });

    }
}
