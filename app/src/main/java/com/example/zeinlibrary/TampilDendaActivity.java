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

public class TampilDendaActivity extends AppCompatActivity {
    private RecyclerView list_item;
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private List<Denda> itemObjects;
    private DendaAdapter adapter;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_denda);

        /*casting variable*/
        list_item = (RecyclerView)findViewById(R.id.recycler_view);

        /*memasukkan layout ini ke recyclerView*/
        LinearLayoutManager = new LinearLayoutManager(this);
        list_item.setLayoutManager(LinearLayoutManager);


//        itemObjects = new ArrayList<>();
//        itemObjects.add(new Denda(1,"abidmuhabbab", "Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","22222222222",8000,"18 Desember 2022","Lunas"));
//        itemObjects.add(new Denda(1,"abidmuhabbab", "Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","22222222222",8000,"18 Desember 2022","Lunas"));
//        itemObjects.add(new Denda(1,"abidmuhabbab", "Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","22222222222",8000,"18 Desember 2022","Lunas"));
//        itemObjects.add(new Denda(1,"abidmuhabbab", "Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","22222222222",8000,"18 Desember 2022","Lunas"));
//        /*membuat adapter*/
//        adapter = new DendaAdapter(getApplicationContext(),itemObjects);
//        /*masukkan ke recyclerview*/
//        list_item.setAdapter(adapter);

        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        String username = sharedPreferences.getString(USERNAME,"");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Denda>> peminjaman = mApiInterface.getDendaUser("Bearer "+token,username);
        peminjaman.enqueue(new Callback<List<Denda>>() {
            @Override
            public void onResponse(Call<List<Denda>> call, Response<List<Denda>> response) {
                Log.d("CEK", "onResponse: "+response.body().size());
                if(response.isSuccessful() & response.body().size() !=0){
                    List<Denda> PeminjamanList = response.body();
                    /*membuat adapter*/
                    adapter = new DendaAdapter(getApplicationContext(),PeminjamanList);
                    /*masukkan ke recyclerview*/
                    list_item.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Denda>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
    }
