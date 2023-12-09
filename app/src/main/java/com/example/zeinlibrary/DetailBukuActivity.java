package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


public class DetailBukuActivity extends AppCompatActivity {
    TextView judul, ISBN, penulis, penerbit, bidang, stok, deskripsi;
    ImageView foto;
    String value_judul, value_ISBN, value_penulis, value_penerbit, value_bidang,  value_deskripsi, value_foto;
    int value_id, value_stok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);
        judul = (TextView) findViewById(R.id.judul);
        ISBN = (TextView) findViewById(R.id.ISBN);
        penulis = (TextView) findViewById(R.id.penulis);
        penerbit = (TextView) findViewById(R.id.penerbit);
        bidang = (TextView) findViewById(R.id.bidang);
        stok = (TextView) findViewById(R.id.stok);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        foto = (ImageView) findViewById(R.id.foto);

        Intent i = getIntent();
        value_id = i.getIntExtra("id",1);
        value_judul = i.getStringExtra("judul");
        value_ISBN = i.getStringExtra("ISBN");
        value_penulis = i.getStringExtra("penulis");
        value_penerbit = i.getStringExtra("penerbit");
        value_bidang = i.getStringExtra("bidang");
        value_stok = i.getIntExtra("stok",0);
        value_deskripsi = i.getStringExtra("deskripsi");
        value_foto = i.getStringExtra("foto");

        judul.setText(value_judul);
        ISBN.setText(value_ISBN);
        penulis.setText(value_penulis);
        penerbit.setText(value_penerbit);
        bidang.setText(value_bidang);
        stok.setText(String.valueOf(value_stok));
        deskripsi.setText(value_deskripsi);
        Glide.with(this)
                .load(value_foto)
                .placeholder(R.drawable.no_book)
                .into(foto);
    }

    public void onBackPressed() {
        finish();
    }
}