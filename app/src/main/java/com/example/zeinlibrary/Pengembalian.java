package com.example.zeinlibrary;

import com.google.gson.annotations.SerializedName;

public class Pengembalian {
    @SerializedName("id")
    public int id;
    @SerializedName("username")
    public String username;
    @SerializedName("ISBN")
    public String ISBN;
    @SerializedName("tanggal_pinjam")
    public String tanggal_pinjam;
    @SerializedName("judul")
    public String judul;
    @SerializedName("foto")
    public String foto;

    public Pengembalian(int id, String username, String iSBN, String tanggal_pinjam, String judul, String foto) {
        this.id = id;
        this.username = username;
        this.ISBN = iSBN;
        this.tanggal_pinjam = tanggal_pinjam;
        this.judul = judul;
        this.foto = foto;
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTanggalPinjam() {
        return tanggal_pinjam;
    }

    public void setTanggalPinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
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
}
