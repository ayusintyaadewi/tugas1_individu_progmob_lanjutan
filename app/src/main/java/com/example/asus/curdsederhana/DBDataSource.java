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
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_ALAMAT, DBHelper.COLUMN_TELEPON};

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

    //mengambil semua data barang
    public ArrayList<Konsumen> getAllKonsumen() {
        ArrayList<Konsumen> daftarKonsumen = new ArrayList<Konsumen>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data konsumen ke
        // daftar konsumen
        while (!cursor.isAfterLast()) {
            Konsumen konsumen = cursorToKonsumen(cursor);
            daftarKonsumen.add(konsumen);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarKonsumen;
    }

    //ambil satu konsumen sesuai id
    public Konsumen getKonsumen(long id)
    {
        Konsumen konsumen = new Konsumen(); //inisialisasi barang
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id ="+id, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek konsumen
        konsumen = cursorToKonsumen(cursor);
        //tutup sambungan
        cursor.close();
        //return konsumen
        return konsumen;
    }

    //update barang yang diedit
    public void updateKonsumen(Konsumen k)
    {
        //ambil id barang
        String strFilter = "_id=" + k.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAMA, k.getNama_konsumen());
        args.put(DBHelper.COLUMN_ALAMAT, k.getAlamat_konsumen());
        args.put(DBHelper.COLUMN_TELEPON, k.getTelepon_konsumen() );
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    // delete barang sesuai ID
    public void deleteKonsumen(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
