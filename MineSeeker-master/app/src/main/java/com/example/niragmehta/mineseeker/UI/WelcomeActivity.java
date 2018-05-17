package com.example.niragmehta.mineseeker.UI;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.niragmehta.mineseeker.R;
import com.example.niragmehta.mineseeker.model.MusicService;

public class WelcomeActivity extends AppCompatActivity {

    private long animationDuration = 1000;

    private boolean startClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button startButton = (Button) findViewById(R.id.btnStart);
        handleAnimation();

        final MediaPlayer player = MediaPlayer.create(this, R.raw.fanfare);
        player.start();

        startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startClicked = true;
                    Intent intent = MainMenu.makeIntent(WelcomeActivity.this);
                    startActivity(intent);
                    finish();
                }
        });

        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if(startClicked == false){
                    Intent intent = MainMenu.makeIntent(WelcomeActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();
    }

    public void handleAnimation(){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(R.id.imgPokemonWelcome,"x",300f);
        animatorX.setDuration(animationDuration);

        ObjectAnimator animatorRotation = ObjectAnimator.ofFloat(R.id.imgPokemonWelcome,"rotation",0f, 360f);
        animatorX.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorRotation);
        animatorSet.start();
    }
}
