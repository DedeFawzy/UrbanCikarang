package com.bagen.ilok.urbancikarang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class Twit extends Activity {

    private static final String baseURl = "https://twitter.com";

    private static final String widgetInfo =
            "<a class=\"twitter-timeline\"  href=\"https://twitter.com/UrbanCikarang\" data-widget-id=\"558178304138555392\">Loading Tweets by \"@UrbanCikarang...\"</a> " +
            "<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_twit);

        load_background_color();

        WebView mWebView = (WebView) findViewById(R.id.activity_twit_webview);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);

        Toast.makeText(getApplicationContext(), (getString(R.string.tunggu)), Toast.LENGTH_LONG).show();

    }

    private void load_background_color() {
        WebView mWebView = (WebView) findViewById(R.id.activity_twit_webview);
        mWebView.setBackgroundColor(0);
    }
}
