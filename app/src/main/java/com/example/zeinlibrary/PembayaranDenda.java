package com.example.zeinlibrary;

import com.google.gson.annotations.SerializedName;

public class PembayaranDenda {
    @SerializedName("id")
    public int id;
    @SerializedName("username")
    public String username;
    @SerializedName("judul")
    public String judul;
    @SerializedName("ISBN")
    public String ISBN;
    @SerializedName("foto")
    public String foto;
    @SerializedName("tanggal_pinjam")
    public String tanggalPinjam;
    @SerializedName("tanggal_kembali")
    public String tanggalKembali;
    @SerializedName("denda")
    public int denda;

    public PembayaranDenda(int id, String username, String judul, String ISBN, String foto, String tanggalPinjam, String tanggalKembali, int denda) {
        this.id = id;
        this.username = username;
        this.judul = judul;
        this.ISBN = ISBN;
        this.foto = foto;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.denda = denda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }
}
