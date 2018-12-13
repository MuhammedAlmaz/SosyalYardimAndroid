package com.muhammedalmaz.sosyalyardim.onaylayici;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kontrol implements IKontrol{
    OnaylanacakNesne nesne;
    public Kontrol(OnaylanacakNesne nesne){
        this.nesne=nesne;
    }
    @Override
    public Boolean kontrolEt() {
        Boolean Sonuc=false;
        EditText editText=nesne.editText;
        int karakterSayisi=editText.getText().length();
        if(karakterSayisi>=nesne.ayarlar.getMinimumKarakterSayisi()&&karakterSayisi<=nesne.ayarlar.getMaksimumKarakterSayisi())
        {
            try {
                if (nesne.ayarlar.onaylayiciTip == OnaylayiciTip.EMAIL) {
                    String regExpFormul =
                            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                    CharSequence eMail = editText.getText().toString();

                    Pattern pattern = Pattern.compile(regExpFormul, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(eMail);

                    Sonuc = matcher.matches();
                } else if (nesne.ayarlar.onaylayiciTip == OnaylayiciTip.TC_KIMLIK_NUMARASI) {

                    String tmp = editText.getText().toString();

                    if (tmp.length() == 11) {
                        int totalOdd = 0;

                        int totalEven = 0;

                        for (int i = 0; i < 9; i++) {
                            int val = Integer.valueOf(tmp.substring(i, i + 1));

                            if (i % 2 == 0) {
                                totalOdd += val;
                            } else {
                                totalEven += val;
                            }
                        }

                        int total = totalOdd + totalEven + Integer.valueOf(tmp.substring(9, 10));

                        int lastDigit = total % 10;

                        if (tmp.substring(10).equals(String.valueOf(lastDigit))) {
                            int check = (totalOdd * 7 - totalEven) % 10;

                            if (tmp.substring(9, 10).equals(String.valueOf(check))) {
                                Sonuc= true;
                            }
                        }
                    }

                }else if(nesne.ayarlar.onaylayiciTip==OnaylayiciTip.KREDI_KARTI)
                {
                    String krediKartiNumarasi=editText.getText().toString();
                    ArrayList<String> krediKartıRegexListe=new ArrayList<String>();

                    String visa = "^4[0-9]{6,}$";
                    krediKartıRegexListe.add(visa);
                    String masterCard= "^5[1-5][0-9]{5,}$";
                    krediKartıRegexListe.add(masterCard);
                    String ameExp = "^3[47][0-9]{5,}$";
                    krediKartıRegexListe.add(ameExp);
                    String dinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
                    krediKartıRegexListe.add(dinClb);
                    String discover= "^6(?:011|5[0-9]{2})[0-9]{3,}$";
                    krediKartıRegexListe.add(discover);
                    String jcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
                    krediKartıRegexListe.add(jcb);
                    for(String p:krediKartıRegexListe){
                        if(krediKartiNumarasi.matches(p)){
                            Sonuc=true;
                            break;
                        }
                    }
                }else if(nesne.ayarlar.onaylayiciTip==OnaylayiciTip.SIFRE_SEKIZ_KARAKTERLI){
                    return editText.getText().toString().length()>=8;
                }else if(nesne.ayarlar.onaylayiciTip==OnaylayiciTip.SIFRE_EN_AZ_BIR_KARAKTER_BUYUK_KUCUK)
                {
                    String sifre=editText.getText().toString();
                    Boolean sekizKarakterMi=sifre.length()>=8;
                    if(sekizKarakterMi)
                    {
                        int buyukHarfSayisi=0;
                        int kucukHarfSayisi=0;
                        for (int k = 0; k < sifre.length(); k++) {
                            if (Character.isUpperCase(sifre.charAt(k))){ buyukHarfSayisi++;}
                            if (Character.isLowerCase(sifre.charAt(k))){ kucukHarfSayisi++;}
                        }
                        if (buyukHarfSayisi>0&&kucukHarfSayisi>0)
                        {
                            Sonuc=true;
                        }
                    }
                }else if(nesne.ayarlar.onaylayiciTip==OnaylayiciTip.SIFRE_EN_AZ_BIR_KARAKTER_BUYUK_KUCUK_SAYI)
                {
                    String sifre=editText.getText().toString();
                    Boolean sekizKarakterMi=sifre.length()>=8;
                    if(sekizKarakterMi)
                    {
                        int buyukHarfSayisi=0;
                        int kucukHarfSayisi=0;
                        int numaraSayisi=0;
                        for (int k = 0; k < sifre.length(); k++) {
                            if (Character.isUpperCase(sifre.charAt(k))){ buyukHarfSayisi++;}
                            if (Character.isLowerCase(sifre.charAt(k))){ kucukHarfSayisi++;}
                            if (Character.isDigit(sifre.charAt(k))){ numaraSayisi++;}
                        }
                        if (buyukHarfSayisi>0&&kucukHarfSayisi>0&&numaraSayisi>0)
                        {
                            Sonuc=true;
                        }
                    }
                }else if(nesne.ayarlar.onaylayiciTip==OnaylayiciTip.SIFRE_EN_AZ_BIR_KARAKTER_BUYUK_KUCUK_SAYI_SEMBOL)
                {
                    String sifre=editText.getText().toString();
                    Boolean sekizKarakterMi=sifre.length()>=8;
                    if(sekizKarakterMi)
                    {
                        String semboller = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
                        int buyukHarfSayisi=0;
                        int kucukHarfSayisi=0;
                        int numaraSayisi=0;
                        int sembolSayisi=0;
                        for (int k = 0; k < sifre.length(); k++) {
                            if (Character.isUpperCase(sifre.charAt(k))){ buyukHarfSayisi++;}
                            if (Character.isLowerCase(sifre.charAt(k))){ kucukHarfSayisi++;}
                            if (semboller.contains(sifre.charAt(k)+"")){ sembolSayisi++;}
                            if (Character.isDigit(sifre.charAt(k))){ numaraSayisi++;}
                        }
                        if (buyukHarfSayisi>0&&kucukHarfSayisi>0&&numaraSayisi>0&&sembolSayisi>0)
                        {
                            Sonuc=true;
                        }
                    }
                }else if(nesne.ayarlar.onaylayiciTip==OnaylayiciTip.NORMAL_YAZI){
                    Sonuc=true;
                }
            }catch (Exception err)
            {
                err.printStackTrace();
                return false;
            }
        }
        return Sonuc;
    }

    @Override
    public String mesajiGetir() {
        return nesne.ayarlar.getHataMesaji();
    }
}
