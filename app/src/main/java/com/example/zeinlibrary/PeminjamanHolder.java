package com.example.zeinlibrary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PeminjamanHolder extends RecyclerView.ViewHolder {
    public TextView txt_judul, txt_tanggalPinjam, txt_tanggalKembali, txt_statusPeminjaman;
    public ImageView img_icon;


    public PeminjamanHolder(View itemView) {
        super(itemView);

        /*mangenalkan objek*/
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_tanggalPinjam = (TextView) itemView.findViewById(R.id.txt_tanggalPinjam);
        txt_tanggalKembali = (TextView) itemView.findViewById(R.id.txt_tanggalKembali);
        txt_statusPeminjaman = (TextView) itemView.findViewById(R.id.txt_status);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}