package com.example.asus.curdsederhana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditData extends Activity implements OnClickListener {

    private DBDataSource dataSource;

    private long id;
    private String telepon;
    private String alamat;
    private String nama;

    private EditText edNama;
    private EditText edTelepon;
    private EditText edAlamat;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Konsumen konsumen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        //inisialisasi variabel
        edNama = (EditText) findViewById(R.id.editText_nama);
        edAlamat = (EditText) findViewById(R.id.editText_alamat);
        edTelepon = (EditText) findViewById(R.id.editText_telepon);
        txId = (TextView) findViewById(R.id.text_id_konsumen);
        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();
        // ambil data barang dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        telepon = bun.getString("telepon");
        alamat = bun.getString("alamat");
        nama = bun.getString("nama");
        //masukkan data-data barang tersebut ke field editor
        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edAlamat.setText(alamat);
        edTelepon.setText(telepon);

        //set listener pada tombol
        btnSave = (Button) findViewById(R.id.button_save_update);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update barang)
            case R.id.button_save_update :
                konsumen = new Konsumen();
                konsumen.setNama_konsumen(edNama.getText().toString());
                konsumen.setAlamat_konsumen(edAlamat.getText().toString());
                konsumen.setTelepon_konsumen(edTelepon.getText().toString());
                konsumen.setId(id);
                dataSource.updateKonsumen(konsumen);
                Intent i = new Intent(this, ViewData.class);
                startActivity(i);
                EditData.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_update :
                finish();
                dataSource.close();
                break;
        }
    }
}
