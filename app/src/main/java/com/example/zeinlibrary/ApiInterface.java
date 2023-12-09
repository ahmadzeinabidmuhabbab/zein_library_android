package com.example.zeinlibrary;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("buku")
    Call<List<Buku>> getAllBuku(@Header("Authorization") String token);

    @GET("user")
    Call<List<User>> getAllUser(@Header("Authorization") String token);

    @GET("peminjaman")
    Call<List<Pengembalian>> getAllPeminjaman(@Header("Authorization") String token);

    @GET("denda")
    Call<List<PembayaranDenda>> getAllDenda(@Header("Authorization") String token);

    @GET("user/{id}")
    Call<String> getUser(@Header("Authorization") String token,
                         @Path("id") int id);

    @GET("peminjamanUser/{id}")
    Call<List<Peminjaman>> getPeminjamanUser(@Header("Authorization") String token,
                                             @Path("id") String id);

    @GET("denda/{id}")
    Call<List<Denda>> getDendaUser(@Header("Authorization") String token,
                                   @Path("id") String id);

    @FormUrlEncoded
    @POST("login")
    Call<Token> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("nomorHP") String nomorHP,
                                       @Field("password") String password,
                                       @Field("foto") String foto);

    @FormUrlEncoded
    @POST("buku")
    Call<ResponseBody> addBuku(@Header("Authorization") String token,
                               @Field("judul") String judul,
                               @Field("penulis") String penulis,
                               @Field("ISBN") String ISBN,
                               @Field("penerbit") String penerbit,
                               @Field("bidang") String bidang,
                               @Field("stok") int stok,
                               @Field("deskripsi") String deskripsi,
                               @Field("foto") String foto);

    @FormUrlEncoded
    @POST("peminjaman")
    Call<String> addPinjam (@Header("Authorization") String token,
                                   @Field("username") String username,
                                   @Field("ISBN") String ISBN);

    @FormUrlEncoded
    @PUT("user/{id}")
    Call<String> editProfil(@Header("Authorization") String token,
                            @Path("id") int id,
                            @Field("nama") String nama,
                            @Field("email") String email,
                            @Field("nomorHP") String nomorHP,
                            @Field("foto") String foto);


    @FormUrlEncoded
    @PUT("userRole/{id}")
    Call<ResponseBody> editRole(@Header("Authorization") String token,
                                @Path("id") int id,
                                @Field("role") String role);

    @FormUrlEncoded
    @PUT("userPassword/{id}")
    Call<String> editPassword(@Header("Authorization") String token,
                              @Path("id") int id,
                              @Field("password") String password);

    @FormUrlEncoded
    @PUT("buku/{id}")
    Call<ResponseBody> editBuku(@Header("Authorization") String token,
                                @Path("id") int id,
                                @Field("judul") String judul,
                                @Field("penulis") String penulis,
                                @Field("ISBN") String ISBN,
                                @Field("penerbit") String penerbit,
                                @Field("bidang") String bidang,
                                @Field("stok") int stok,
                                @Field("deskripsi") String deskripsi,
                                @Field("foto") String foto);

    @FormUrlEncoded
    @PUT("peminjaman/{id}")
    Call<ResponseBody> returnBuku(@Header("Authorization") String token,
                                  @Path("id") int id,
                                  @Field("status") String status);

    @FormUrlEncoded
    @PUT("denda/{id}")
    Call<ResponseBody> bayarDenda(@Header("Authorization") String token,
                                  @Path("id") int id,
                                  @Field("status") String status);

    @DELETE("buku/{id}")
    Call<ResponseBody> deleteBuku(@Header("Authorization") String token,
                                  @Path("id") int id);
}
