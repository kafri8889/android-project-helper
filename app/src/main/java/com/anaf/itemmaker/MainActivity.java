package com.anaf.itemmaker;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button buttonNottif, buttonPlay, buttonStop;
    TextView textViewDuration;

    NotificationMaker notificationMaker;
    NotificationMaker.Channel notificationChannel;
    MediaPlayerHelper mediaPlayerHelper;
    OtherHelper otherHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        otherHelper = new OtherHelper(getApplicationContext());
        otherHelper.textToSpeechInit();

        buttonNottif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void init() {
        buttonNottif = findViewById(R.id.buttonNotif);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonStop = findViewById(R.id.buttonStop);
        textViewDuration = findViewById(R.id.textViewDuration);
    }
}
