package com.example.zeinlibrary;

import android.app.Activity;
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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageAkunAdapter extends RecyclerView.Adapter<ManageAkunHolder>{
    Context context;
    List<User> itemObjects;
    AlertDialog.Builder builder;
    ApiInterface mApiInterface;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String ROLE = "role";
    public static final String TOKEN = "token";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdfNew = new SimpleDateFormat("E yyyy.MM.dd 'pada' hh:mm:ss a zzz");

    public ManageAkunAdapter(Context context, List<User> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public ManageAkunHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manageakun_carditem, null);
        ManageAkunHolder holderItem=new ManageAkunHolder(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(ManageAkunHolder holder, int position) {
        User modal = itemObjects.get(position);
        String dateString = itemObjects.get(position).getCreated_at();
        try {
            // string to date
            Date date = sdf.parse(dateString);
//            System.out.println(sdf.format(date));
            holder.txt_tanggalBergabung.setText("Bergabung pada: \n"+date);
//            System.out.println(sdfNew.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txt_username.setText(itemObjects.get(position).getUsername());
        holder.txt_role.setText("Role : "+itemObjects.get(position).getRole());
//        holder.txt_tanggalBergabung.setText("Bidang : "+itemObjects.get(position).getCreated_at());
        Glide.with(context)
                .load(itemObjects.get(position).getFoto())
                .transform(new CircleCrop())
                .placeholder(R.drawable.user1)
                .into(holder.img_icon);
        String role = itemObjects.get(position).getRole();
        int idUser = itemObjects.get(position).getId();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        int id_akunini = sharedPreferences.getInt(ID,0);
        String token = sharedPreferences.getString(TOKEN,"");
        holder.admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+token);
                builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Apakah  \""+modal.getUsername()+"\" akan dijadikan admin?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(role.equals("admin")) Toast.makeText(context.getApplicationContext(),"Akun sudah sebagai admin",
                                        Toast.LENGTH_SHORT).show();
                                else{
                                        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                        Call<ResponseBody> add = mApiInterface.editRole("Bearer "+token, idUser, "admin");
                                        add.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful()) {
                                                    Toast.makeText(context, modal.getUsername()+" sukses diubah menjadi admin",
                                                            Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, ManageAkunActivity.class);
                                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    context.startActivity(i);
                                                } else
                                                    try {
                                                        Log.d("ddd", "onResponse: " + response.errorBody().string());
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
                                }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Pengubahan Role");
                alert.show();
            }
        });
        holder.member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Apakah  \""+modal.getUsername()+"\" akan dijadikan member?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(role.equals("member")) Toast.makeText(context.getApplicationContext(),"Akun sudah sebagai member",
                                        Toast.LENGTH_SHORT).show();
                                else{
                                    if(id_akunini==idUser) Toast.makeText(context.getApplicationContext(),"Tidak bisa edit role akun sendiri",
                                            Toast.LENGTH_SHORT).show();
                                    else {
                                        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                                        Call<ResponseBody> add = mApiInterface.editRole("Bearer "+token, idUser, "member");
                                        add.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful()) {
                                                    Toast.makeText(context, modal.getUsername()+" sukses diubah menjadi member",
                                                            Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(context, ManageAkunActivity.class);
                                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    context.startActivity(i);
                                                } else
                                                    try {
                                                        Log.d("ddd", "onResponse: " + response.errorBody().string());
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
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Pengubahan Role");
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }
}
