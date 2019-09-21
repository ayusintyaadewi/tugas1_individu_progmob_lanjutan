package com.example.asus.curdsederhana;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSource {

    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_ALAMAT,DBHelper.COLUMN_TELEPON};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert barang ke database
    public Konsumen createKonsumen(String nama, String alamat, String telepon) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA, nama);
        values.put(DBHelper.COLUMN_ALAMAT, alamat);
        values.put(DBHelper.COLUMN_TELEPON, telepon);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek barang
        Konsumen newKonsumen = cursorToKonsumen(cursor);

        // close cursor
        cursor.close();

        // mengembalikan barang baru
        return newKonsumen;
    }

    private Konsumen cursorToKonsumen(Cursor cursor)
    {
        // buat objek barang baru
        Konsumen konsumen = new Konsumen();
        // debug LOGCAT
        Log.v("info", "The getLONG "+cursor.getLong(0));
        Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));

        /* Set atribut pada objek barang dengan
         * data kursor yang diambil dari database*/
        konsumen.setId(cursor.getLong(0));
        konsumen.setNama_konsumen(cursor.getString(1));
        konsumen.setAlamat_konsumen(cursor.getString(2));
        konsumen.setTelepon_konsumen(cursor.getString(3));

        //kembalikan sebagai objek barang
        return konsumen;
    }
}
