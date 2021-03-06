package com.example.autok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "autok.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "autok";
    private static final String COL_ID = "id";
    private static final String COL_GYARTO = "gyarto";
    private static final String COL_MODELL = "modell";
    private static final String COL_UZEMBE = "uzembe";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME +" (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_GYARTO + " TEXT NOT NULL, " +
                COL_MODELL + " TEXT NOT NULL, " +
                COL_UZEMBE + " INTEGER NOT NULL " +
                ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String gyarto, String modell, int uzembe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_GYARTO, gyarto);
        values.put(COL_MODELL, modell);
        values.put(COL_UZEMBE, uzembe);
        return db.insert(TABLE_NAME, null, values) != -1;
    }

    public Cursor modellKeres(String gyarto){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT "+COL_MODELL+" FROM "+TABLE_NAME+" WHERE "+COL_GYARTO+" = ?", new String[]{gyarto});
    }
}
