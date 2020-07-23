package com.chupakubera.sqliteipkul.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // name table
    public static final String TABLE_MATKUL = "DAFTAR_MATKUL";
    public static final String TABLE_JADWAL = "JADWAL_KULIAH";

    // column table TABLE_MATKUL
    public static final String _ID = "_id";
    public static final String MATKUL = "matkul";
    public static final String SKS = "sks";
    public static final String INDEKS = "indeks";
    public static final String SEMESTER = "semester";
    public static final String BOBOT = "bobot";

    // column table TABLE_JADWAL
    public static final String MATKUL_JADWAL = "matkulJadwal";
    public static final String HARI = "hari";
    public static final String WAKTU = "waktu";
    public static final String RUANGAN = "ruangan";

    // name database
    static final String DB_NAME = "IP_KULIAH.DB";

    // database version
    static final int DB_VERSION = 1;

    // make query for table TABLE_MATKUL
    private static final String CREATE_TABLE_MATKUL = "create table " + TABLE_MATKUL + "(" +
                            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            MATKUL + " TEXT, " +
                            SKS + " INTEGER, " +
                            INDEKS + " TEXT, " +
                            SEMESTER + " TEXT, " +
                            BOBOT + " REAL " +
                            ");";

    // make query for table TABLE_JADWAL
    private static final String CREATE_TABLE_JADWAL = "create table " + TABLE_JADWAL + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MATKUL_JADWAL + " TEXT, " +
            HARI + " TEXT, " +
            WAKTU + " TEXT, " +
            RUANGAN + " TEXT " +
            ");";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MATKUL);
        db.execSQL(CREATE_TABLE_JADWAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATKUL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JADWAL);
        onCreate(db);
    }
}
