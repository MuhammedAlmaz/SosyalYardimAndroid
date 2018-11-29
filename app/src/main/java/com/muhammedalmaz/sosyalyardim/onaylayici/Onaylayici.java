package com.muhammedalmaz.sosyalyardim.onaylayici;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class Onaylayici implements View.OnClickListener{
    Context context;
    ITiklanmaListener listener;
    String hataMesaji = "";
    ArrayList<OnaylanacakNesne> onaylanacakNesneArrayList;
    public Onaylayici(Context context, ArrayList<OnaylanacakNesne> onaylanacakNesneArrayList)
    {
        this.context=context;
        this.listener=listener;
        this.onaylanacakNesneArrayList=onaylanacakNesneArrayList;

    }

    @Override
    public void onClick(View v) {
        Boolean hataVarMi=false;
        for(OnaylanacakNesne nesne:onaylanacakNesneArrayList){
            Kontrol kontrol=new Kontrol(nesne);
            if(!kontrol.kontrolEt())
            {
                hataVarMi=true;
                hataMesaji=kontrol.mesajiGetir();
                break;
            }
        }
        if(!hataVarMi)
        {
            basarili();
        }else{
            Toast.makeText(context, hataMesaji, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void basarili();
}
