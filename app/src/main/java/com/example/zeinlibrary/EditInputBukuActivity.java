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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInputBukuActivity extends AppCompatActivity {
    Button editButton;
    AlertDialog.Builder builder;
    ApiInterface mApiInterface;
    TextView judul, ISBN, penulis, penerbit, bidang, stok, deskripsi, link;
    TextInputLayout kotakJudul, kotakISBN, kotakPenulis, kotakPenerbit, kotakBidang, kotakStok, kotakDeskripsi, kotakLink;
    ImageView foto;
    String value_judul, value_ISBN, value_penulis, value_penerbit, value_bidang,  value_deskripsi, value_link;
    int value_id, value_stok;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_input_buku);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");

        kotakJudul = (TextInputLayout) findViewById(R.id.kotakJudul);
        kotakISBN = (TextInputLayout) findViewById(R.id.kotakISBN);
        kotakPenulis = (TextInputLayout) findViewById(R.id.kotakPenulis);
        kotakPenerbit = (TextInputLayout) findViewById(R.id.kotakPenerbit);
        kotakBidang = (TextInputLayout) findViewById(R.id.kotakBidang);
        kotakStok = (TextInputLayout) findViewById(R.id.kotakStok);
        kotakDeskripsi = (TextInputLayout) findViewById(R.id.kotakDeskripsi);
        kotakLink = (TextInputLayout) findViewById(R.id.kotakLink);

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

        editButton = (Button) findViewById(R.id.editButton);
        builder = new AlertDialog.Builder(this);
        editButton.setOnClickListener(new View.OnClickListener() {
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
                    error = true;} else kotakLink.setError(null);
                if(!val_link.isEmpty() && val_link.contains("https://drive.google.com/file/d/") && val_link.contains("/view?usp=sharing")){
                    String url = value_link;
                    value_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 17);
                }else if(!val_link.isEmpty() && val_link.contains("https://drive.google.com/file/d/") && val_link.contains("/view")){
                    String url = value_link;
                    value_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 5);
                } else if(!val_link.isEmpty() && val_link.contains("https://drive.google.com/uc?id=")){
                    val_link = val_link;
                }
//                }else if (!val_link.isEmpty() && !val_link.contains("https://drive.google.com/")) {
//                    kotakLink.setError("Tidak link GDrive");
//                    error = true;
//                }
//                else


                String valLink = val_link;
                if(!error){
                    builder.setMessage("Apakah data yang diedit sudah benar?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                                    Call<ResponseBody> add = mApiInterface.editBuku("Bearer "+token,value_id, val_judul, val_penulis, val_ISBN, val_penerbit, val_bidang, Integer.valueOf(val_stok),val_deskripsi, valLink);
                                    add.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(),"Buku berhasil diedit",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getApplicationContext(), EditBukuActivity.class);
                                                finish();
                                                overridePendingTransition(0, 0);
                                                startActivity(i);
                                                overridePendingTransition(0, 0);
                                            } else
                                                kotakISBN.setError("ISBN sudah ada");
                                                ISBN.requestFocus();
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
                    alert.setTitle("Pengeditan Buku");
                    alert.show();
            }}
        });
    }

    public void onBackPressed() {
        finish();
    }
}