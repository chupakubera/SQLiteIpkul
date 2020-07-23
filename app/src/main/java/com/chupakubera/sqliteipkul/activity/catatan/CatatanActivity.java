package com.chupakubera.sqliteipkul.activity.catatan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.activity.jadwal.JadwalActivity;
import com.chupakubera.sqliteipkul.activity.matkul.DaftarMatkulActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CatatanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);

        TextView title = (TextView) findViewById(R.id.activityTitle2);
        title.setText("Activity Catatan");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(CatatanActivity.this, DaftarMatkulActivity.class);
                        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(a);
                        break;
                    case R.id.navigation_jadwal:
                        Intent b = new Intent(CatatanActivity.this, JadwalActivity.class);
                        b.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(b);
                        break;
                    case R.id.navigation_catatan:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
