package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HapusActionBukuActivity extends AppCompatActivity {
    Button hapusButton;
    AlertDialog.Builder builder;
    ApiInterface mApiInterface;
    TextView judul, ISBN, penulis, penerbit, bidang, stok, deskripsi, link;
    ImageView foto;
    String value_judul, value_ISBN, value_penulis, value_penerbit, value_bidang,  value_deskripsi, value_link;
    int value_id, value_stok;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_action);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");

        judul = (TextView) findViewById(R.id.judul);
        ISBN = (TextView) findViewById(R.id.ISBN);
        penulis = (TextView) findViewById(R.id.penulis);
        penerbit = (TextView) findViewById(R.id.penerbit);
        bidang = (TextView) findViewById(R.id.bidang);
        stok = (TextView) findViewById(R.id.stok);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        link = (TextView) findViewById(R.id.link);
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
        value_link = i.getStringExtra("foto");

        judul.setText(value_judul);
        ISBN.setText(value_ISBN);
        penulis.setText(value_penulis);
        penerbit.setText(value_penerbit);
        bidang.setText(value_bidang);
        stok.setText(String.valueOf(value_stok));
        deskripsi.setText(value_deskripsi);
        link.setText(value_link);

        Glide.with(this)
                .load(value_link)
                .placeholder(R.drawable.no_book)
                .into(foto);

        hapusButton = (Button) findViewById(R.id.hapusButton);
        builder = new AlertDialog.Builder(this);
        hapusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting message manually and performing action on button click
                builder.setMessage("Apakah yakin menghapus buku ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                                Call<ResponseBody> delete = mApiInterface.deleteBuku("Bearer "+token, value_id);
                                delete.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),"Buku berhasil dihapus",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), HapusBukuActivity.class);
                                            finish();
                                            overridePendingTransition(0, 0);
                                            startActivity(i);
                                            overridePendingTransition(0, 0);
                                        } else
                                            try {
                                                Log.d("ddd", "onResponse: "+response.errorBody().string());
                                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                Toast.makeText(getApplicationContext(), jObjError.getJSONObject("messages").getString("errors"), Toast.LENGTH_LONG).show();

                                            } catch (Exception e) {
                                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.e("Retrofit Get", t.toString());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Penghapusan Buku");
                alert.show();
            }
        });
    }

    public void onBackPressed() {
        finish();
    }

}