package com.example.zeinlibrary;

public class Denda {
    public int id;
    public String judul;
    public String foto;
    public int denda;
    public String tanggal_pinjam;
    public String tanggal_kembali;
    public String tanggal_pembayaran;
    public String status_denda;

    public Denda(int id, String judul, String foto, int denda, String tanggal_pinjam, String tanggal_kembali, String tanggal_pembayaran, String status_denda) {
        this.id = id;
        this.judul = judul;
        this.foto = foto;
        this.denda = denda;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
        this.tanggal_pembayaran = tanggal_pembayaran;
        this.status_denda = status_denda;
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

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    public void setTanggal_pinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public String getTanggal_pembayaran() {
        return tanggal_pembayaran;
    }

    public void setTanggal_pembayaran(String tanggal_pembayaran) {
        this.tanggal_pembayaran = tanggal_pembayaran;
    }

    public String getStatus_denda() {
        return status_denda;
    }

    public void setStatus_denda(String status_denda) {
        this.status_denda = status_denda;
    }
}
