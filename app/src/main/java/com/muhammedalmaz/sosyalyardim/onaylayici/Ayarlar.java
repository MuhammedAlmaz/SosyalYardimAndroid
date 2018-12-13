package com.muhammedalmaz.sosyalyardim.onaylayici;

public class Ayarlar {
    OnaylayiciTip onaylayiciTip;
    int maksimumKarakterSayisi = 999999;
    int minimumKarakterSayisi = 0;
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

    public Ayarlar(
            OnaylayiciTip onaylayiciTip,
            int maksimumKarakterSayisi,
            int minimumKarakterSayisi,
            String hataMesaji
    ) {
        this.onaylayiciTip = onaylayiciTip;
        this.maksimumKarakterSayisi = maksimumKarakterSayisi;
        this.minimumKarakterSayisi = minimumKarakterSayisi;
        this.hataMesaji = hataMesaji;
    }

    public static class Yapici {

        OnaylayiciTip onaylayiciTip;
        int maksimumKarakterSayisi = 999999;
        int minimumKarakterSayisi = 0;
        String hataMesaji;
        public Yapici(){}
        public OnaylayiciTip getOnaylayiciTip() {
            return onaylayiciTip;
        }

        public Yapici setOnaylayiciTip(OnaylayiciTip onaylayiciTip) {
            this.onaylayiciTip = onaylayiciTip;
            return this;
        }

        public Yapici setMaksimumKarakterSayisi(int maksimumKarakterSayisi) {
            this.maksimumKarakterSayisi = maksimumKarakterSayisi;
            return this;
        }

        public Yapici setMinimumKarakterSayisi(int minimumKarakterSayisi) {
            this.minimumKarakterSayisi = minimumKarakterSayisi;
            return this;
        }


        public Yapici setHataMesaji(String hataMesaji) {
            this.hataMesaji = hataMesaji;
            return this;
        }

        public Ayarlar Yap() {
            return new Ayarlar(onaylayiciTip, maksimumKarakterSayisi, minimumKarakterSayisi, hataMesaji);
        }
    }
}
