package com.anaf.itemmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MediaPlayerHelperActivity extends AppCompatActivity {

    MediaPlayerHelper helper;
    Button buttonPlay, buttonPause, buttonStop;
    TextView textViewDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_helper);

        helper = new MediaPlayerHelper(getApplicationContext());
        buttonPlay = findViewById(R.id.buttonMediaPlayerHelperPlay);
        buttonPause = findViewById(R.id.buttonMediaPlayerHelperPause);
        buttonStop = findViewById(R.id.buttonMediaPlayerHelperStop);
        textViewDuration = findViewById(R.id.textViewMediaPlayerHelperDuration);

        try {
            helper.setDataSource(R.raw.say_my_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        textViewDuration.setText(helper.getStringDuration());

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.pauseCanPlay();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.pauseCanPlay();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.stop();
                helper.release();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (helper.isPlaying()) {
            helper.stopWithRelease();
        }

        helper.stopWithRelease();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
