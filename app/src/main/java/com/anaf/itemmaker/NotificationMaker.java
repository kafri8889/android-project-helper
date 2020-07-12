package com.anaf.itemmaker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class NotificationMaker extends ContextWrapper {
    private Context context;

    public NotificationMaker(Context base) {
        super(base);

        this.context = base;
    }

    public void simpleNotification(String title, String text, int iconResID) {
         NotificationCompat.Builder builder =  new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(iconResID);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, builder.build());
    }

    public void bigTextStyleNotification(String title, String text, String bigContentTitle, String bigText, String summaryText, int smallIconResId, int bigIconResId) {
        Bitmap lagreIcon = BitmapFactory.decodeResource(getResources(), bigIconResId);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(bigText)
                .setBigContentTitle(bigContentTitle)
                .setSummaryText(summaryText);

        NotificationCompat.Builder builder =  new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(smallIconResId)
                .setStyle(bigTextStyle)
                .setLargeIcon(lagreIcon);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, builder.build());

    }

    public void bigPictureStyleNotification(Class activityClass1, Class activityClass2, String title, String text, String textActionActivity1, String textActionActivity2, int iconActivity1, int iconActivity2, int smallIconResId, int bigPictureResId) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), bigPictureResId)).build();

        Intent intent1 = new Intent(getApplicationContext(), activityClass1);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Intent intent2 = new Intent(getApplicationContext(), activityClass2);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent1 = PendingIntent.getActivity(getApplicationContext(), (int) Calendar.getInstance().getTimeInMillis(), intent1, 0);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), (int) Calendar.getInstance().getTimeInMillis(), intent2, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(smallIconResId)
                .setContentText(text)
                .setContentTitle(title)
                .setStyle(bigPictureStyle)
                .addAction(iconActivity1, textActionActivity1, pendingIntent1)
                .addAction(iconActivity2, textActionActivity2, pendingIntent2);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, builder.build());
    }

    public void inboxStyleNotification(Class activityClass, String title, String text, String bigContentTitle, String[] lineMessage, String summaryText, String textIconActivity, int iconActivity, int smallIconResId) {
        Intent intent = new Intent(getApplicationContext(), activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), (int) Calendar.getInstance().getTimeInMillis(), intent, 0);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setBigContentTitle(bigContentTitle);
        for (String s : lineMessage) {
            inboxStyle.addLine(s);
        }
        inboxStyle.setSummaryText(summaryText);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(smallIconResId)
                .setStyle(inboxStyle)
                .addAction(iconActivity, textIconActivity, pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, builder.build());

    }

    public void exampleSimple() {
        Toast.makeText(context, "notificationChannel.simpleNotification(\"title\", \"text\", R.drawable.icon)", Toast.LENGTH_SHORT).show();
    }

    public void exampleBigText() {
        Toast.makeText(context, "notificationChannel.bigTextStyleNotification(\"title\", \"text\", \"bigtitle\", \"bigtext\", \"summarytext\", R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);", Toast.LENGTH_LONG).show();
    }

    public void exampleBigPicture() {
        Toast.makeText(context, "notificationChannel.bigPictureStyleNotification(MainActivity.class, NotificationMaker.class, \\\"title\\\", \\\"text\\\", \\\"act1\\\", \\\"act2\\\", R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);", Toast.LENGTH_SHORT).show();
    }

    public void exampleInbox() {
        Toast.makeText(context, "notificationChannel.inboxStyleNotification(MainActivity.class, \\\"tit\\\", \\\"txt\\\", \\\"bigtit\\\", 5, new String[]{\\\"a\\\", \\\"d\\\", \\\"d\\\", \\\"e\\\", \\\"d\\\"}, \\\"sum\\\", \\\"txticact\\\", R.drawable.ic_launcher_background, R.mipmap.ic_launcher);", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static class Channel extends ContextWrapper {
        private NotificationChannel notificationChannel;
        private NotificationCompat.Builder builder;
        private Context context;
        private NotificationManager manager;
        private String ANDROID_DEFAULT_CHANNEL_ID = "DEFAULT";
        private String ANDROID_CHANNEL_ID = "ID_CODE_O_R";
        private String ANDROID_CHANNEL_NAME = "ANDROID_O_R";

        private int IMPORTANCE;

        public Channel(Context base) {
            super(base);
            this.context = base;

            createChannel();
            builder =  new NotificationCompat.Builder(context, ANDROID_CHANNEL_ID);
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        private void createChannel() {
            notificationChannel = new NotificationChannel(ANDROID_CHANNEL_ID, ANDROID_CHANNEL_NAME, IMPORTANCE);

        }

        public void setIMPORTANCE(String default_high_low_max_min_none_unspecified) {
            if (default_high_low_max_min_none_unspecified.equals("default")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;
            }

            else if (default_high_low_max_min_none_unspecified.equals("high")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
            }

            else if (default_high_low_max_min_none_unspecified.equals("low")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_LOW;
            }

            else if (default_high_low_max_min_none_unspecified.equals("max")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_MAX;
            }

            else if (default_high_low_max_min_none_unspecified.equals("min")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_MIN;
            }

            else if (default_high_low_max_min_none_unspecified.equals("none")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_NONE;
            }

            else if (default_high_low_max_min_none_unspecified.equals("unspecified")) {
                IMPORTANCE = NotificationManager.IMPORTANCE_UNSPECIFIED;
            }

            else {
                Toast.makeText(context, "Error set Importance: add specified importance", Toast.LENGTH_SHORT).show();
            }
        }

        public void simpleNotification(String title, String text, int iconResId) {
                    builder.setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(iconResId);
            builder.setChannelId(ANDROID_CHANNEL_ID);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
            manager.notify((int) System.currentTimeMillis(), builder.build());
        }

        public void bigTextStyleNotification(String title, String text, String bigContentTitle, String bigText, String summaryText, int smallIconResId, int bigIconResId) {
            Bitmap lagreIcon = BitmapFactory.decodeResource(getResources(), bigIconResId);

            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.bigText(bigText)
                    .setBigContentTitle(bigContentTitle)
                    .setSummaryText(summaryText);

            NotificationCompat.Builder builder =  new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(smallIconResId)
                    .setStyle(bigTextStyle)
                    .setLargeIcon(lagreIcon);
            builder.setChannelId(ANDROID_CHANNEL_ID);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
            manager.notify((int) System.currentTimeMillis(), builder.build());

        }

        public void bigPictureStyleNotification(Class activityClass1, Class activityClass2, String title, String text, String textActionActivity1, String textActionActivity2, int iconActivity1, int iconActivity2, int smallIconResId, int bigPictureResId) {
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), bigPictureResId)).build();

            Intent intent1 = new Intent(getApplicationContext(), activityClass1);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            Intent intent2 = new Intent(getApplicationContext(), activityClass2);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent1 = PendingIntent.getActivity(getApplicationContext(), (int) Calendar.getInstance().getTimeInMillis(), intent1, 0);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), (int) Calendar.getInstance().getTimeInMillis(), intent2, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(smallIconResId)
                    .setContentText(text)
                    .setContentTitle(title)
                    .setStyle(bigPictureStyle)
                    .addAction(iconActivity1, textActionActivity1, pendingIntent1)
                    .addAction(iconActivity2, textActionActivity2, pendingIntent2);

            builder.setChannelId(ANDROID_CHANNEL_ID);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
            manager.notify((int) System.currentTimeMillis(), builder.build());
        }

        public void inboxStyleNotification(Class activityClass, String title, String text, String bigContenttitle, String[] lineMessage, String summaryText, String textIconActivity, int iconActivity, int smallIconResId) {
            Intent intent = new Intent(getApplicationContext(), activityClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), (int) Calendar.getInstance().getTimeInMillis(), intent, 0);

            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

            inboxStyle.setBigContentTitle(bigContenttitle);
            for (String s : lineMessage) {
                inboxStyle.addLine(s);
            }
            inboxStyle.setSummaryText(summaryText);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(smallIconResId)
                    .setStyle(inboxStyle)
                    .addAction(iconActivity, textIconActivity, pendingIntent);

            builder.setChannelId(ANDROID_CHANNEL_ID);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
            manager.notify((int) System.currentTimeMillis(), builder.build());

        }

        public void setEnableLights(int color) {
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(color);
        }

        public void setSound(Uri uriSound, AudioAttributes audioAttributes) {
            notificationChannel.setSound(uriSound, audioAttributes);
        }

        public void setEnableVibration() {
            notificationChannel.enableVibration(true);
        }

        public void setNotRemovable() {
            builder.setOngoing(true);
        }

        public void setRemovable() {
            builder.setOngoing(false);
        }

        public void exampleSimple() {
            Toast.makeText(context, "notificationChannel.simpleNotification(\"title\", \"text\", R.drawable.icon)", Toast.LENGTH_SHORT).show();
        }

        public void exampleBigText() {
            Toast.makeText(context, "notificationChannel.bigTextStyleNotification(\"title\", \"text\", \"bigtitle\", \"bigtext\", \"summarytext\", R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);", Toast.LENGTH_LONG).show();
        }

        public void exampleBigPicture() {
            Toast.makeText(context, "notificationChannel.bigPictureStyleNotification(MainActivity.class, NotificationMaker.class, \\\"title\\\", \\\"text\\\", \\\"act1\\\", \\\"act2\\\", R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background);", Toast.LENGTH_SHORT).show();
        }

        public void exampleInbox() {
            Toast.makeText(context, "notificationChannel.inboxStyleNotification(MainActivity.class, \\\"tit\\\", \\\"txt\\\", \\\"bigtit\\\", 5, new String[]{\\\"a\\\", \\\"d\\\", \\\"d\\\", \\\"e\\\", \\\"d\\\"}, \\\"sum\\\", \\\"txticact\\\", R.drawable.ic_launcher_background, R.mipmap.ic_launcher);", Toast.LENGTH_SHORT).show();
        }

    }
}
