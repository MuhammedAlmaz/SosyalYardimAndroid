package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class YetkilerimFragment extends Fragment {

    TextView
            lblYetkiKullaniciListesi,
            lblYetkiKullaniciEkle,
            lblYetkiKullaniciSil,
            lblYetkiKullaniciDuzenle,
            lblYetkiSubeListesi,
            lblYetkiSubeEkle,
            lblYetkiSubeSil,
            lblYetkiSubeDuzenle,
            lblYetkiSubeDetay;

    public YetkilerimFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_yetkilerim, container, false);

        lblYetkiKullaniciListesi = fragmentView.findViewById(R.id.LblYetkiKullaniciListesi);
        lblYetkiKullaniciEkle = fragmentView.findViewById(R.id.LblYetkiKullaniciEkle);
        lblYetkiKullaniciSil = fragmentView.findViewById(R.id.LblYetkiKullaniciSil);
        lblYetkiKullaniciDuzenle = fragmentView.findViewById(R.id.LblYetkiKullaniciDuzenle);
        lblYetkiSubeListesi = fragmentView.findViewById(R.id.LblYetkiSubeListesi);
        lblYetkiSubeEkle = fragmentView.findViewById(R.id.LblYetkiSubeEkle);
        lblYetkiSubeSil = fragmentView.findViewById(R.id.LblYetkiSubeSil);
        lblYetkiSubeDuzenle = fragmentView.findViewById(R.id.LblYetkiSubeDuzenle);
        lblYetkiSubeDetay = fragmentView.findViewById(R.id.LblYetkiSubeDetay);
        lblYetkiKullaniciListesi.setText(HesapBilgileri.yetkiKullaniciListesi==1?"EVET":"HAYIR" );
                lblYetkiKullaniciEkle.setText(HesapBilgileri.yetkiKullaniciEkle==1?"EVET":"HAYIR" );
        lblYetkiKullaniciSil.setText(HesapBilgileri.yetkiKullaniciSil==1?"EVET":"HAYIR" );
                lblYetkiKullaniciDuzenle.setText(HesapBilgileri.yetkiKullaniciDuzenle==1?"EVET":"HAYIR" );
        lblYetkiSubeListesi.setText(HesapBilgileri.yetkiSubeListesi==1?"EVET":"HAYIR" );
                lblYetkiSubeEkle.setText(HesapBilgileri.yetkiSubeEkle==1?"EVET":"HAYIR" );
        lblYetkiSubeSil.setText(HesapBilgileri.yetkiSubeSil==1?"EVET":"HAYIR" );
                lblYetkiSubeDuzenle.setText(HesapBilgileri.yetkiSubeDuzenle==1?"EVET":"HAYIR" );
        lblYetkiSubeDetay.setText(HesapBilgileri.yetkiSubeDetay==1?"EVET":"HAYIR" );




        return fragmentView;
    }

}
