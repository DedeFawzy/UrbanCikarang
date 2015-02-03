package com.bagen.ilok.urbancikarang;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class Changelog extends Activity {
    private WebView mChangelog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);

        mChangelog = (WebView) findViewById(R.id.changelogView);
        WebSettings webSettings = mChangelog.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mChangelog.loadUrl("file:///android_asset/changelog.html");
    }
}
