package com.example.zeinlibrary;

public class ManageAkun {
    int id;
    String username, role, tanggalBergabung, foto;

    public ManageAkun(int id, String username, String role, String tanggalBergabung, String foto) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.tanggalBergabung = tanggalBergabung;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTanggalBergabung() {
        return tanggalBergabung;
    }

    public void setTanggalBergabung(String tanggalBergabung) {
        this.tanggalBergabung = tanggalBergabung;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
