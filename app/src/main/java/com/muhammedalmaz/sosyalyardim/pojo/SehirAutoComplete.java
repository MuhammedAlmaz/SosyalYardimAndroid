package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muham on 18.11.2018.
 */

public class SehirAutoComplete {
    @SerializedName("ID")
    private int id;
    @SerializedName("Ad")
    private int ad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }
}
