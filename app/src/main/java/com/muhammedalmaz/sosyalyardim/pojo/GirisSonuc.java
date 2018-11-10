package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muham on 9.11.2018.
 */

public class GirisSonuc {
    @SerializedName("Sonuc")
    public String sonuc;
    @SerializedName("Icerik")
    public String icerik;
    @SerializedName("HataKodu")
    public int hataKodu;
    @SerializedName("KullaniciBilgileri")
    public KullaniciBilgileri kullaniciBilgileri;
}
