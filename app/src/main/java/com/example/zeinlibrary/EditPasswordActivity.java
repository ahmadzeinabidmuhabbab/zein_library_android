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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";
    TextView passwordLama, passwordBaru, konfirmasiPassword;
    TextInputLayout kotakPasswordLama, kotakPasswordBaru, kotakKonfirmasiPassword;
    Button editButton;
    ApiInterface mApiInterface;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        int id_akun = sharedPreferences.getInt(ID,0);
        String nilaiPasswordLama = sharedPreferences.getString(PASSWORD,"");
        String token = sharedPreferences.getString(TOKEN,"");
        kotakPasswordLama = (TextInputLayout) findViewById(R.id.kotakPasswordLama);
        kotakPasswordBaru = (TextInputLayout) findViewById(R.id.kotakPasswordBaru);
        kotakKonfirmasiPassword = (TextInputLayout) findViewById(R.id.kotakKonfirmasiPassword);
        passwordLama = (EditText) findViewById(R.id.passwordLama);
        passwordBaru = (EditText) findViewById(R.id.passwordBaru);
        konfirmasiPassword = (EditText) findViewById(R.id.konfirmasiPassword);
        editButton = (Button) findViewById(R.id.editButton);
        passwordLama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kotakPasswordLama.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 kotakPasswordBaru.setError(null);
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
        builder = new AlertDialog.Builder(this);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean error = false;
                String valPasswordLama, valPasswordBaru, valKonfirmasiPassword;
                valPasswordLama = passwordLama.getText().toString();
                valPasswordBaru = passwordBaru.getText().toString();
                valKonfirmasiPassword = konfirmasiPassword.getText().toString();
                if(valPasswordLama.isEmpty()){
                    kotakPasswordLama.setError("Belum diisi");
                    error = true;
                }else if(valPasswordLama.length()<8){
                    kotakPasswordLama.setError("Password lama kurang dari 8 karakter");
                    error = true;
                }else if(valPasswordLama.contains(" ")){
                    kotakPasswordLama.setError("Password lama mengandung spasi");
                    error = true;
                }else if(!valPasswordLama.equals(nilaiPasswordLama)){
                    kotakPasswordLama.setError("Password lama tidak valid");
                    error = true;
                }else kotakPasswordLama.setError(null);

                if(valPasswordBaru.isEmpty()){
                    kotakPasswordBaru.setError("Belum diisi");
                    error = true;
                }else if(valPasswordBaru.length()<8){
                    kotakPasswordBaru.setError("Password baru kurang dari 8 karakter");
                    error = true;
                }else if(valPasswordBaru.contains(" ")){
                    kotakPasswordBaru.setError("Password baru mengandung spasi");
                    error = true;
                }else kotakPasswordBaru.setError(null);

                if(valKonfirmasiPassword.isEmpty()){
                    kotakKonfirmasiPassword.setError("Belum diisi");
                    error = true;
                }else if (valKonfirmasiPassword.contains(" ")) {
                    kotakKonfirmasiPassword.setError("Konfirmasi password mengandung spasi");
                    error = true;
                } else if(!valKonfirmasiPassword.equals(valPasswordBaru)){
                    kotakKonfirmasiPassword.setError("Konfirmasi password tidak sesuai");
                    error = true;
                }else kotakKonfirmasiPassword.setError(null);
                if(!error){
                    builder.setMessage("Apakah yakin edit password?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                    Call<String> edit = mApiInterface.editPassword("Bearer "+token, id_akun, valPasswordBaru);
                                    edit.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            if (response.isSuccessful()) {
                                                try {
                                                    JSONObject userRespon = new JSONObject(response.body());
                                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString(PASSWORD, valPasswordBaru);
                                                    editor.apply();
                                                    Toast.makeText(getApplicationContext(), "Edit password berhasil", Toast.LENGTH_SHORT).show();
//                                                    passwordLama.setText(null);
//                                                    passwordBaru.setText(null);
//                                                    konfirmasiPassword.setText(null);
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
                    alert.setTitle("Edit Password");
                    alert.show();
                }
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}