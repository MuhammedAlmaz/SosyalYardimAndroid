package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muham on 18.11.2018.
 */

public class Sube {

    @SerializedName("SubeID")
    private int subeId;
    @SerializedName("IlID")
    private int ilId;
    @SerializedName("GorevliID")
    private int gorevliId;
    @SerializedName("SubeIl")
    private String subeIl;
    @SerializedName("SubeSorumlusu")
    private String subeSorumlusu;

    public int getSubeId() {
        return subeId;
    }

    public void setSubeId(int subeId) {
        this.subeId = subeId;
    }

    public int getIlId() {
        return ilId;
    }

    public void setIlId(int ilId) {
        this.ilId = ilId;
    }

    public int getGorevliId() {
        return gorevliId;
    }

    public void setGorevliId(int gorevliId) {
        this.gorevliId = gorevliId;
    }

    public String getSubeIl() {
        return subeIl;
    }

    public void setSubeIl(String subeIl) {
        this.subeIl = subeIl;
    }

    public String getSubeSorumlusu() {
        return subeSorumlusu;
    }

    public void setSubeSorumlusu(String subeSorumlusu) {
        this.subeSorumlusu = subeSorumlusu;
    }


}
