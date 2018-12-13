package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IhtiyacSahibiListeSonuc {

    @SerializedName("Sonuc")
    public String sonuc;
    @SerializedName("Aciklama")
    public String aciklama;
    @SerializedName("HataKodu")
    public int hataKodu;
    @SerializedName("IhtiyacSahibiListe")
    public ArrayList<IhtiyacSahibi> ihtiyacSahibiListe;
}
