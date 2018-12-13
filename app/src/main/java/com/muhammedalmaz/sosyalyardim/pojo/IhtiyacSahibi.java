package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

public class IhtiyacSahibi {

    @SerializedName("Adi")
    private String adi;

    @SerializedName("Soyadi")
    private String soyadi;

    @SerializedName("TelNo")
    private String telNo;

    public int getSehirID() {
        return sehirID;
    }

    public void setSehirID(int sehirID) {
        this.sehirID = sehirID;
    }

    @SerializedName("SehirID")
    private int sehirID;

    @SerializedName("Adres")
    private String adres;

    @SerializedName("Aciklama")
    private String aciklama;

    @SerializedName("SehirAd")
    private String sehirAd;

    public int getIhtiyacSahibiID() {
        return ihtiyacSahibiID;
    }

    public void setIhtiyacSahibiID(int ihtiyacSahibiID) {
        this.ihtiyacSahibiID = ihtiyacSahibiID;
    }

    @SerializedName("IhtiyacSahibiID")
    private int ihtiyacSahibiID;


    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }


    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getSehirAd() {
        return sehirAd;
    }

    public void setSehirAd(String sehirAd) {
        this.sehirAd = sehirAd;
    }
}
