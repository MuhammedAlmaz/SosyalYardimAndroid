package com.muhammedalmaz.sosyalyardim.api;

import com.muhammedalmaz.sosyalyardim.pojo.GirisSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SehirAutoCompleteSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SubeListeSonuc;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {


    @FormUrlEncoded
    @POST("/SosyalYardim/GirisApi/GirisYap.php")
    Call<GirisSonuc> girisYap(
            @Field("KullaniciEPosta") String kullaniciEPosta,
            @Field("Sifre") String sifre);

    @FormUrlEncoded
    @POST("/SosyalYardim/SubeApi/SubeListe.php")
    Call<SubeListeSonuc> subeListe(@Field("AndroidToken") String androidToken);

    @FormUrlEncoded
    @POST("/SosyalYardim/SehirApi/SehirAutoCompleteGetir.php")
    Call<SehirAutoCompleteSonuc> sehirAutoComplete(@Field("AndroidToken") String androidToken);

}
