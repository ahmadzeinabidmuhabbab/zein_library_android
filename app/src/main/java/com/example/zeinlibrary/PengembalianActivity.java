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

public class PengembalianActivity extends AppCompatActivity {
    private RecyclerView list_item;
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private List<Pengembalian> itemObjects;
    private PengembalianAdapter adapter;
    ApiInterface mApiInterface;
    public static PengembalianActivity ma;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengembalian);

        /*casting variable*/
        list_item = (RecyclerView)findViewById(R.id.recycler_view);

        /*memasukkan layout ini ke recyclerView*/
        LinearLayoutManager = new LinearLayoutManager(this);
        list_item.setLayoutManager(LinearLayoutManager);


//        itemObjects = new ArrayList<>();
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        itemObjects.add(new Pengembalian(1,"abidmuhabbab", "Statistika Ria", "2728881677172","https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022"));
//        /*membuat adapter*/
//        adapter = new PengembalianAdapter(getApplicationContext(),itemObjects);
//        /*masukkan ke recyclerview*/
//        list_item.setAdapter(adapter);

        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Pengembalian>> pengembalian = mApiInterface.getAllPeminjaman("Bearer "+token);
        pengembalian.enqueue(new Callback<List<Pengembalian>>() {
            @Override
            public void onResponse(Call<List<Pengembalian>> call, Response<List<Pengembalian>> response) {
                if(response.isSuccessful() & response !=null){
                    List<Pengembalian> BukuList = response.body();
                    /*membuat adapter*/
                    adapter = new PengembalianAdapter(getApplicationContext(),BukuList);
                    /*masukkan ke recyclerview*/
                    list_item.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pengembalian>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
        Intent i = new Intent(getApplicationContext(), AdminMenuActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }

}