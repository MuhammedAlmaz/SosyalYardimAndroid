package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.onaylayici.Ayarlar;
import com.muhammedalmaz.sosyalyardim.onaylayici.OnaylanacakNesne;
import com.muhammedalmaz.sosyalyardim.onaylayici.OnaylayiciTip;
import com.muhammedalmaz.sosyalyardim.pojo.IhtiyacSahibi;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinner;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinnerSonuc;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class IhtiyacSahibiDuzenleFragment extends Fragment {


    public IhtiyacSahibiDuzenleFragment() {
        // Required empty public constructor
    }

    IhtiyacSahibi ihtiyacSahibi;

    @SuppressLint("ValidFragment")
    public IhtiyacSahibiDuzenleFragment(IhtiyacSahibi ihtiyacSahibi) {
        this.ihtiyacSahibi = ihtiyacSahibi;
    }

    int seciliSehirPozisyon;
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    ArrayList<String> sehirListe = new ArrayList<String>();
    ArrayList<SehirSpinner> sehirSpinnerArrayList;
    ArrayList<OnaylanacakNesne> onaylanacakNesneArrayList = new ArrayList<OnaylanacakNesne>();

    public void spinnerDoldur() {
        Call<SehirSpinnerSonuc> sehirSpinnerSonucCall = apiInterface.sehirSpinnerGetir(HesapBilgileri.androidToken);
        try {
            SehirSpinnerSonuc sehirAutoCompleteSonuc = sehirSpinnerSonucCall.execute().body();
            if (!dialogMesajlari.hataMesajiGoster(sehirAutoCompleteSonuc.hataKodu)) {
                sehirSpinnerArrayList = sehirAutoCompleteSonuc.sehirSpinnerArrayList;
                for (int i = 0; i < sehirSpinnerArrayList.size(); i++) {
                    SehirSpinner sehirSpinner = sehirSpinnerArrayList.get(i);
                    sehirListe.add(sehirSpinner.getAd());
                    if (sehirSpinner.getId() == ihtiyacSahibi.getSehirID()) {
                        seciliSehirPozisyon = i;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            dialogMesajlari.hataMesajiGoster();
        }
    }

    Spinner
            spinnerSehir;
    EditText
            txtAdi,
            txtSoyadi,
            txtAdres,
            txtAciklama,
            txtTelNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_ihtiyac_sahibi_duzenle, container, false);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        dialogMesajlari = new DialogMesajlari(getActivity());


        spinnerSehir = (Spinner) fragmentView.findViewById(R.id.SpinnerSehir);

        txtAdi = (EditText) fragmentView.findViewById(R.id.TxtIhtiyacSahibiAdi);
        txtSoyadi = (EditText) fragmentView.findViewById(R.id.TxtIhtiyacSahibiSoyadi);
        txtAdres = (EditText) fragmentView.findViewById(R.id.TxtIhtiyacSahibiAdres);
        txtAciklama = (EditText) fragmentView.findViewById(R.id.TxtIhtiyacSahibiAciklama);
        txtTelNo = (EditText) fragmentView.findViewById(R.id.TxtIhtiyacSahibiTelNo);

        spinnerDoldur();


        ArrayAdapter<String> spinnerArrayAdapterSehir = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, sehirListe);
        spinnerArrayAdapterSehir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerSehir.setAdapter(spinnerArrayAdapterSehir);


        spinnerSehir.setSelection(seciliSehirPozisyon);
        txtAdi.setText(ihtiyacSahibi.getAdi());
        txtSoyadi.setText(ihtiyacSahibi.getSoyadi());
        txtAdres.setText(ihtiyacSahibi.getAdres());
        txtAciklama.setText(ihtiyacSahibi.getAciklama());
        txtTelNo.setText(ihtiyacSahibi.getTelNo());

        onaylayiciSinifiniOlustur();

        return fragmentView;
    }

    public void onaylayiciSinifiniOlustur() {
        onaylanacakNesneArrayList.add(
                new OnaylanacakNesne(txtAdi, new Ayarlar.Yapici()
                        .setHataMesaji("Adınız En Az 1 En Fazla 25 Karakter Olmalıdır.")
                        .setMinimumKarakterSayisi(1)
                        .setMaksimumKarakterSayisi(25)
                        .setOnaylayiciTip(OnaylayiciTip.NORMAL_YAZI)
                        .Yap())
        );
        onaylanacakNesneArrayList.add(
                new OnaylanacakNesne(txtSoyadi, new Ayarlar.Yapici()
                        .setHataMesaji("SoyAdınız En Az 1 En Fazla 25 Karakter Olmalıdır.")
                        .setMinimumKarakterSayisi(1)
                        .setMaksimumKarakterSayisi(25)
                        .setOnaylayiciTip(OnaylayiciTip.NORMAL_YAZI)
                        .Yap())
        );
        onaylanacakNesneArrayList.add(
                new OnaylanacakNesne(txtTelNo, new Ayarlar.Yapici()
                        .setHataMesaji("Lütfen Geçerli Bir Telefon Numarası Giriniz.")
                        .setMinimumKarakterSayisi(10)
                        .setMaksimumKarakterSayisi(10)
                        .setOnaylayiciTip(OnaylayiciTip.NORMAL_YAZI)
                        .Yap())
        );
    }

}
