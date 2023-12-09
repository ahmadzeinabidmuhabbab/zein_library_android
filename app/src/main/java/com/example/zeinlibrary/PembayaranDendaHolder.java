package com.example.zeinlibrary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PembayaranDendaHolder extends RecyclerView.ViewHolder {
    public TextView txt_judul, txt_username, txt_ISBN, txt_tanggalPinjam, txt_tanggalKembali, txt_denda;
    public ImageView img_icon;


    public PembayaranDendaHolder(View itemView) {
        super(itemView);

        /*mangenalkan objek*/
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_username = (TextView) itemView.findViewById(R.id.txt_username);
        txt_ISBN = (TextView) itemView.findViewById(R.id.txt_ISBN);
        txt_tanggalPinjam = (TextView) itemView.findViewById(R.id.txt_tanggalPinjam);
        txt_tanggalKembali = (TextView) itemView.findViewById(R.id.txt_tanggalKembali);
        txt_denda = (TextView) itemView.findViewById(R.id.txt_denda);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}
