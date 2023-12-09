package com.example.zeinlibrary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PeminjamanAdapter extends RecyclerView.Adapter<PeminjamanHolder>{
    Context context;
    List<Peminjaman> itemObjects;

    public PeminjamanAdapter(Context context, List<Peminjaman> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public PeminjamanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.peminjaman_carditem, null);
        PeminjamanHolder holderItem=new PeminjamanHolder(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(PeminjamanHolder holder, int position) {
        Peminjaman modal = itemObjects.get(position);
        holder.txt_judul.setText(itemObjects.get(position).getJudul());
        holder.txt_tanggalPinjam.setText("Tanggal Pinjam : "+itemObjects.get(position).getTanggalPinjam());
        String tanggal_kembali;
        if(String.valueOf(itemObjects.get(position).getTanggalKembali()).contains("0000-00-00 00:00:00")) tanggal_kembali="-";
        else tanggal_kembali = itemObjects.get(position).getTanggalKembali();
        holder.txt_tanggalKembali.setText("Tanggal Kembali : "+tanggal_kembali);
        holder.txt_statusPeminjaman.setText(String.valueOf("Status : "+itemObjects.get(position).getStatusPeminjaman()));
        Glide.with(context)
                .load(itemObjects.get(position).getFoto())
                .placeholder(R.drawable.no_book)
                .into(holder.img_icon);
    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }
}

