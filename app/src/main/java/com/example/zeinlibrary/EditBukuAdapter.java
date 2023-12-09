package com.example.zeinlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EditBukuAdapter extends RecyclerView.Adapter<BukuHolder>{
    Context context;
    List<Buku> itemObjects;

    public EditBukuAdapter(Context context, List<Buku> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public BukuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buku_carditem, null);
        BukuHolder holderItem=new BukuHolder(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(BukuHolder holder, int position) {
        Buku modal = itemObjects.get(position);
        holder.txt_judul.setText(itemObjects.get(position).getJudul());
        holder.txt_ISBN.setText("ISBN : "+itemObjects.get(position).getISBN());
        holder.txt_bidang.setText("Bidang : "+itemObjects.get(position).getBidang());
        holder.txt_stok.setText(String.valueOf("Stok : "+itemObjects.get(position).getStok()));
        Glide.with(context)
                .load(itemObjects.get(position).getFoto())
                .placeholder(R.drawable.no_book)
                .into(holder.img_icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditInputBukuActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id", modal.getId());
                i.putExtra("judul", modal.getJudul());
                i.putExtra("ISBN", modal.getISBN());
                i.putExtra("penulis", modal.getPenulis());
                i.putExtra("penerbit", modal.getPenerbit());
                i.putExtra("bidang", modal.getBidang());
                i.putExtra("stok", modal.getStok());
                i.putExtra("deskripsi", modal.getDeskripsi());
                i.putExtra("foto", modal.getFoto());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }
}
