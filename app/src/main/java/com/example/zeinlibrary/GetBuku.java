package com.example.zeinlibrary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBuku {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Buku> listDataBuku;
    @SerializedName("messages")
    String messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Buku> getListDataBuku() {
        return listDataBuku;
    }

    public void setListDataBuku(List<Buku> listDataBuku) {
        this.listDataBuku = listDataBuku;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
