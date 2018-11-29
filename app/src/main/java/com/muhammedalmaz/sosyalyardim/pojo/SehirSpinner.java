package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muham on 18.11.2018.
 */

public class SehirSpinner {
    @SerializedName("ID")
    private int id;
    @SerializedName("Ad")
    private String ad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
}
