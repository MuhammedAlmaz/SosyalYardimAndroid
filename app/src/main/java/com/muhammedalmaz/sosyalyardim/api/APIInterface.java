package com.muhammedalmaz.sosyalyardim.api;

import com.muhammedalmaz.sosyalyardim.fragmentler.IhtiyacSahibiEkleFragment;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepoEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepoGuncelleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepoListeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepoSilmeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaGuncelleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaListeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaSilmeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.GirisSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.IhtiyacSahibiEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.IhtiyacSahibiGuncelleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.IhtiyacSahibiListeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.IhtiyacSahibiSilmeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciGuncelleSonuc;
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
    @POST("/SosyalYardim/EsyaApi/EsyaListe.php")
    Call<EsyaListeSonuc> esyaListe(@Field("AndroidToken") String androidToken);
    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaDepoApi/EsyaDepoListe.php")
    Call<EsyaDepoListeSonuc> esyaDepoListe(@Field("AndroidToken") String androidToken);
    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciListe.php")
    Call<KullaniciListeSonuc> kullaniciListe(@Field("AndroidToken") String androidToken);
    @FormUrlEncoded
    @POST("/SosyalYardim/IhtiyacSahibiApi/IhtiyacSahibiListe.php")
    Call<IhtiyacSahibiListeSonuc> ihtiyacSahibiListe(@Field("AndroidToken") String androidToken);




    //Silme İşlemleri
    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeSil.php")
    Call<SubeSilmeSonuc> subeSil(@Field("AndroidToken") String androidToken,@Field("SubeID") int subeID);
    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaApi/EsyaSil.php")
    Call<EsyaSilmeSonuc> esyaSil(@Field("AndroidToken") String androidToken, @Field("EsyaId") int esyaId);
    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaDepoApi/EsyaDepoSil.php")
    Call<EsyaDepoSilmeSonuc> esyaDepoSil(@Field("AndroidToken") String androidToken, @Field("EsyaDepoId") int esyaDepoId);
    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciSil.php")
    Call<KullaniciSilmeSonuc> kullaniciSil(@Field("AndroidToken") String androidToken, @Field("KullaniciID") int kullaniciID);

    @FormUrlEncoded
    @POST("/SosyalYardim/IhtiyacSahibiApi/IhtiyacSahibiSil.php")
    Call<IhtiyacSahibiSilmeSonuc> ihtiyacSahibiSil(@Field("AndroidToken") String androidToken, @Field("IhtiyacSahibiID") int ihtiyacSahibiID);



    // Güncelleme İşlemleri
    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeGuncelle.php")
    Call<SubeGuncelleSonuc> subeGuncelle(@Field("AndroidToken") String androidToken
            , @Field("SubeID") int subeID,@Field("IlID") int ilID,@Field("KullaniciID") int kullaniciID);
    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaApi/EsyaGuncelle.php")
    Call<EsyaGuncelleSonuc> esyaGuncelle(@Field("AndroidToken") String androidToken
            , @Field("EsyaId") int esyaId, @Field("EsyaAdi") String esyaAdi);
    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaDepoApi/EsyaDepoGuncelle.php")
    Call<EsyaDepoGuncelleSonuc> esyaDepoGuncelle(@Field("AndroidToken") String androidToken
            , @Field("EsyaDepoId") int esyaDepoId, @Field("Miktar") int miktar, @Field("EsyaId") int esyaId);
    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciGuncelle.php")
    Call<KullaniciGuncelleSonuc> kullaniciGuncelle(@Field("AndroidToken") String androidToken
            , @Field("KullaniciID") int kullaniciID
            , @Field("SehirID") int sehirID
            , @Field("EPosta") String ePosta
            , @Field("TelegramKullaniciAdi") String telegramKullaniciAdi
            , @Field("TCKimlikNo") String tcKimlikNo
            , @Field("Merkezde") int merkezde
            , @Field("Onayli") int onayli
            , @Field("Tel") String tel
            , @Field("KullaniciAdi") String kullaniciAdi
            , @Field("KullaniciSoyadi") String kullaniciSoyadi
    );
    @FormUrlEncoded
    @POST("/SosyalYardim/IhtiyacSahibiApi/IhtiyacSahibiGuncelle.php")
    Call<IhtiyacSahibiGuncelleSonuc> ihtiyacSahibiGuncelle(@Field("AndroidToken") String androidToken
            , @Field("IhtiyacSahibiID") int ihtiyacSahibiID
            , @Field("Adi") int adi
            , @Field("Soyadi") String soyadi
            , @Field("TelNo") String telNo
            , @Field("SehirID") String sehirID
            , @Field("Adres") int adres
            , @Field("Aciklama") int aciklama
    );



    //Ekleme İşlemleri

    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeEkle.php")
    Call<SubeEkleSonuc> subeEkle(@Field("AndroidToken") String androidToken
            , @Field("IlID") int ilID, @Field("KullaniciID") int kullaniciID);
    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciEkle.php")
    Call<KullaniciEkleSonuc> kullaniciEkle(@Field("AndroidToken") String androidToken
            , @Field("SehirID") int sehirID
            , @Field("EPosta") String ePosta
            , @Field("TelegramKullaniciAdi") String telegramKullaniciAdi
            , @Field("TCKimlikNo") String tcKimlikNo
            , @Field("Merkezde") int merkezde
            , @Field("Onayli") int onayli
            , @Field("Tel") String tel
            , @Field("KullaniciAdi") String kullaniciAdi
            , @Field("KullaniciSoyadi") String kullaniciSoyadi);
    @FormUrlEncoded
    @POST("/SosyalYardim/IhtiyacSahibiApi/IhtiyacSahibiEkle.php")
    Call<IhtiyacSahibiEkleSonuc> ihtiyacSahibiEkle(@Field("AndroidToken") String androidToken
            , @Field("Adi") String adi
            , @Field("Soyadi") String soyadi
            , @Field("TelNo") String telNo
            , @Field("SehirID") int sehirID
            , @Field("Adres") String adres
            , @Field("Aciklama") String aciklama
    );

    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaApi/EsyaEkle.php")
    Call<EsyaEkleSonuc> esyaEkle(@Field("AndroidToken") String androidToken
            , @Field("EsyaAdi") String esyaAdi);
    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaDepoApi/EsyaDepoEkle.php")
    Call<EsyaDepoEkleSonuc> esyaDepoEkle(@Field("AndroidToken") String androidToken
            , @Field("EsyaId") int esyaId, @Field("Miktar") int miktar);





    //Spinner


    @FormUrlEncoded
    @POST("/SosyalYardim/SehirApi/SehirSpinnerGetir.php")
    Call<SehirSpinnerSonuc> sehirSpinnerGetir(@Field("AndroidToken") String androidToken);


    @FormUrlEncoded
    @POST("/SosyalYardim/EsyaApi/EsyaSpinnerGetir.php")
    Call<EsyaSpinnerSonuc> esyaSpinnerGetir(@Field("AndroidToken") String androidToken);

    @FormUrlEncoded
    @POST("/SosyalYardim/KullaniciApi/KullaniciSpinnerGetir.php")
    Call<KullaniciSpinnerSonuc> kullaniciSpinnerGetir(@Field("AndroidToken") String androidToken);

}
