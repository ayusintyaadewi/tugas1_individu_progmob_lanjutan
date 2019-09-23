package com.example.asus.curdsederhana;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateData extends Activity implements OnClickListener{

    //inisilisasi elemen-elemen pada layout
    private Button buttonSubmit;
    private EditText edNama;
    private EditText edAlamat;
    private EditText edTelepon;

    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);

        buttonSubmit = (Button) findViewById(R.id.buttom_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_konsumen);
        edAlamat = (EditText) findViewById(R.id.alamat_konsumen);
        edTelepon = (EditText) findViewById(R.id.telepon_konsumen);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }

    //KETIKA Tombol Submit Diklik
    @Override
    public void onClick(View v) {
        // Inisialisasi data konsumen
        String nama = null;
        String alamat = null;
        String telepon = null;
        @SuppressWarnings("unused")

        //inisialisasi konsumen baru (masih kosong)
                Konsumen konsumen = null;
        if (edNama.getText() != null && edAlamat.getText() != null && edTelepon.getText() != null) {
            /* jika field nama, alamat, dan telepon tidak kosong
             * maka masukkan ke dalam data barang*/
            nama = edNama.getText().toString();
            alamat = edAlamat.getText().toString();
            telepon = edTelepon.getText().toString();
        }

        switch (v.getId()) {
            case R.id.buttom_submit:
                // insert data konsumen baru
                konsumen = dataSource.createKonsumen(nama, alamat, telepon);

                //konfirmasi kesuksesan
                Toast.makeText(this, "data berhasil dimasukkan"
//                        +
//                        "nama" + konsumen.getNama_konsumen() +
//                        "alamat" + konsumen.getAlamat_konsumen() +
//                        "telepon" + konsumen.getTelepon_konsumen()
                        , Toast.LENGTH_LONG).show();
                break;
        }
    }
}
