package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBukuActvity extends AppCompatActivity {
    private RecyclerView list_item;
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private List<Buku> itemObjects;
    private BukuAdapter adapter;
    ApiInterface mApiInterface;
    public static DaftarBukuActvity ma;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_buku_actvity);

        /*casting variable*/
        list_item = (RecyclerView)findViewById(R.id.recycler_view);

        /*memasukkan layout ini ke recyclerView*/
        LinearLayoutManager = new LinearLayoutManager(this);
        list_item.setLayoutManager(LinearLayoutManager);


//        itemObjects = new ArrayList<>();
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan R","penulis1","ISBN1","penerbit1","bidang","deskripsi","https://ugmpress.ugm.ac.id/userfiles/product/analisis-data-longitudinal_full.jpg"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan R","penulis1","ISBN1","penerbit1","bidang","deskripsi", "http://res.cloudinary.com/dk-find-out/image/upload/q_80,w_1440/AW_Venus_nli6oy.jpg"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan R","penulis1","ISBN1","penerbit1","bidang","deskripsi", "https://ugmpress.ugm.ac.id/userfiles/product/analisis-data-longitudinal_full.jpg"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan R","penulis1","ISBN1","penerbit1","bidang","deskripsi", "http://res.cloudinary.com/dk-find-out/image/upload/q_80,w_1440/Mars_ICE_CAP_BACK0000_ozkwko.jpg"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan Python","penulis1","ISBN1","penerbit1","bidang","deskripsi", "https://ugmpress.ugm.ac.id/userfiles/product/analisis-statistika-dengan-R.png"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan Python","penulis1","ISBN1","penerbit1","bidang","deskripsi", "https://pps.whatsapp.net/v/t61.24694-24/312291165_178260108204865_1716944224844354687_n.jpg?ccb=11-4&oh=01_AdRaNn8N0W8zMAhW7nsltCfnipZNANSwe5gScG7fq-O9rQ&oe=63A98637"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan R","penulis1","ISBN1","penerbit1","bidang","deskripsi", "https://s2.graphiq.com/sites/default/files/600/media/images/t2/Uranus_4394249.png"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan R","penulis1","ISBN1","penerbit1","bidang","deskripsi", "https://ugmpress.ugm.ac.id/userfiles/product/teori-grup-hingga-cover.jpg"));
//        itemObjects.add(new Buku(1, 3, "Statistika Dengan Matlab","penulis1","ISBN1","penerbit1","bidang","deskripsi", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png"));
//        /*membuat adapter*/
//        adapter = new BukuAdapter(getApplicationContext(),itemObjects);
//        /*masukkan ke recyclerview*/
//        list_item.setAdapter(adapter);
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Buku>> BukuCall = mApiInterface.getAllBuku("Bearer "+token);
        BukuCall.enqueue(new Callback<List<Buku>>() {
            @Override
            public void onResponse(Call<List<Buku>> call, Response<List<Buku>> response) {
                List<Buku> BukuList = response.body();
                /*membuat adapter*/
                adapter = new BukuAdapter(getApplicationContext(),BukuList);
                /*masukkan ke recyclerview*/
                list_item.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Buku>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

//    public void refresh() {
//        Call<GetBuku> kontakCall = mApiInterface.getBuku();
//        kontakCall.enqueue(new Callback<GetBuku>() {
//            @Override
//            public void onResponse(Call<GetBuku> call, Response<GetBuku>
//                    response) {
//                List<Buku> BukuList = response.body().getListDataBuku();
//                Log.d("Retrofit Get", "Jumlah data Kontak: " +
//                        String.valueOf(BukuList.toString()));
//                /*membuat adapter*/
//                adapter = new BukuAdapter(getApplicationContext(),BukuList);
//                /*masukkan ke recyclerview*/
//                list_item.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<GetBuku> call, Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//            }
//        });
//    }

    public void onBackPressed() {
        finish();
    }
}