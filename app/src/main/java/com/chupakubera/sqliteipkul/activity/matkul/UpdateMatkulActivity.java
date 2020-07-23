package com.chupakubera.sqliteipkul.activity.matkul;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.database.DBManager;


public class UpdateMatkulActivity extends Activity implements View.OnClickListener {

    private EditText matkulText;
    private EditText sksText;
    private EditText indeksText;
    private EditText semesterText;

    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Matkul");
        setContentView(R.layout.activity_update_matkul);

        dbManager = new DBManager(this);
        dbManager.open();

        matkulText = (EditText) findViewById(R.id.matkul_edittext);
        sksText = (EditText) findViewById(R.id.sks_edittext);
        indeksText = (EditText) findViewById(R.id.indeks_edittext);
        semesterText = (EditText) findViewById(R.id.semester_edittext);


        updateBtn = (Button) findViewById(R.id.btn_update_matkul);
        deleteBtn = (Button) findViewById(R.id.btn_delete_matkul);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String matkul = intent.getStringExtra("matkul");
        String sks = intent.getStringExtra("sks");
        String indeks = intent.getStringExtra("indeks");
        String semester = intent.getStringExtra("semester");
        String jumlah_sks = intent.getStringExtra("jumlah_sks");

        _id = Long.parseLong(id);

        matkulText.setText(matkul);
        sksText.setText(sks);
        indeksText.setText(indeks);
        semesterText.setText(semester);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_matkul:
                String matkul = matkulText.getText().toString();
                int sks = Integer.parseInt(sksText.getText().toString());
                String indeks = indeksText.getText().toString();
                String semester = semesterText.getText().toString();

                dbManager.updateMatkul(_id, matkul, sks, indeks, semester);
                this.returnHome();
                break;

            case R.id.btn_delete_matkul:
                dbManager.deleteMatkul(_id);
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
