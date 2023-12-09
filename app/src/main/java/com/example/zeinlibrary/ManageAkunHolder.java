package com.example.zeinlibrary;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ManageAkunHolder extends RecyclerView.ViewHolder {
    public TextView txt_username, txt_role, txt_tanggalBergabung;
    public ImageView img_icon;
    public Button admin, member;


    public ManageAkunHolder(View itemView) {
        super(itemView);

        /*mangenalkan objek*/
        txt_username = (TextView) itemView.findViewById(R.id.txt_username);
        txt_role = (TextView) itemView.findViewById(R.id.txt_role);
        txt_tanggalBergabung = (TextView) itemView.findViewById(R.id.txt_tanggalBergabung);
        admin = (Button) itemView.findViewById(R.id.adminButton);
        member = (Button) itemView.findViewById(R.id.memberButton);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}
