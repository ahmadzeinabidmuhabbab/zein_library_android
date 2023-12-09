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

public class PembayaranDendaActivity extends AppCompatActivity {
    private RecyclerView list_item;
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private List<PembayaranDenda> itemObjects;
    private PembayaranDendaAdapter adapter;
    ApiInterface mApiInterface;
    public static PembayaranDendaActivity ma;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_denda);

        /*casting variable*/
        list_item = (RecyclerView)findViewById(R.id.recycler_view);

        /*memasukkan layout ini ke recyclerView*/
        LinearLayoutManager = new LinearLayoutManager(this);
        list_item.setLayoutManager(LinearLayoutManager);
//
//
//        itemObjects = new ArrayList<>();
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        itemObjects.add(new PembayaranDenda(1,50000,"abidmuhabbab", "2728881677172","Statistika Ria", "https://ugmpress.ugm.ac.id/userfiles/product/Inferensi-Bayesian-dengan-R.png","19 Desember 2022","23 Desember 2022"));
//        /*membuat adapter*/
//        adapter = new PembayaranDendaAdapter(getApplicationContext(),itemObjects);
//        /*masukkan ke recyclerview*/
//        list_item.setAdapter(adapter);
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PembayaranDenda>> denda = mApiInterface.getAllDenda("Bearer "+token);
        denda.enqueue(new Callback<List<PembayaranDenda>>() {
            @Override
            public void onResponse(Call<List<PembayaranDenda>> call, Response<List<PembayaranDenda>> response) {
                Log.d("CEK", "onResponse: "+response.body().size());
                if(response.isSuccessful() & response.body().size() !=0){
                    List<PembayaranDenda> DendaList = response.body();
                    /*membuat adapter*/
                    adapter = new PembayaranDendaAdapter(getApplicationContext(),DendaList);
                    /*masukkan ke recyclerview*/
                    list_item.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PembayaranDenda>> call, Throwable t) {
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