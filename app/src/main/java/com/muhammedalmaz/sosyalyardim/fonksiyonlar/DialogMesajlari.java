package com.muhammedalmaz.sosyalyardim.fonksiyonlar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import android.content.Context;
import android.content.Intent;

import com.muhammedalmaz.sosyalyardim.GirisEkranActivity;

/**
 * Created by muhamed on 10.11.2018.
 */

public class DialogMesajlari {
    Context context;
    public DialogMesajlari(Context context){
        this.context=context;
    }


    public void evetHayirDialogGoster(String icerik,SweetAlertDialog.OnSweetClickListener listener){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Işlem")
                .setContentText(icerik)
                .setCancelText("Hayır")
                .setConfirmText("Evet")
                .showCancelButton(true)
                .setConfirmClickListener(listener)
                .show();
    }
    public void evetHayirDialogGoster(SweetAlertDialog.OnSweetClickListener listener){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Işlem")
                .setContentText("Devam Etmek İstiyor musunuz?")
                .setCancelText("Hayır")
                .setConfirmText("Evet")
                .showCancelButton(true)
                .setConfirmClickListener(listener)
                .show();
    }

    public void basariliIslemDialogGoster(String icerik)
    {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Sonuç")
                .setContentText(icerik)
                .show();
    }
    public void  basariliIslemDialogGoster(){
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Sonuç")
                .setContentText("İşlem Başarıyla Tamamlandı")
                .show();
    }
    public  void hataMesajiGoster(){
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Hata")
                .setContentText("Üzgünüz. Bir Sorun Oluştu...")
                .show();
    }

    public  void hataMesajiGoster(String icerik){
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Hata")
                .setContentText(icerik)
                .show();
    }
    public boolean hataMesajiGoster(int hataKodu){
        String hataMesaji;
        if(hataKodu==-1)
        {
            return false;
        }else if(hataKodu==1)
        {
            hataMesaji="Bilgileri eksik girdiniz";
        }else if(hataKodu==2)
        {
            hataMesaji="Kullanıcı adınız veya şifreniz hatalı";
        }else if(hataKodu==3)
        {
            hataMesaji="Yetkisiz işlem , lütfen tekrar giriş yapınız.";
            context.startActivity(new Intent(context, GirisEkranActivity.class));
        }else{
            hataMesaji="?????";
        }

        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Hata")
                .setContentText(hataMesaji)
                .show();
        return true;
    }
}
