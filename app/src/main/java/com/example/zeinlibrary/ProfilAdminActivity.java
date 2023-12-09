package com.example.zeinlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ProfilAdminActivity extends AppCompatActivity {
    Button detail, editProfil, editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_admin);

        detail = (Button) findViewById(R.id.detail);
        editProfil = (Button) findViewById(R.id.editProfil);
        editPassword = (Button) findViewById(R.id.editPassword);

        detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DetailProfilActivity.class);
                startActivity(i);
            }
        });

        editProfil.setOnClickListener(new View.OnClickListener(){
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
    }

    public void onBackPressed() {
        finish();
    }
}