package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EsyaSpinnerSonuc {

    @SerializedName("Sonuc")
    public String sonuc;
    @SerializedName("Aciklama")
    public String aciklama;
    @SerializedName("HataKodu")
    public int hataKodu;
    @SerializedName("EsyaListe")
    public ArrayList<Esya> esyaSpinnerArrayList;
}
