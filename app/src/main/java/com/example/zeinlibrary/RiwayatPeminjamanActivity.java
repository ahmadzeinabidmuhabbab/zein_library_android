package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPeminjamanActivity extends AppCompatActivity {
    private RecyclerView list_item;
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private List<Peminjaman> itemObjects;
    private PeminjamanAdapter adapter;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_peminjaman);

        /*casting variable*/
        list_item = (RecyclerView)findViewById(R.id.recycler_view);

        /*memasukkan layout ini ke recyclerView*/
        LinearLayoutManager = new LinearLayoutManager(this);
        list_item.setLayoutManager(LinearLayoutManager);


//        itemObjects = new ArrayList<>();
//        itemObjects.add(new Peminjaman(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","-","Masih Dipinjam"));
//        itemObjects.add(new Peminjaman(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","-","Masih Dipinjam"));
//        itemObjects.add(new Peminjaman(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","-","Masih Dipinjam"));
//        itemObjects.add(new Peminjaman(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","-","Masih Dipinjam"));
//        itemObjects.add(new Peminjaman(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://awsimages.detik.net.id/community/media/visual/2019/09/25/fe853eb5-e5f8-453d-915e-63c71ce0cdc6.jpeg?w=750&q=90","19 Desember 2022","-","Masih Dipinjam"));
//        /*membuat adapter*/
//        adapter = new PeminjamanAdapter(getApplicationContext(),itemObjects);
//        /*masukkan ke recyclerview*/
//        list_item.setAdapter(adapter);

        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        String username = sharedPreferences.getString(USERNAME,"");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Peminjaman>> peminjaman = mApiInterface.getPeminjamanUser("Bearer "+token,username);
        peminjaman.enqueue(new Callback<List<Peminjaman>>() {
            @Override
            public void onResponse(Call<List<Peminjaman>> call, Response<List<Peminjaman>> response) {
                Log.d("CEK", "onResponse: "+response.body().size());
                if(response.isSuccessful() & response.body().size() !=0){
                    List<Peminjaman> PeminjamanList = response.body();
                    /*membuat adapter*/
                    adapter = new PeminjamanAdapter(getApplicationContext(),PeminjamanList);
                    /*masukkan ke recyclerview*/
                    list_item.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Peminjaman>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}