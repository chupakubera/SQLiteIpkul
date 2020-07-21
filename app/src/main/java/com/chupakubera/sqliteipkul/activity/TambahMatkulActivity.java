package com.chupakubera.sqliteipkul.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.database.DBManager;

public class TambahMatkulActivity extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText matkulEditText;
    private EditText sksEditText;
    private EditText indeksEditText;
    private EditText semesterEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Tambah Matakuliah");

        setContentView(R.layout.activity_tambah_matkul);

        matkulEditText = (EditText) findViewById(R.id.matkul_edittext);
        sksEditText = (EditText) findViewById(R.id.sks_edittext);
        indeksEditText = (EditText) findViewById(R.id.indeks_edittext);
        semesterEditText = (EditText) findViewById(R.id.semester_edittext);

        addTodoBtn = (Button) findViewById(R.id.add_matkul);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_matkul:

                final String matkul = matkulEditText.getText().toString();
                final int sks = Integer.parseInt(sksEditText.getText().toString());
                final String indeks = indeksEditText.getText().toString();
                final String semester = semesterEditText.getText().toString();

                dbManager.insertMatkul(matkul, sks, indeks, semester);

                Intent main = new Intent(TambahMatkulActivity.this, DaftarMatkulActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }


}
