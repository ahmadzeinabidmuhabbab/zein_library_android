package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView registrasi;
    TextInputLayout kotakUsername, kotakPassword;
    EditText username, password;
    Button loginButton;
    ApiInterface mApiInterface;
    Context mContext;
    private long pressedTime;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String NAMA = "nama";
    public static final String EMAIL = "email";
    public static final String NOHP = "nohp";
    public static final String PASSWORD = "password";
    public static final String FOTO = "foto";
    public static final String ROLE = "role";
    public static final String TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");

        kotakUsername = (TextInputLayout) findViewById(R.id.kotakUsername);
        kotakPassword = (TextInputLayout) findViewById(R.id.kotakPassword);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        registrasi = (TextView) findViewById(R.id.registrasiText);
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegistrasiActivity.class);
                startActivity(i);
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(username.getText().toString().contains(" ")) kotakUsername.setError("Username mengandung spasi");
                else kotakUsername.setError(null);
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
                if(password.getText().toString().isEmpty()){
                    kotakPassword.setError("Belum diisi");
                }else if(password.getText().toString().contains(" ")){
                    kotakPassword.setError("Password mengandung spasi");
                }else kotakPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mContext = this;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error=false;
                String val_username = username.getText().toString();
                String val_password = password.getText().toString();
                if(val_username.isEmpty()){
                    kotakUsername.setError("Belum diisi");
                    error = true;
                }
                if(val_password.isEmpty()){
                    kotakPassword.setError("Belum diisi");
                    error = true;
                }else if(val_password.length()<8){
                    kotakPassword.setError("Password kurang dari 8 karakter");
                    error = true;
                }else if(val_password.contains(" ")){
                    kotakPassword.setError("Password mengandung spasi");
                    error = true;
                }
                if(!error) {
                    mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<Token> tokenku = mApiInterface.loginRequest(val_username, val_password);
                    tokenku.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            if (response.isSuccessful()) {

                                try {
                                    Token respon = response.body();
                                    String token = respon.getToken();
                                    int idku = respon.getId();
                                    Call<String> user = mApiInterface.getUser("Bearer "+token, idku);
                                    user.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
//                                            if(response != null){
                                                try {
                                                    JSONObject userRespon = new JSONObject(response.body());
                                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString(TOKEN,token);
                                                    editor.putInt(ID,idku);
                                                    editor.putString(USERNAME,userRespon.getString("username"));
                                                    editor.putString(NAMA,userRespon.getString("nama"));
                                                    editor.putString(EMAIL,userRespon.getString("email"));
                                                    editor.putString(PASSWORD,val_password);
                                                    editor.putString(NOHP,userRespon.getString("nomorHP"));
                                                    editor.putString(FOTO,userRespon.getString("foto"));
                                                    editor.putString(ROLE,userRespon.getString("role"));
                                                    editor.putString(TOKEN,token);
                                                    editor.apply();

                                                    Toast.makeText(getApplicationContext(), "Login sukses", Toast.LENGTH_SHORT).show();
                                                    String role = userRespon.getString("role");
                                                    Log.d("Coba", role);
                                                    if(role.equals("admin")){
                                                        Intent i = new Intent(getApplicationContext(), AdminMenuActivity.class);
                                                        startActivity(i);
                                                    }else{
                                                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                                        startActivity(i);
                                                    }

                                                }catch (Exception e){
                                                    Log.d("hdhd", "onResponse: "+e.getMessage());
                                                    Toast.makeText(getApplicationContext(), "ini"+e.getMessage(), Toast.LENGTH_LONG).show();
                                                }
//                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "itu"+e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(getApplicationContext(),jObjError.getJSONObject("messages").getString("error"), Toast.LENGTH_LONG).show();
                                    Log.d("Tag", "onResponse: "+jObjError.toString());
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "ina"+e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                            Log.e("Retrofit Get", t.toString());
                        }
                    });
                }

//                boolean error = false;
//                if(username.getText().toString().isEmpty()) {
//                    kotakUsername.setError("Belum diisi");
//                    error = true;
//                } else kotakUsername.setError(null);
//                if(!username.getText().toString().isEmpty() && username.getText().toString().contains(" ")){
//                    kotakUsername.setError("Username mengandung spasi");
//                    error = true;
//                }
//                if(password.getText().toString().isEmpty()){
//                    kotakPassword.setError("Belum diisi");
//                    error = true;
//                }else if(password.getText().toString().contains(" ")){
//                    kotakPassword.setError("Password mengandung spasi");
//                    error = true;
//                }else kotakPassword.setError(null);
//                if(!error) Toast.makeText(getApplicationContext(),"Success Login",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}