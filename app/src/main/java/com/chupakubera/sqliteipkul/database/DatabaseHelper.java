package com.chupakubera.sqliteipkul.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // name table TABLE_MATKUL
    public static final String TABLE_MATKUL = "DAFTAR_MATKUL";

    // column table TABLE_MATKUL
    public static final String _ID = "_id";
    public static final String MATKUL = "matkul";
    public static final String SKS = "sks";
    public static final String INDEKS = "indeks";
    public static final String SEMESTER = "semester";
    public static final String BOBOT = "bobot";

    // name database
    static final String DB_NAME = "IP_KULIAH.DB";

    // database version
    static final int DB_VERSION = 1;

    // make query for table TABLE_MATKUL
    private static final String CREATE_TABLE1 = "create table " + TABLE_MATKUL + "(" +
                            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            MATKUL + " TEXT, " +
                            SKS + " INTEGER, " +
                            INDEKS + " TEXT, " +
                            SEMESTER + " TEXT, " +
                            BOBOT + " REAL " +
                            ");";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATKUL);
        onCreate(db);
    }
}
