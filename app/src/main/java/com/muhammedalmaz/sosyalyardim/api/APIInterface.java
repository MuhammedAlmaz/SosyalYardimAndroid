package com.muhammedalmaz.sosyalyardim.api;

import com.muhammedalmaz.sosyalyardim.pojo.GirisSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciListeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSilmeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SubeEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SubeGuncelleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SubeListeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SubeSilmeSonuc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {


    @FormUrlEncoded
    @POST("/SosyalYardim/GirisApi/GirisYap.php")
    Call<GirisSonuc> girisYap(
            @Field("KullaniciEPosta") String kullaniciEPosta,
            @Field("Sifre") String sifre);

    //Listeleme
    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeListe.php")
    Call<SubeListeSonuc> subeListe(@Field("AndroidToken") String androidToken);
    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciListe.php")
    Call<KullaniciListeSonuc> kullaniciListe(@Field("AndroidToken") String androidToken);




    //Silme İşlemleri
    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeSil.php")
    Call<SubeSilmeSonuc> subeSil(@Field("AndroidToken") String androidToken,@Field("SubeID") int subeID);
    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciSil.php")
    Call<KullaniciSilmeSonuc> kullaniciSil(@Field("AndroidToken") String androidToken, @Field("KullaniciID") int kullaniciID);



    // Güncelleme İşlemleri
    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeGuncelle.php")
    Call<SubeGuncelleSonuc> subeGuncelle(@Field("AndroidToken") String androidToken
            , @Field("SubeID") int subeID,@Field("IlID") int ilID,@Field("KullaniciID") int kullaniciID);



    //Ekleme İşlemleri

    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeEkle.php")
    Call<SubeEkleSonuc> subeEkle(@Field("AndroidToken") String androidToken
            , @Field("IlID") int ilID, @Field("KullaniciID") int kullaniciID);

    //Spinner


    @FormUrlEncoded
    @POST("/SosyalYardim/SehirApi/SehirSpinnerGetir.php")
    Call<SehirSpinnerSonuc> sehirSpinnerGetir(@Field("AndroidToken") String androidToken);

    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciSpinnerGetir.php")
    Call<KullaniciSpinnerSonuc> kullaniciSpinnerGetir(@Field("AndroidToken") String androidToken);

}
