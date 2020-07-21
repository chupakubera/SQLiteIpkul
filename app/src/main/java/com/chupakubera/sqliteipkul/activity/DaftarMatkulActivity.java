package com.chupakubera.sqliteipkul.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chupakubera.sqliteipkul.R;
import com.chupakubera.sqliteipkul.database.DBManager;
import com.chupakubera.sqliteipkul.database.DatabaseHelper;

public class DaftarMatkulActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    public static int sksTotal;

    final String[] from = new String[] { DatabaseHelper._ID, DatabaseHelper.MATKUL,
            DatabaseHelper.SKS, DatabaseHelper.INDEKS, DatabaseHelper.SEMESTER, DatabaseHelper.BOBOT };

    final int[] to = new int[] {R.id.id, R.id.matkul, R.id.sks, R.id.indeks, R.id.semester, R.id.bobot};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_daftar_matkul);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_matkul, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener listview daftar matakuliah
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView matkulTextView = (TextView) view.findViewById(R.id.matkul);
                TextView sksTextView = (TextView) view.findViewById(R.id.sks);
                TextView indeksTextView = (TextView) view.findViewById(R.id.indeks);
                TextView semesterTextView = (TextView) view.findViewById(R.id.semester);
                TextView jumlah_sksTextView = (TextView) view.findViewById(R.id.bobot);

                String _id = idTextView.getText().toString();
                String matkul = matkulTextView.getText().toString();
                String sks = sksTextView.getText().toString();
                String indeks = indeksTextView.getText().toString();
                String semester = semesterTextView.getText().toString();
                String jumlah_sks = jumlah_sksTextView.getText().toString();


                Intent update_intent = new Intent(getApplicationContext(), UpdateMatkulActivity.class);
                update_intent.putExtra("id", _id);
                update_intent.putExtra("matkul", matkul);
                update_intent.putExtra("sks", sks);
                update_intent.putExtra("indeks", indeks);
                update_intent.putExtra("semester", semester);
                update_intent.putExtra("jumlah_sks", jumlah_sks);

                startActivity(update_intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_matkul) {

            Intent add_mem = new Intent(this, TambahMatkulActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}