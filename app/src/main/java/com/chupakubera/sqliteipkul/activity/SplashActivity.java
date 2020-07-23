package com.chupakubera.sqliteipkul.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.activity.matkul.DaftarMatkulActivity;

public class SplashActivity extends Activity {
    private int loading_time = 2000;    // 2 detik waktu loading screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // loading screen 4 detik lalu pindah ke home
                Intent home = new Intent(SplashActivity.this, DaftarMatkulActivity.class);
                startActivity(home);
                finish();

            }
        }, loading_time);
    }
}
