package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {
    TextView login;
    TextInputLayout kotakUsername, kotakNama, kotakEmail, kotakNoHP, kotakPassword, kotakKonfirmasiPassword, kotakLink;
    EditText username, nama, email, noHP, password, konfirmasiPassword, link;
    Button registrasiButton;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        kotakUsername = (TextInputLayout) findViewById(R.id.kotakUsername);
        kotakNama = (TextInputLayout) findViewById(R.id.kotakNama);
        kotakEmail = (TextInputLayout) findViewById(R.id.kotakEmail);
        kotakNoHP = (TextInputLayout) findViewById(R.id.kotakNoHP);
        kotakPassword = (TextInputLayout) findViewById(R.id.kotakPassword);
        kotakKonfirmasiPassword = (TextInputLayout) findViewById(R.id.kotakKonfirmasiPassword);
        kotakLink = (TextInputLayout) findViewById(R.id.kotakLink);
        username = (EditText) findViewById(R.id.username);
        nama = (EditText) findViewById(R.id.nama);
        email = (EditText) findViewById(R.id.email);
        noHP = (EditText) findViewById(R.id.noHP);
        password = (EditText) findViewById(R.id.password);
        konfirmasiPassword = (EditText) findViewById(R.id.konfirmasiPassword);
        link = (EditText) findViewById(R.id.link);
        registrasiButton = (Button) findViewById(R.id.registrasiButton);
        login = (TextView) findViewById(R.id.loginText);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
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
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakPassword.setError(null);
//                if(!password.getText().toString().isEmpty() && password.getText().toString().contains(" ")){
//                    kotakPassword.setError("Password mengandung spasi");
//                }else if(password.getText().toString().length()<8){
//                    kotakPassword.setError("Password kurang dari 8 karakter");
//                }else kotakPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        konfirmasiPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakKonfirmasiPassword.setError(null);
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
        registrasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                String valUsename, valNama, valEmail, valNoHP, valPassword, valKonfirmasiPassword, value_link;
                valUsename = username.getText().toString();
                valNama = nama.getText().toString();
                valEmail = email.getText().toString();
                valNoHP = noHP.getText().toString();
                valPassword = password.getText().toString();
                valKonfirmasiPassword = konfirmasiPassword.getText().toString();
                value_link = link.getText().toString();
                Log.d("HP", "onClick: " + valNoHP);
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
                if (valPassword.isEmpty()) {
                    kotakPassword.setError("Belum diisi");
                    error = true;
                } else if (valPassword.contains(" ")) {
                    kotakPassword.setError("Password mengandung spasi");
                    error = true;
                } else if (valPassword.length() < 8) {
                    kotakPassword.setError("Password kurang dari 8 karakter");
                    error = true;
                } else kotakPassword.setError(null);
                if (valKonfirmasiPassword.isEmpty()) {
                    kotakKonfirmasiPassword.setError("Belum diisi");
                    error = true;
                } else if (valKonfirmasiPassword.contains(" ")) {
                    kotakKonfirmasiPassword.setError("Konfirmasi password mengandung spasi");
                    error = true;
                } else if (!valKonfirmasiPassword.equals(valPassword)) {
                    kotakKonfirmasiPassword.setError("Konfirmasi password tidak sesuai");
                    error = true;
                } else kotakKonfirmasiPassword.setError(null);
//                if (!value_link.isEmpty() && !value_link.contains("https://drive.google.com/file/d/")) {
//                    kotakLink.setError("Tidak link GDrive");
//                    error = true;
//                } else
                if(!value_link.isEmpty() && value_link.contains("https://drive.google.com/file/d/") && value_link.contains("/view?usp=sharing")){
                    String url = value_link;
                    value_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 17);
                }else if(!value_link.isEmpty() && value_link.contains("https://drive.google.com/file/d/") && value_link.contains("/view")){
                    String url = value_link;
                    value_link = "https://drive.google.com/uc?id="+url.substring(32, url.length() - 5);
                } else if(!value_link.isEmpty() && value_link.contains("https://drive.google.com/uc?id=")){
                    value_link = value_link;
                }
//                else kotakLink.setError(null);
                if (value_link.isEmpty()) {
                    value_link = "no_picture";
                }
                if (!error) {
                    mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<ResponseBody> token = mApiInterface.registerRequest(valUsename, valNama, valEmail, valNoHP, valPassword, value_link);
                    token.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registrasi sukses\nSilakan login",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                    Toast.makeText(getApplicationContext(), jObjError.getJSONObject("messages").getString("errors"), Toast.LENGTH_LONG).show();
                                    kotakUsername.setError(jObjError.getJSONObject("messages").getJSONObject("errors").getString("username"));
                                    username.requestFocus();
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
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}