package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class MenuActivity extends AppCompatActivity {
    LinearLayout daftarBuku, riwayatPeminjaman, denda, editAkun, editPassword, logout;
    TextView namaAkun;
    ImageView foto;
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        daftarBuku = (LinearLayout) findViewById(R.id.daftar_buku);
        riwayatPeminjaman = (LinearLayout) findViewById(R.id.riwayat_peminjaman);
        denda = (LinearLayout) findViewById(R.id.denda);
        editAkun = (LinearLayout) findViewById(R.id.edit_akun);
        editPassword = (LinearLayout) findViewById(R.id.edit_password);
        logout = (LinearLayout) findViewById(R.id.logout);
        namaAkun = (TextView) findViewById(R.id.nama);
        foto = (ImageView) findViewById(R.id.foto);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String linkFoto = sharedPreferences.getString("foto","");
        String token = sharedPreferences.getString("token","");
        if(token.equals("none")){
            finish();
            overridePendingTransition(0, 0);
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        String nama = sharedPreferences.getString("nama","");
//                "https://drive.google.com/uc?id=1ltaK208pB-IBqOwAl2hc4wsaRogMnKAJ";
        namaAkun.setText(nama);
        Glide.with(this)
                .load(linkFoto)
                .transform(new CircleCrop())
                .placeholder(R.drawable.user1)
                .into(foto);
        daftarBuku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DaftarBukuActvity.class);
                startActivity(i);
            }
        });
        riwayatPeminjaman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RiwayatPeminjamanActivity.class);
                startActivity(i);
            }
        });
        denda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TampilDendaActivity.class);
                startActivity(i);
            }
        });
        editAkun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditAkunActivity.class);
                startActivity(i);
            }
        });
        editPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditPasswordActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token","none");
                editor.apply();
                finish();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        foto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DetailProfilActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

//    public void onBackPressed() {
//        finish();
//    }

    public void onBackPressed() {
        finish();
    }

}