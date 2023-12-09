package com.example.zeinlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranDendaAdapter extends RecyclerView.Adapter<PembayaranDendaHolder>{
    Context context;
    List<PembayaranDenda> itemObjects;
    AlertDialog.Builder builder;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    public PembayaranDendaAdapter(Context context, List<PembayaranDenda> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public PembayaranDendaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pembayarandenda_carditem, null);
        PembayaranDendaHolder holderItem=new PembayaranDendaHolder(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(PembayaranDendaHolder holder, int position) {
        PembayaranDenda modal = itemObjects.get(position);
        holder.txt_judul.setText(itemObjects.get(position).getJudul());
        holder.txt_username.setText("Username : "+itemObjects.get(position).getUsername());
        holder.txt_ISBN.setText("ISBN : "+itemObjects.get(position).getISBN());
        holder.txt_tanggalPinjam.setText("Tanggal Pinjam : "+itemObjects.get(position).getTanggalPinjam());
        holder.txt_tanggalKembali.setText(String.valueOf("Tanggal Kembali: "+itemObjects.get(position).getTanggalKembali()));
        holder.txt_denda.setText("Denda : Rp "+String.valueOf(itemObjects.get(position).getDenda()));
        Glide.with(context)
                .load(itemObjects.get(position).getFoto())
                .placeholder(R.drawable.no_book)
                .into(holder.img_icon);
        int idDenda = itemObjects.get(position).getId();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Apakah  \""+modal.getUsername()+"\" sudah membayar denda sebesar Rp "+String.valueOf(modal.getDenda())+"?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                Call<ResponseBody> bayar = mApiInterface.bayarDenda("Bearer "+token,idDenda,"Lunas");
                                bayar.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(context, "Pembayaran denda berhasil",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(context, PembayaranDendaActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(i);
                                        } else
                                            try {
                                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                Log.d("cek", "onResponse: "+jObjError.toString());
                                                Toast.makeText(context, jObjError.toString(), Toast.LENGTH_LONG).show();

                                            } catch (Exception e) {
                                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
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
                alert.setTitle("Pembayaran Denda");
                alert.show();



//                Intent i = new Intent(context, DetailBukuActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("id", modal.getId());
//                i.putExtra("judul", modal.getJudul());
//                i.putExtra("ISBN", modal.getISBN());
//                i.putExtra("penulis", modal.getPenulis());
//                i.putExtra("penerbit", modal.getPenerbit());
//                i.putExtra("bidang", modal.getBidang());
//                i.putExtra("stok", modal.getStok());
//                i.putExtra("deskripsi", modal.getDeskripsi());
//                i.putExtra("foto", modal.getFoto());
//                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }
}

