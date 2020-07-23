package com.chupakubera.sqliteipkul.activity.matkul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.database.DBManager;

import java.text.DecimalFormat;

public class TampilkanIpkActivity extends Activity{

    private TextView total_sks;
    private TextView total_bobot;
    private TextView ipk;
    private int totalSks;
    private double totalBobot;
    private double hasil_ipk;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Statistik Perkuliahan");
        setContentView(R.layout.activity_tampilkan_ipk);

        // declare and open database
        dbManager = new DBManager(this);
        dbManager.open();
        totalSks = dbManager.getSumSks();
        totalBobot = dbManager.getSumBobot();
        hasil_ipk = totalBobot / totalSks;

        total_sks = (TextView) findViewById(R.id.total_sks);
        total_sks.setText(Integer.toString(totalSks));
        total_bobot = (TextView) findViewById(R.id.total_bobot);
        total_bobot.setText(Double.toString(totalBobot));
        ipk = (TextView) findViewById(R.id.ipk);
        ipk.setText(String.format("%.2f", hasil_ipk));

        // close database
        dbManager.close();

        Button closeButton = (Button) findViewById(R.id.exit);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO:
                // This function closes Activity TampilkanIpkActivity
                finish();
            }
        });
    }
}
