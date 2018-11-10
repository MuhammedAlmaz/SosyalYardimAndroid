package com.muhammedalmaz.sosyalyardim.pojo;

import com.google.gson.annotations.SerializedName;

public class KullaniciBilgileri {
    @SerializedName("KullaniciAdi")
    public String kullaniciAdi;
    @SerializedName("AndroidToken")
    public String androidToken;
    @SerializedName("KullaniciSoyadi")
    public String kullaniciSoyadi;
}
