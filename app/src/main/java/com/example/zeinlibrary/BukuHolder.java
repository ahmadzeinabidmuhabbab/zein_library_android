package com.example.zeinlibrary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BukuHolder extends RecyclerView.ViewHolder {
    public TextView txt_judul, txt_ISBN, txt_stok, txt_bidang;
    public ImageView img_icon;


    public BukuHolder(View itemView) {
        super(itemView);

        /*mangenalkan objek*/
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_bidang = (TextView) itemView.findViewById(R.id.txt_bidang);
        txt_ISBN = (TextView) itemView.findViewById(R.id.txt_ISBN);
        txt_stok = (TextView) itemView.findViewById(R.id.txt_stok);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}
