package com.anaf.itemmaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationHelperActivity extends AppCompatActivity {
    NotificationMaker notificationMaker;
    NotificationMaker.Channel notificationMakerChannel;
    Button buttonSimpleNotif, buttonBigtext, buttonBigPicture, buttonInbox;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_helper);

        notificationMaker = new NotificationMaker(getApplicationContext());
        notificationMakerChannel = new NotificationMaker.Channel(getApplicationContext());
        notificationMakerChannel.setIMPORTANCE("max");
        notificationMakerChannel.setEnableVibration();

        buttonBigPicture = findViewById(R.id.buttonNotificationMakerBigPicture);
        buttonBigtext = findViewById(R.id.buttonNotificationMakerBigText);
        buttonInbox = findViewById(R.id.buttonNotificationMakerInbox);
        buttonSimpleNotif = findViewById(R.id.buttonNotificationMakerSimpleNotification);

        buttonSimpleNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 28) {
                    notificationMakerChannel.simpleNotification("Title", "Message", R.drawable.ic_launcher_background);
                }
                notificationMaker.simpleNotification("Title", "Message", R.drawable.ic_launcher_background);
            }
        });

        buttonBigtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 28) {
                    notificationMakerChannel.bigTextStyleNotification("Title", "Message", "Big Title", "Big Message", "Summary", R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground);
                }
                notificationMaker.bigTextStyleNotification("Title", "Message", "Big Title", "Big Message", "Summary", R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground);
            }
        });

        buttonBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 28) {
                    notificationMakerChannel.bigPictureStyleNotification(MainActivity.class, MainActivity.class, "Title", "Message",
                            "Message Action Act 1", "Message Action Act 2", R.drawable.ic_launcher_background,
                            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground);
                }
                notificationMaker.bigPictureStyleNotification(MainActivity.class, MainActivity.class, "Title", "Message",
                        "Message Action Act 1", "Message Action Act 2", R.drawable.ic_launcher_background,
                        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground);
            }
        });

        buttonInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 28) {
                    notificationMakerChannel.inboxStyleNotification(MainActivity.class, "Title", "Message", "Big Title",
                            new String[] {"Lee Soojin", "Park Soeun", "Shin Jiyoon", "Kim Jimin", "Cho Hyewon", "Han Jihyo", "Lee Jaehee"},
                            "Summary", "Text Icon Activity", R.mipmap.ic_launcher, R.drawable.ic_launcher_background);
                }
                notificationMaker.inboxStyleNotification(MainActivity.class, "Title", "Message", "Big Title",
                        new String[] {"Lee Soojin", "Park Soeun", "Shin Jiyoon", "Kim Jimin", "Cho Hyewon", "Han Jihyo", "Lee Jaehee"},
                        "Summary", "Text Icon Activity", R.mipmap.ic_launcher, R.drawable.ic_launcher_background);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
