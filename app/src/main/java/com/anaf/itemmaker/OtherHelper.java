package com.anaf.itemmaker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class OtherHelper extends ContextWrapper {
    private TextToSpeech textToSpeech;
    private View view;
    private VideoView videoView;
    private WebView webView;

    private boolean initTextToSpeech;

    public OtherHelper(Context base) {
        super(base);
    }

    public void textToSpeechInit() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                        Toast.makeText(OtherHelper.this, "Language not Supported", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        initTextToSpeech = true;
                        Log.i("Lang Result", "Berhasil");
                    }
                }

                else {
                    Log.e("TextToSpeech", "initializon Failed");
                }
            }
        });
    }

    public void textToSpeechSpeak(String text) {
        float pitch = 1f;
        float speed = 1.1f;

        if (initTextToSpeech) {
            textToSpeech.setPitch(pitch);
            textToSpeech.setSpeechRate(speed);

            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

        }

        else {
            Toast.makeText(this, "TextToSpeech Initialization Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void textToSpeechOnDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    public void videoViewNow(int resIdVideoView, int resIdVideoPath) {
        videoView = view.findViewById(resIdVideoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + resIdVideoPath;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    public void webView(int resIdWebView, String url_canNull) {
        webView = view.findViewById(resIdWebView);
        webView.setWebViewClient(new WebViewClient());

        if (url_canNull == null) {
            webView.loadUrl("https://www.google.com");
        }

        else {
            webView.loadUrl(url_canNull);
        }

        webView.canGoBack();
        webView.canGoForward();

        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setSafeBrowsingEnabled(true);
        webSettings.setDisplayZoomControls(true);
    }

    public void webViewOnBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }

    }

}
