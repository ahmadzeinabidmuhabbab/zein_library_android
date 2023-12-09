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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity {
    EditText username, nama, email, noHP, link;
    TextInputLayout kotakUsername, kotakNama, kotakEmail, kotakNoHP, kotakLink;
    Button editButton;
    ApiInterface mApiInterface;
    AlertDialog.Builder builder;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String NAMA = "nama";
    public static final String EMAIL = "email";
    public static final String NOHP = "nohp";
    public static final String FOTO = "foto";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        int id_akun = sharedPreferences.getInt(ID,0);
        String token = sharedPreferences.getString(TOKEN,"");

        kotakUsername = (TextInputLayout) findViewById(R.id.kotakUsername);
        kotakNama = (TextInputLayout) findViewById(R.id.kotakNama);
        kotakEmail = (TextInputLayout) findViewById(R.id.kotakEmail);
        kotakNoHP = (TextInputLayout) findViewById(R.id.kotakNoHP);
        kotakLink = (TextInputLayout) findViewById(R.id.kotakLink);
        username= (EditText) findViewById(R.id.username);
        nama= (EditText) findViewById(R.id.nama);
        email= (EditText) findViewById(R.id.email);
        noHP= (EditText) findViewById(R.id.noHP);
        link= (EditText) findViewById(R.id.link);
        editButton = (Button) findViewById(R.id.editButton);
        username.setText(sharedPreferences.getString(USERNAME,""));
        nama.setText(sharedPreferences.getString(NAMA,""));
        email.setText(sharedPreferences.getString(EMAIL,""));
        noHP.setText(sharedPreferences.getString(NOHP,""));
        link.setText(sharedPreferences.getString(FOTO,""));
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(username.getText().toString().contains(" ")) kotakUsername.setError("Username mengandung spasi");
//                else kotakUsername.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakNama.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        noHP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakNoHP.setError(null);
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
        builder = new AlertDialog.Builder(this);
        editButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  boolean error = false;
                  String valUsename, valNama, valEmail, valNoHP, value_link;
                  valUsename = username.getText().toString();
                  valNama = nama.getText().toString();
                  valEmail = email.getText().toString();
                  valNoHP = noHP.getText().toString();
                  value_link = link.getText().toString();
                  if (valUsename.isEmpty()) {
                      kotakUsername.setError("Belum diisi");
                      error = true;
                  } else if (valUsename.contains(" ")) {
                      kotakUsername.setError("Username mengandung spasi");
                      error = true;
                  } else kotakUsername.setError(null);
                  if (valNama.isEmpty()) {
                      kotakNama.setError("Belum diisi");
                      error = true;
                  } else kotakNama.setError(null);
                  if (valEmail.isEmpty()) {
                      kotakEmail.setError("Belum diisi");
                      error = true;
                  } else if (!valEmail.matches("^(.+)@(\\S+)$")) {
                      kotakEmail.setError("Email tidak valid");
                      error = true;
                  } else kotakEmail.setError(null);
                  if (valNoHP.isEmpty()) {
                      kotakNoHP.setError("Belum diisi");
                      error = true;
                  } else kotakNoHP.setError(null);
//                  if (!value_link.isEmpty() && !value_link.contains("https://drive.google.com/")) {
//                      kotakLink.setError("Tidak link GDrive");
//                      error = true;
//                  } else
                  if(!value_link.isEmpty() && value_link.contains("https://drive.google.com/file/d/") && value_link.contains("/view?usp=sharing")){
                      String url = value_link;
                      value_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 17);
                  }else if(!value_link.isEmpty() && value_link.contains("https://drive.google.com/file/d/") && value_link.contains("/view")){
                      String url = value_link;
                      value_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 5);
                  } else if(!value_link.isEmpty() && value_link.contains("https://drive.google.com/uc?id=")){
                      value_link = value_link;
                  }
//                  else kotakLink.setError(null);
                  if (value_link.isEmpty()) {
                      value_link = "no_picture";
                  }
                  String valLink = value_link;
                  if(!error) {
                      builder.setMessage("Apakah data yang diedit sudah benar?")
                              .setCancelable(false)
                              .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                  public void onClick(DialogInterface dialog, int id) {
                                      mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                      Log.d("coba", "onClick: "+valNama+valEmail+valNoHP+valLink);
                                      Call<String> edit = mApiInterface.editProfil("Bearer "+token, id_akun, valNama, valEmail, valNoHP, valLink);
                                      edit.enqueue(new Callback<String>() {
                                          @Override
                                          public void onResponse(Call<String> call, Response<String> response) {
                                              if (response.isSuccessful()) {
                                                  try {
                                                          JSONObject userRespon = new JSONObject(response.body());
                                                          SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                                          SharedPreferences.Editor editor = sharedPreferences.edit();
                                                          editor.putString(NAMA, valNama);
                                                          editor.putString(EMAIL, valEmail);
                                                          editor.putString(NOHP, valNoHP);
                                                          editor.putString(FOTO, valLink);
                                                          editor.apply();
                                                          Toast.makeText(getApplicationContext(), "Edit profil berhasil", Toast.LENGTH_SHORT).show();
                                                          finish();
                                                          overridePendingTransition(0, 0);
                                                          startActivity(getIntent());
                                                          overridePendingTransition(0, 0);
                                                  } catch (Exception e) {
                                                      Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                  }
                                              } else
                                                  Toast.makeText(getApplicationContext(), "Gagal edit profil", Toast.LENGTH_LONG).show();
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
                      alert.setTitle("Edit Profil");
                      alert.show();
                  }
              }}
        );

    }

    public void onBackPressed() {
        finish();
    }
}