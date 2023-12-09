package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class AdminMenuActivity extends AppCompatActivity {
    LinearLayout pinjamBuku, pengembalianBuku, pembayaranDenda, manageAkun, tambahBuku, editBuku, hapusBuku, logout;
    TextView namaAkun;
    ImageView foto;
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        pinjamBuku = (LinearLayout) findViewById(R.id.pinjam_buku);
        pengembalianBuku = (LinearLayout) findViewById(R.id.pengembalian);
        pembayaranDenda = (LinearLayout) findViewById(R.id.pembayaran_denda);
        manageAkun = (LinearLayout) findViewById(R.id.manage_akun);
        tambahBuku = (LinearLayout) findViewById(R.id.tambah_buku);
        editBuku = (LinearLayout) findViewById(R.id.edit_buku);
        hapusBuku = (LinearLayout) findViewById(R.id.hapus_buku);
        logout = (LinearLayout) findViewById(R.id.logout);
        namaAkun = (TextView) findViewById(R.id.nama);
        foto = (ImageView) findViewById(R.id.foto);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String linkFoto = sharedPreferences.getString("foto","");
        String nama = sharedPreferences.getString("nama","");
        String token = sharedPreferences.getString("token","");
        if(token.equals("none")){
            finish();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        namaAkun.setText(nama);
        Glide.with(this)
                .load(linkFoto)
                .transform(new CircleCrop())
                .placeholder(R.drawable.user1)
                .into(foto);
        pinjamBuku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PinjamBukuActivity.class);
                startActivity(i);
            }
        });
        pengembalianBuku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PengembalianActivity.class);
                startActivity(i);
            }
        });
        pembayaranDenda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PembayaranDendaActivity.class);
                startActivity(i);
            }
        });
        manageAkun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ManageAkunActivity.class);
                startActivity(i);
            }
        });
        tambahBuku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TambahBukuActivity.class);
                startActivity(i);
            }
        });
        editBuku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditBukuActivity.class);
                startActivity(i);
            }
        });
        hapusBuku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HapusBukuActivity.class);
                startActivity(i);
            }
        });
        foto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ProfilAdminActivity.class);
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
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

    }
}