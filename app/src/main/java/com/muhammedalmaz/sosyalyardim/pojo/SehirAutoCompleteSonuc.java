package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by muham on 18.11.2018.
 */

public class SehirAutoCompleteSonuc {
    @SerializedName("Sonuc")
    public String sonuc;
    @SerializedName("Aciklama")
    public String aciklama;
    @SerializedName("HataKodu")
    public int hataKodu;
    @SerializedName("SehirListe")
    public ArrayList<SehirAutoComplete> sehirAutoCompleteList;
}

