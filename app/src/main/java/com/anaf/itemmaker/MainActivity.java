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
    Button buttonNotificationMaker, buttonMediaPlayerHelper, buttonHardwareHelper, buttonOtherHelper,
    buttonAlertDialogMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonNotificationMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NotificationHelperActivity.class);
                startActivity(i);
                finish();
            }
        });

        buttonMediaPlayerHelper.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MediaPlayerHelperActivity.class);
                startActivity(i);
                finish();
            }
        });

        buttonHardwareHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonAlertDialogMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonOtherHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonAlertDialogMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), AlertDialogMakerActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void init() {
        buttonNotificationMaker = findViewById(R.id.buttonNotificationMaker);
        buttonMediaPlayerHelper = findViewById(R.id.buttonMediaPlayerHelper);
        buttonHardwareHelper = findViewById(R.id.buttonHardWareHelper);
        buttonOtherHelper = findViewById(R.id.buttonOtherHelper);
        buttonAlertDialogMaker = findViewById(R.id.buttonAlertDialogMaker);
    }
}
