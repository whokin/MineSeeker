package com.example.niragmehta.mineseeker.UI;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.niragmehta.mineseeker.R;
import com.example.niragmehta.mineseeker.model.MusicService;

public class MainMenu extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context, MainMenu.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent svc=new Intent(this, MusicService.class);
        startService(svc);

        Button aboutButton = (Button) findViewById(R.id.btnAbout);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AboutActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });

        Button  playButton = (Button) findViewById(R.id.btnPlayGame);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Game.makeIntent(MainMenu.this);
                Intent svc=new Intent(MainMenu.this, MusicService.class);
                stopService(svc);
                startActivity(intent);
            }
        });

        Button startButton = (Button) findViewById(R.id.btnOptions);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OptionsActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });

        Button scoreButton=findViewById(R.id.btnScore);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = scoreBoard.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });

    }
}
