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

public class PinjamBukuActivity extends AppCompatActivity {
    Button tambah;
    AlertDialog.Builder builder;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    EditText username, ISBN;
    TextInputLayout kotakISBN, kotakUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_buku);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");

        kotakISBN = (TextInputLayout) findViewById(R.id.kotakISBN);
        kotakUsername = (TextInputLayout) findViewById(R.id.kotakUsername);
        ISBN = (EditText) findViewById(R.id.ISBN);
        username = (EditText) findViewById(R.id.username);
        tambah = (Button) findViewById(R.id.addButton);
        builder = new AlertDialog.Builder(this);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakUsername.setError(null);
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
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valUsername, valISBN;
                boolean error = false;
                valUsername = username.getText().toString();
                valISBN = ISBN.getText().toString();
                if (valUsername.isEmpty()) {
                    kotakUsername.setError("Belum diisi");
                    error = true;
                } else if (valUsername.contains(" ")) {
                    kotakUsername.setError("Username mengandung spasi");
                    error = true;
                } else kotakUsername.setError(null);
                if(valISBN.isEmpty()){
                    kotakISBN.setError("Belum diisi");
                    error = true;
                }else if(Integer.valueOf(valISBN.length()) !=13){
                    if(valISBN.length()!=10) {
                        kotakISBN.setError("Jumlah angka harus 10 atau 13");
                        error = true;}
                }else kotakISBN.setError(null);
                if(!error){
                builder.setMessage("Apakah data yang diinputkan sudah benar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                Call<String> add = mApiInterface.addPinjam("Bearer "+token, valUsername, valISBN);
                                add.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),"Peminjaman buku berhasil",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), TambahBukuActivity.class);
                                            finish();
                                            overridePendingTransition(0, 0);
                                            startActivity(getIntent());
                                            overridePendingTransition(0, 0);
                                        } else

                                            try {
                                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                Log.d("Cek", "onResponse: "+jObjError);
                                                String errorISBN = jObjError.getJSONObject("messages").getString("errorISBN");
                                                String errorUsername = jObjError.getJSONObject("messages").getString("errorUser");
                                                if(!errorISBN.equals("no") && !errorUsername.equals("no")) {
                                                    kotakISBN.setError(errorISBN);
                                                    kotakUsername.setError(errorUsername);
                                                }
                                                else if(!errorISBN.equals("no")){
                                                    kotakISBN.setError(errorISBN);
                                                    kotakUsername.setError(null);
                                                }else if(!errorUsername.equals("no")){
                                                    kotakUsername.setError(errorUsername);
                                                    kotakISBN.setError(null);
                                                }

                                            } catch (Exception e) {
                                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                Log.d("Coba", "onResponse: "+e.getMessage());
                                            }
                                        }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
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
                alert.setTitle("Peminjaman Buku");
                alert.show();
            }
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}