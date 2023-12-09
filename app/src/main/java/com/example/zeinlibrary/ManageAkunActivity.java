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

public class ManageAkunActivity extends AppCompatActivity {
    private RecyclerView list_item;
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private List<ManageAkun> itemObjects;
    private ManageAkunAdapter adapter;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_akun);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");

        /*casting variable*/
        list_item = (RecyclerView)findViewById(R.id.recycler_view);

        /*memasukkan layout ini ke recyclerView*/
        LinearLayoutManager = new LinearLayoutManager(this);
        list_item.setLayoutManager(LinearLayoutManager);


//        itemObjects = new ArrayList<>();
//        itemObjects.add(new ManageAkun(1, "abidmuhabbab", "admin","18 Februari 2022","https://drive.google.com/uc?id=1ltaK208pB-IBqOwAl2hc4wsaRogMnKAJ"));
//        /*membuat adapter*/
//        adapter = new ManageAkunAdapter(getApplicationContext(),itemObjects);
//        /*masukkan ke recyclerview*/
//        list_item.setAdapter(adapter);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<User>> User = mApiInterface.getAllUser("Bearer "+token);
        User.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> UserList = response.body();
                /*membuat adapter*/
                adapter = new ManageAkunAdapter(getApplicationContext(),UserList);
                /*masukkan ke recyclerview*/
                list_item.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
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