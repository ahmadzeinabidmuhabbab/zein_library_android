package com.example.zeinlibrary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DendaHolder extends RecyclerView.ViewHolder {
    public TextView txt_judul, txt_jumlahDenda, txt_tanggalPembayaran, txt_statusPembayaran, txt_tanggalPinjam, txt_tanggalKembali;
    public ImageView img_icon;


    public DendaHolder(View itemView) {
        super(itemView);

        /*mangenalkan objek*/
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_jumlahDenda = (TextView) itemView.findViewById(R.id.txt_jumlahDenda);
        txt_tanggalPinjam = (TextView) itemView.findViewById(R.id.txt_tanggalPinjam);
        txt_tanggalKembali = (TextView) itemView.findViewById(R.id.txt_tanggalKembali);
        txt_tanggalPembayaran = (TextView) itemView.findViewById(R.id.txt_tanggalPembayaran);
        txt_statusPembayaran = (TextView) itemView.findViewById(R.id.txt_statusPembayaran);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}
