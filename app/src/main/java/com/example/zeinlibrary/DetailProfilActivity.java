package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class DetailProfilActivity extends AppCompatActivity {
    ImageView foto;
    EditText username, nama, email, noHP, link;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "username";
    public static final String NAMA = "nama";
    public static final String EMAIL = "email";
    public static final String NOHP = "nohp";
    public static final String FOTO = "foto";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);
        foto = (ImageView) findViewById(R.id.foto);
        username= (EditText) findViewById(R.id.username);
        nama= (EditText) findViewById(R.id.nama);
        email= (EditText) findViewById(R.id.email);
        noHP= (EditText) findViewById(R.id.noHP);
        link= (EditText) findViewById(R.id.link);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        username.setText(sharedPreferences.getString(USERNAME,""));
        nama.setText(sharedPreferences.getString(NAMA,""));
        email.setText(sharedPreferences.getString(EMAIL,""));
        noHP.setText(sharedPreferences.getString(NOHP,""));
        link.setText(sharedPreferences.getString(FOTO,""));
        Glide.with(this)
                .load(link.getText().toString())
                .transform(new CircleCrop())
                .placeholder(R.drawable.user1)
                .into(foto);
    }

    public void onBackPressed() {
        finish();
    }
}