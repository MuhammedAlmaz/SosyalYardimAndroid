package com.muhammedalmaz.sosyalyardim.onaylayici;

public class Ayarlar {
    OnaylayiciTip onaylayiciTip;
    int maksimumKarakterSayisi=999999;
    int minimumKarakterSayisi=0;
    String hataMesaji;

    public OnaylayiciTip getOnaylayiciTip() {
        return onaylayiciTip;
    }

    public void setOnaylayiciTip(OnaylayiciTip onaylayiciTip) {
        this.onaylayiciTip = onaylayiciTip;
    }

    public int getMaksimumKarakterSayisi() {
        return maksimumKarakterSayisi;
    }

    public void setMaksimumKarakterSayisi(int maksimumKarakterSayisi) {
        this.maksimumKarakterSayisi = maksimumKarakterSayisi;
    }

    public int getMinimumKarakterSayisi() {
        return minimumKarakterSayisi;
    }

    public void setMinimumKarakterSayisi(int minimumKarakterSayisi) {
        this.minimumKarakterSayisi = minimumKarakterSayisi;
    }

    public String getHataMesaji() {
        return hataMesaji;
    }

    public void setHataMesaji(String hataMesaji) {
        this.hataMesaji = hataMesaji;
    }
}
