package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

public class Esya {

    @SerializedName("EsyaId")
    private int esyaId;

    @SerializedName("EsyaAdi")
    private String esyaAdi;

    public int getEsyaId() {
        return esyaId;
    }

    public void setEsyaId(int esyaId) {
        this.esyaId = esyaId;
    }

    public String getEsyaAdi() {
        return esyaAdi;
    }

    public void setEsyaAdi(String esyaAdi) {
        this.esyaAdi = esyaAdi;
    }
}
