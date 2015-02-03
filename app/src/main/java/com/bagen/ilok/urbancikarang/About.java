package com.bagen.ilok.urbancikarang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        try {
            final PackageManager packageManager = getPackageManager();
            PackageInfo pinfo = packageManager.getPackageInfo(getPackageName(), 0);
            setTitle("UrbanCikarang v" + pinfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("NameNotFoundException", e.getMessage());
        }

        Button creditButton = (Button) findViewById(R.id.creditButton);
        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditList();
            }
        });

        Button changelogButton = (Button) findViewById(R.id.changelogButton);
        changelogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelog();
            }
        });
    }

    protected void creditList() {
        new AlertDialog.Builder(this).setTitle("UrbanCikarang").setMessage(getString(R.string.daftar_credit)).setNeutralButton("Close", null).show();
    }

    protected void changelog() {
        Intent intent = new Intent(About.this, Changelog.class);
        About.this.startActivity(intent);
        overridePendingTransition(R.anim.translate_fast, R.anim.translate_fast);
    }
}