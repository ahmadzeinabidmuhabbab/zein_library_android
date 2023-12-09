package com.example.zeinlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DendaAdapter extends RecyclerView.Adapter<DendaHolder>{
    Context context;
    List<Denda> itemObjects;

    public DendaAdapter(Context context, List<Denda> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public DendaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.denda_carditem, null);
        DendaHolder holderItem=new DendaHolder(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(DendaHolder holder, int position) {
        Denda modal = itemObjects.get(position);
        holder.txt_judul.setText(itemObjects.get(position).getJudul());
        holder.txt_jumlahDenda.setText("Jumlah Denda : "+itemObjects.get(position).getDenda());
        String tanggalBayar;
        if(itemObjects.get(position).getTanggal_pembayaran().contains("0000-00-00 00:00:00")) tanggalBayar = "-";
        else tanggalBayar = itemObjects.get(position).getTanggal_pembayaran();
        holder.txt_tanggalPinjam.setText("Tanggal Pinjam : "+itemObjects.get(position).getTanggal_pinjam());
        holder.txt_tanggalKembali.setText("Tanggal Kembali : "+itemObjects.get(position).getTanggal_kembali());
        holder.txt_tanggalPembayaran.setText("Tanggal Bayar : "+tanggalBayar);
        holder.txt_statusPembayaran.setText(String.valueOf("Status : "+itemObjects.get(position).getStatus_denda()));
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
