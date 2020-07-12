package com.anaf.itemmaker;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.widget.Toast;

import java.io.IOException;

public class MediaPlayerHelper extends ContextWrapper {
    MediaPlayer mediaPlayer;
    String dataSourcePath;
    String dataSourceUri;

    public MediaPlayerHelper(Context base) {
        super(base);
        mediaPlayer = new MediaPlayer();
    }

    public boolean isNull() {
        return mediaPlayer == null;
    }

    public boolean notNull() {
        return mediaPlayer != null;
    }

    public String getStringDuration() {
        int minutes = mediaPlayer.getDuration() / 1000 / 60;
        int seconds = mediaPlayer.getDuration() / 1000 % 60;

        if (seconds < 10) {
            String minute = String.valueOf(minutes);
            String second = String.valueOf(seconds);
            return minute + ":0" + second;
        }

        else {
            String minute = String.valueOf(minutes);
            String second = String.valueOf(seconds);
            return minute + ":" + second;
        }
    }

    public int getIntDuration() {
        int minute = mediaPlayer.getDuration() / 1000 / 60;
        int second = mediaPlayer.getDuration() / 1000 % 60;

        return (minute * 60) + second;
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public String getPath() {
        return dataSourcePath;
    }

    public String getPathUri() {
        return dataSourceUri;
    }

    public void setDataSource(String path_or_url) throws IOException {
        try {
            mediaPlayer.setDataSource(path_or_url);
            dataSourcePath = path_or_url;
        } catch (IOException | IllegalStateException | SecurityException | IllegalArgumentException e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void setDataSource(int rawId) throws IOException {
        String path = "android.resource://" + getPackageName() + "/" + rawId;
        Uri uri = Uri.parse(path);
        mediaPlayer.setDataSource(getApplicationContext(), uri);
        dataSourceUri = path;
        mediaPlayer.prepare();
    }

    public void start() throws IOException {
        try {
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void start(boolean prepare, boolean prepareAsync) throws IOException {
        if (prepare && prepareAsync) {
            Toast.makeText(this, "Error, You Have To Choose One", Toast.LENGTH_SHORT).show();
        }

        else if (prepare) {
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

        else if (prepareAsync) {
            try {
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void seekToInSec(int second) {
        int msec = second * 1000;
        mediaPlayer.seekTo(msec);
    }

    public void seekTo(int seekTo) {
        mediaPlayer.seekTo(seekTo);
    }

    public void setSameVolumeOnDevice() {
        mediaPlayer.setVolume(1, 1);
    }

    public void setMuteVolume() {
        mediaPlayer.setVolume(0,0);
    }

    public void setVolume(float leftVolume, float rightVolume) {
        mediaPlayer.setVolume(leftVolume, rightVolume);
    }

    public void setCountDownMuteVolumeBeforeStart(int second) {
        int sec = second * 1000;
        new CountDownTimer(sec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mediaPlayer.setVolume(0,0);
            }

            @Override
            public void onFinish() {
                mediaPlayer.setVolume(1,1);
            }
        }.start();
    }

    public void setCountDownStartMuteVolume(int second) {
        int sec = second * 1000;
        new CountDownTimer(sec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mediaPlayer.setVolume(0,0);
                mediaPlayer.start();
            }

            @Override
            public void onFinish() {
                mediaPlayer.setVolume(1,1);
            }
        }.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void pauseCanPlay() {
        try {
            pauseTry();
        } catch (Exception e) {
            Toast.makeText(this, "Can't pause", Toast.LENGTH_SHORT).show();
        }
    }

    private void pauseTry() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

        else {
            try {
                mediaPlayer.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void setIsLooping() {
        mediaPlayer.setLooping(true);
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void stopWithRelease() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void reset() {
        mediaPlayer.reset();
    }

    public void prepare() throws IOException {
        mediaPlayer.prepare();
    }

    public void setWakeMode() {
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void release() {
        mediaPlayer.release();
    }

    public void setAudioStreamTypeStreamMusic() {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void setIsScreenOnWhilePlaying() {
        try {
            mediaPlayer.setScreenOnWhilePlaying(true);
        } catch (Exception e) {
            Toast.makeText(this, "Add Permission", Toast.LENGTH_SHORT).show();
        }
    }
    
}
