package com.chupakubera.sqliteipkul.activity.jadwal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.database.DBManager;

public class TambahJadwalActivity extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText matkulEditText;
    private EditText hariEditText;
    private EditText waktuEditText;
    private EditText ruanganEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tambah Jadwal");
        setContentView(R.layout.activity_tambah_jadwal);

        matkulEditText = (EditText) findViewById(R.id.matkulJadwal_edittext);
        hariEditText = (EditText) findViewById(R.id.hari_edittext);
        waktuEditText = (EditText) findViewById(R.id.waktu_edittext);
        ruanganEditText = (EditText) findViewById(R.id.ruangan_edittext);

        addTodoBtn = (Button) findViewById(R.id.add_jadwal);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_jadwal:

                final String matkul = matkulEditText.getText().toString();
                final String hari = hariEditText.getText().toString();
                final String waktu = waktuEditText.getText().toString();
                final String ruangan = ruanganEditText.getText().toString();

                dbManager.insertJadwal(matkul, hari, waktu, ruangan);

                Intent main = new Intent(TambahJadwalActivity.this, JadwalActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
