package com.example.zeinlibrary;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Buku {
    @SerializedName("id")
    int id;
    @SerializedName("stok")
    int stok;
    @SerializedName("judul")
    private String judul;
    @SerializedName("penulis")
    private String penulis;
    @SerializedName("ISBN")
    private String ISBN;
    @SerializedName("penerbit")
    private String penerbit;
    @SerializedName("bidang")
    private String bidang;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("foto")
    private String foto;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Buku(int id, int stok, String judul, String penulis, String ISBN, String penerbit, String bidang, String deskripsi, String foto, String createdAt, String updatedAt) {
        this.id = id;
        this.stok = stok;
        this.judul = judul;
        this.penulis = penulis;
        this.ISBN = ISBN;
        this.penerbit = penerbit;
        this.bidang = bidang;
        this.deskripsi = deskripsi;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
