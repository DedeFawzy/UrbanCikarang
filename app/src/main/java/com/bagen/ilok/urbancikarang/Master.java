package com.bagen.ilok.urbancikarang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bagen.ilok.urbancikarang.nearby.CikarangNearby;


public class Master extends Activity {

    private WebView mWebView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getString(R.string.alert)).setMessage(getString(R.string.koneksi))
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(isNetworkStatusAvialable (getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), (getString(R.string.tunggu)), Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_master);
                    mWebView = (WebView) findViewById(R.id.activity_master_webview);
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);


                    WebSettings webSettings = mWebView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    mWebView.loadUrl("http://www.urbancikarang.com/v2/");
                    mWebView.setWebViewClient(new mWebViewClient());
                    mWebView.getSettings().setSupportZoom(true);
                    mWebView.getSettings().setBuiltInZoomControls(true);
                    mWebView.getSettings().setUseWideViewPort(true);
                    mWebView.getSettings().setLoadWithOverviewMode(true);
                }
                else {
                    Toast.makeText(getApplicationContext(), (getString(R.string.putus)), Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

        })

        .setNegativeButton(getString(R.string.ntar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();

    }

    public boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
        if
            (mWebView.canGoBack()){
             mWebView.goBack();
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.title_exit))
                    .setMessage(getString(R.string.action_exit))
                    .setPositiveButton(getString(R.string.yoik), new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton(getString(R.string.mbung), null)
                    .show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_master, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_twit:
                startActivity(new Intent(Master.this, Twit.class));
                overridePendingTransition(R.anim.translate_fast, R.anim.translate_fast);
                return true;
            case R.id.action_nearby:
                startActivity(new Intent(Master.this, CikarangNearby.class));
                overridePendingTransition(R.anim.translate_fast, R.anim.translate_fast);
                return true;
            case R.id.action_iklan:
                Toast.makeText(getApplicationContext(), "Beriklan di UrbanCikarang mulai dari Rp. 50.000", Toast.LENGTH_SHORT).show();
                Intent iklanIntent = new Intent(android.content.Intent.ACTION_SEND);
                String iklanEmailList[] = {"iklan.urbancikarang@gmail.com"};
                iklanIntent.putExtra(android.content.Intent.EXTRA_EMAIL, iklanEmailList);
                iklanIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Beriklan di UrbanCikarang");
                iklanIntent.setType("plain/text");
                iklanIntent.putExtra(android.content.Intent.EXTRA_TEXT, (getString(R.string.isi_iklan)));
                startActivity(Intent.createChooser(iklanIntent, (getString(R.string.aksi))));
                return true;
            case R.id.action_partner:
                Intent partnerIntent = new Intent(android.content.Intent.ACTION_SEND);
                String partnerEmailList[] = {"mediapartner.urbancikarang@gmail.com"};
                partnerIntent.putExtra(android.content.Intent.EXTRA_EMAIL, partnerEmailList);
                partnerIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Media Partner UrbanCikarang");
                partnerIntent.setType("plain/text");
                partnerIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Rincian event :");
                startActivity(Intent.createChooser(partnerIntent, (getString(R.string.aksi))));
                return true;
            case R.id.action_feedback:
                Intent feedIntent = new Intent(android.content.Intent.ACTION_SEND);
                String feedEmailList[] = {"dede.fawzy@gmail.com"};
                feedIntent.putExtra(android.content.Intent.EXTRA_EMAIL, feedEmailList);
                feedIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback for UrbanCikarang app");
                feedIntent.setType("plain/text");
                startActivity(Intent.createChooser(feedIntent, (getString(R.string.aksi))));
                return true;
            case R.id.action_about:
                startActivity(new Intent(Master.this, About.class));
                overridePendingTransition(R.anim.translate_fast, R.anim.translate_fast);
                return true;

            case R.id.action_exit:
            {
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.title_exit))
                        .setMessage(getString(R.string.action_exit))
                        .setPositiveButton(getString(R.string.yoik), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton(getString(R.string.mbung), null)
                        .show();
            }

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public class mWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}
