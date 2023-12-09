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

public class PengembalianAdapter extends RecyclerView.Adapter<PengembalianHolder>{
    Context context;
    List<Pengembalian> itemObjects;
    AlertDialog.Builder builder;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";

    public PengembalianAdapter(Context context, List<Pengembalian> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public PengembalianHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pengembalian_carditem, null);
        PengembalianHolder holderItem=new PengembalianHolder(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(PengembalianHolder holder, int position) {
        Pengembalian modal = itemObjects.get(position);
        holder.txt_judul.setText(itemObjects.get(position).getJudul());
        holder.txt_username.setText("Username : "+itemObjects.get(position).getUsername());
        holder.txt_ISBN.setText("ISBN : "+itemObjects.get(position).getISBN());
        holder.txt_tanggalPinjam.setText(String.valueOf("Tanggal Pinjam : "+itemObjects.get(position).getTanggalPinjam()));
        Glide.with(context)
                .load(itemObjects.get(position).getFoto())
                .placeholder(R.drawable.no_book)
                .into(holder.img_icon);

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        int idPeminjaman =  itemObjects.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Apakah buku yang dikembalikan \""+modal.getUsername()+"\" sudah benar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                Call<ResponseBody> returnBook = mApiInterface.returnBuku("Bearer "+token, idPeminjaman,"Dikembalikan");
                                returnBook.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(context, "Pengembalian buku sukses",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(context, PengembalianActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(i);
                                        } else
                                            try {
                                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                Toast.makeText(context, jObjError.getJSONObject("messages").getString("errors"), Toast.LENGTH_LONG).show();

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
                alert.setTitle("Pengembalian Buku");
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
