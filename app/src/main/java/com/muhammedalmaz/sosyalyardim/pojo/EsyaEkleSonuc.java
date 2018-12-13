package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

public class EsyaEkleSonuc {

    @SerializedName("Sonuc")
    public String sonuc;
    @SerializedName("Aciklama")
    public String aciklama;
    @SerializedName("HataKodu")
    public int hataKodu;
}
