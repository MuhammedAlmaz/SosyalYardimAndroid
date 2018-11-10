package com.muhammedalmaz.sosyalyardim.api;

import com.muhammedalmaz.sosyalyardim.pojo.GirisSonuc;

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

}
