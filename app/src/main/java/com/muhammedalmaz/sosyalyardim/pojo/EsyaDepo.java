package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

public class EsyaDepo {

    @SerializedName("EsyaId")
    private int esyaId;

    @SerializedName("Miktar")
    private int miktar;
    @SerializedName("EsyaAdi")
    private String esyaAdi;

    @SerializedName("EsyaDepoId")
    private int esyaDepoId;


    public int getEsyaId() {
        return esyaId;
    }

    public void setEsyaId(int esyaId) {
        this.esyaId = esyaId;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public String getEsyaAdi() {
        return esyaAdi;
    }

    public void setEsyaAdi(String esyaAdi) {
        this.esyaAdi = esyaAdi;
    }

    public int getEsyaDepoId() {
        return esyaDepoId;
    }

    public void setEsyaDepoId(int esyaDepoId) {
        this.esyaDepoId = esyaDepoId;
    }
}
