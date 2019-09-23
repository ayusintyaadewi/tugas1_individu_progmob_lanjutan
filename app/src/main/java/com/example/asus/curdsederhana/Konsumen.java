package com.example.asus.curdsederhana;

public class Konsumen {

    private long id;
    private String nama_konsumen;
    private String alamat_konsumen;
    private String telepon_konsumen;

    public Konsumen(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_konsumen(){
        return nama_konsumen;
    }

    public void setNama_konsumen(String nama_konsumen) {
        this.nama_konsumen = nama_konsumen;
    }

    public String getAlamat_konsumen(){
        return alamat_konsumen;
    }

    public void setAlamat_konsumen(String alamat_konsumen) {
        this.alamat_konsumen = alamat_konsumen;
    }

    public String getTelepon_konsumen(){
        return telepon_konsumen;
    }

    public void setTelepon_konsumen(String telepon_konsumen) {
        this.telepon_konsumen = telepon_konsumen;
    }

    @Override
    public String toString(){
        return "\n" + "Nama      : " + nama_konsumen + "\n" + "Alamat    : " + alamat_konsumen + "\n" + "Telepon  : " + telepon_konsumen + "\n";
    }
}
