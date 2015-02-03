package com.bagen.ilok.urbancikarang;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    ProgressBar progress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        progress = (ProgressBar) findViewById(R.id.progress);
        progress = new ProgressBar(SplashActivity.this);

        CountDown detik;
        detik=new CountDown(3000,3000,this,Master.class);
        detik.start();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        StartAnimations();

    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout Rela=(RelativeLayout) findViewById(R.id.bg_splash);
        Rela.clearAnimation();
        Rela.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

    }

    public class CountDown extends CountDownTimer {
        private Activity aktif;
        private Class kelas;
        public CountDown(long millisInFuture, long countDownInterval,Activity act,Class cls) {
            super(millisInFuture, countDownInterval);
            aktif=act;
            kelas=cls;
        }
        @Override
        public void onFinish() {
            aktif.startActivity(new Intent(aktif,kelas));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            aktif.finish();
        }
        @Override
        public void onTick(long millisUntilFinished) {

        }
    }
}