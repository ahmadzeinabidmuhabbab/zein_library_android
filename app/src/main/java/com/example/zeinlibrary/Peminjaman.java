package com.example.zeinlibrary;

import com.google.gson.annotations.SerializedName;

public class Peminjaman {
    @SerializedName("id")
    public int id;
    @SerializedName("judul")
    public String judul;
    @SerializedName("foto")
    public String foto;
    @SerializedName("tanggal_pinjam")
    public String tanggalPinjam;
    @SerializedName("tanggal_kembali")
    public String tanggalKembali;
    @SerializedName("status_peminjaman")
    public String statusPeminjaman;

    public Peminjaman(int id, String judul, String foto, String tanggalPinjam, String tanggalKembali, String statusPeminjaman) {
        this.id = id;
        this.judul = judul;
        this.foto = foto;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.statusPeminjaman = statusPeminjaman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getStatusPeminjaman() {
        return statusPeminjaman;
    }

    public void setStatusPeminjaman(String statusPeminjaman) {
        this.statusPeminjaman = statusPeminjaman;
    }
}
