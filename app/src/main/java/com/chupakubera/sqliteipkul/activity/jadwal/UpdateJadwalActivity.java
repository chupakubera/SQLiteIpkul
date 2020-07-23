package com.chupakubera.sqliteipkul.activity.jadwal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.activity.matkul.DaftarMatkulActivity;
import com.chupakubera.sqliteipkul.database.DBManager;

public class UpdateJadwalActivity extends Activity implements View.OnClickListener {
    private EditText matkulText;
    private EditText hariText;
    private EditText waktuText;
    private EditText ruanganText;

    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Jadwal");
        setContentView(R.layout.activity_update_jadwal);

        dbManager = new DBManager(this);
        dbManager.open();

        matkulText = (EditText) findViewById(R.id.matkulJadwal_edittext);
        hariText = (EditText) findViewById(R.id.hari_edittext);
        waktuText = (EditText) findViewById(R.id.waktu_edittext);
        ruanganText = (EditText) findViewById(R.id.ruangan_edittext);


        updateBtn = (Button) findViewById(R.id.btn_update_jadwal);
        deleteBtn = (Button) findViewById(R.id.btn_delete_jadwal);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String matkul = intent.getStringExtra("matkul");
        String hari = intent.getStringExtra("hari");
        String waktu = intent.getStringExtra("waktu");
        String ruangan = intent.getStringExtra("ruangan");

        _id = Long.parseLong(id);

        matkulText.setText(matkul);
        hariText.setText(hari);
        waktuText.setText(waktu);
        ruanganText.setText(ruangan);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_matkul:
                String matkul = matkulText.getText().toString();
                String hari = matkulText.getText().toString();
                String waktu = waktuText.getText().toString();
                String ruangan = ruanganText.getText().toString();

                dbManager.updateJadwal(_id, matkul, hari, waktu, ruangan);
                this.returnHome();
                break;

            case R.id.btn_delete_matkul:
                dbManager.deleteJadwal(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), DaftarMatkulActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
