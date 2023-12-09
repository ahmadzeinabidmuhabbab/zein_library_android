package com.example.zeinlibrary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PengembalianHolder extends RecyclerView.ViewHolder {
    public TextView txt_judul, txt_tanggalPinjam, txt_username, txt_ISBN;
    public ImageView img_icon;


    public PengembalianHolder(View itemView) {
        super(itemView);

        /*mangenalkan objek*/
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_tanggalPinjam = (TextView) itemView.findViewById(R.id.txt_tanggalPinjam);
        txt_username = (TextView) itemView.findViewById(R.id.txt_username);
        txt_ISBN = (TextView) itemView.findViewById(R.id.txt_ISBN);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}