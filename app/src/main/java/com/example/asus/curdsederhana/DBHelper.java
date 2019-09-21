package com.example.asus.curdsederhana;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "data_konsumen";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA = "nama_konsumen";
    public static final String COLUMN_ALAMAT = "alamat_konsumen";
    public static final String COLUMN_TELEPON = "telepon_konsumen";
    private static final String db_name ="konsumen.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID +" integer primary key autoincrement, "
            + COLUMN_NAMA + " varchar(50) not null, "
            + COLUMN_ALAMAT + " varchar(50) not null, "
            + COLUMN_TELEPON + " varchar(50) not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
