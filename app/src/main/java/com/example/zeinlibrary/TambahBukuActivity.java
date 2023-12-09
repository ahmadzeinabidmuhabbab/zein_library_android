package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBukuActivity extends AppCompatActivity {
    Button tambah;
    TextInputLayout kotakJudul, kotakISBN, kotakPenulis, kotakPenerbit, kotakBidang, kotakStok, kotakDeskripsi, kotakLink;
    EditText judul, ISBN, penulis, penerbit, bidang, stok, deskripsi, link;
    ApiInterface mApiInterface;
    AlertDialog.Builder builder;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");

        kotakJudul = (TextInputLayout) findViewById(R.id.kotakJudul);
        kotakISBN = (TextInputLayout) findViewById(R.id.kotakISBN);
        kotakPenulis = (TextInputLayout) findViewById(R.id.kotakPenulis);
        kotakPenerbit = (TextInputLayout) findViewById(R.id.kotakPenerbit);
        kotakBidang = (TextInputLayout) findViewById(R.id.kotakBidang);
        kotakStok = (TextInputLayout) findViewById(R.id.kotakStok);
        kotakDeskripsi = (TextInputLayout) findViewById(R.id.kotakDeskripsi);
        kotakLink = (TextInputLayout) findViewById(R.id.kotakFoto);
        judul = (EditText) findViewById(R.id.judul);
        ISBN = (EditText) findViewById(R.id.ISBN);
        penulis = (EditText) findViewById(R.id.penulis);
        penerbit = (EditText) findViewById(R.id.penerbit);
        bidang = (EditText) findViewById(R.id.bidang);
        stok = (EditText) findViewById(R.id.stok);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        link = (EditText) findViewById(R.id.foto);
        tambah = (Button) findViewById(R.id.tambahButton);
        builder = new AlertDialog.Builder(this);
        judul.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakJudul.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ISBN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakISBN.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        penulis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakPenulis.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        penerbit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakPenerbit.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bidang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakBidang.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        stok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakStok.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        deskripsi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakDeskripsi.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        link.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakLink.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                String val_judul, val_ISBN, val_penulis, val_penerbit, val_bidang, val_stok, val_deskripsi, val_link;
                val_judul = judul.getText().toString();
                val_ISBN = ISBN.getText().toString();
                val_penulis = penulis.getText().toString();
                val_penerbit = penerbit.getText().toString();
                val_bidang = bidang.getText().toString();
                val_stok = stok.getText().toString();
                val_deskripsi = deskripsi.getText().toString();
                val_link = link.getText().toString();

                if(val_judul.isEmpty()){
                    kotakJudul.setError("Belum diisi");
                    error = true;
                }else kotakJudul.setError(null);
                if(val_ISBN.isEmpty()){
                    kotakISBN.setError("Belum diisi");
                    error = true;
                }else if(Integer.valueOf(val_ISBN.length()) !=13){
                    if(val_ISBN.length()!=10) {
                    Log.d("coba", "onClick: "+val_ISBN.length());
                    kotakISBN.setError("Jumlah angka harus 10 atau 13");
                    error = true;}
                }else kotakISBN.setError(null);
                if(val_penulis.isEmpty()){
                    kotakPenulis.setError("Belum diisi");
                    error = true;
                }else kotakPenulis.setError(null);
                if(val_penerbit.isEmpty()){
                    kotakPenerbit.setError("Belum diisi");
                    error = true;
                }else kotakPenerbit.setError(null);
                if(val_bidang.isEmpty()){
                    kotakBidang.setError("Belum diisi");
                    error = true;
                }else kotakBidang.setError(null);
                if(val_stok.isEmpty()){
                    kotakStok.setError("Belum diisi");
                    error = true;
                }else kotakStok.setError(null);
                if(val_deskripsi.isEmpty()){
                    kotakDeskripsi.setError("Belum diisi");
                    error = true;
                }else kotakDeskripsi.setError(null);
                if (val_link.isEmpty()){
                    kotakLink.setError("Belum diisi");
                    error = true;
                }else kotakLink.setError(null);
                if(!val_link.isEmpty() && val_link.contains("https://drive.google.com/file/d/")){
                    String url = val_link;
                    val_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 17);
                }else if(!val_link.isEmpty() && val_link.contains("https://drive.google.com/uc?id=")){
                    val_link = val_link;}
//                }else
//                if (!val_link.isEmpty() && !val_link.contains("https://drive.google.com/")) {
//                    kotakLink.setError("Tidak link GDrive");
//                    error = true;
//                } else


                String valLink = val_link;
                Log.d("deskripsi",String.valueOf(val_deskripsi.length()));
                if(!error){
                    builder.setMessage("Apakah data yang diinputkan sudah benar?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                    Call<ResponseBody> add = mApiInterface.addBuku("Bearer "+token, val_judul, val_penulis, val_ISBN, val_penerbit, val_bidang, Integer.valueOf(val_stok),val_deskripsi, valLink);
                                    add.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),"Buku berhasil ditambahkan",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getApplicationContext(), TambahBukuActivity.class);
                                                finish();
                                                overridePendingTransition(0, 0);
                                                startActivity(getIntent());
                                                overridePendingTransition(0, 0);
                                            } else
                                                try {
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
                    alert.setTitle("Penambahan Buku");
                    alert.show();
                }
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}