package com.chupakubera.sqliteipkul.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // method to get sum value of SKS column
    public int getSumSks() {
        int total = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + DatabaseHelper.SKS + ") as Total FROM " + DatabaseHelper.TABLE_MATKUL, null);
        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    public void insertMatkul(String matkul, int sks, String indeks, String semester) {
        int jumlahSksTemp = getSumSks() + sks;
        String jumlah_sks = Integer.toString(jumlahSksTemp);

        double bobotNilai = getBobot(indeks, sks);

        ContentValues contentValuesMatkul = new ContentValues();
        contentValuesMatkul.put(DatabaseHelper.MATKUL, matkul);
        contentValuesMatkul.put(DatabaseHelper.SKS, sks);
        contentValuesMatkul.put(DatabaseHelper.INDEKS, indeks);
        contentValuesMatkul.put(DatabaseHelper.SEMESTER, semester);
        contentValuesMatkul.put(DatabaseHelper.BOBOT, bobotNilai);
        database.insert(DatabaseHelper.TABLE_MATKUL, null, contentValuesMatkul);

    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.MATKUL, DatabaseHelper.SKS,
                                          DatabaseHelper.INDEKS, DatabaseHelper.SEMESTER, DatabaseHelper.BOBOT};
        Cursor cursor = database.query(DatabaseHelper.TABLE_MATKUL, columns, null, null, null, null, DatabaseHelper.SEMESTER);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateMatkul(long _id, String matkul, int sks, String indeks, String semester) {

        double bobotNilai = getBobot(indeks, sks);
        //double bobot = bobotNilai * sks;

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MATKUL, matkul);
        contentValues.put(DatabaseHelper.SKS, sks);
        contentValues.put(DatabaseHelper.INDEKS, indeks);
        contentValues.put(DatabaseHelper.SEMESTER, semester);
        contentValues.put(DatabaseHelper.BOBOT, bobotNilai);
        int i = database.update(DatabaseHelper.TABLE_MATKUL, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void deleteMatkul(long _id) {
        database.delete(DatabaseHelper.TABLE_MATKUL, DatabaseHelper._ID + "=" + _id, null);
    }

    // method to get index convertion multiply with sks value
    public double getBobot(String nilai, int sks) {
        double bobot;
        if (nilai.equals("A") || nilai.equals("a")) {
            bobot =   4.0;
        }
        else if (nilai.equals("AB") || nilai.equals("Ab") || nilai.equals("aB") || nilai.equals("ab") || nilai.equals("BA") || nilai.equals("Ba") || nilai.equals("bA") || nilai.equals("ba") ||
                nilai.equals("B+") || nilai.equals("b+")) {
            bobot = 3.5;
        }
        else if (nilai.equals("B") || nilai.equals("b")) {
            bobot = 3.0;
        }
        else if (nilai.equals("BC") || nilai.equals("Bc") || nilai.equals("bC") || nilai.equals("bc") || nilai.equals("CB") || nilai.equals("Cb") || nilai.equals("cB") || nilai.equals("cb") ||
                nilai.equals("C+") || nilai.equals("c+")) {
            bobot = 2.5;
        }
        else if (nilai.equals("C") || nilai.equals("c")) {
            bobot = 2.0;
        }
        else if (nilai.equals("CD") || nilai.equals("Cd") || nilai.equals("cD") || nilai.equals("cd") || nilai.equals("DC") || nilai.equals("Dc") || nilai.equals("dC") || nilai.equals("dc") ||
                nilai.equals("D+") || nilai.equals("d+")) {
            bobot = 1.5;
        }
        else if (nilai.equals("D") || nilai.equals("d")) {
            bobot = 1.0;
        }
        else if (nilai.equals("DE") || nilai.equals("De") || nilai.equals("dE") || nilai.equals("de") || nilai.equals("ED") || nilai.equals("Ed") || nilai.equals("eD") || nilai.equals("ed") ||
                nilai.equals("E+") || nilai.equals("e+")) {
            bobot = 0.5;
        }
        else if (nilai.equals("E") || nilai.equals("e")) {
            bobot = 0;
        } else {
            bobot = 0;
        }
        return bobot * sks;
    }

}
