package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSpinner;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinner;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.Sube;
import com.muhammedalmaz.sosyalyardim.pojo.SubeEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SubeGuncelleSonuc;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubeEkleFragment extends Fragment {


    private String TAG = SubeEkleFragment.class.getName();
    private Sube sube;
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    Spinner spinnerSubeGorevlisi, spinnerSubeIl;
    ArrayList<String> sehirListe = new ArrayList<String>();
    ArrayList<String> kullaniciListe = new ArrayList<String>();
    ArrayList<SehirSpinner> sehirSpinnerArrayList;
    ArrayList<KullaniciSpinner> kullaniciSpinnerArrayList;
    public SubeEkleFragment() {
        // Required empty public constructor
    }

    public void spinnerDoldur() {
        Call<SehirSpinnerSonuc> sehirSpinnerSonucCall = apiInterface.sehirSpinnerGetir(HesapBilgileri.androidToken);
        Call<KullaniciSpinnerSonuc> kullaniciSpinnerSonucCall = apiInterface.kullaniciSpinnerGetir(HesapBilgileri.androidToken);
        try {
            SehirSpinnerSonuc sehirAutoCompleteSonuc = sehirSpinnerSonucCall.execute().body();
            KullaniciSpinnerSonuc kullaniciSpinnerSonuc = kullaniciSpinnerSonucCall.execute().body();
            if (!dialogMesajlari.hataMesajiGoster(sehirAutoCompleteSonuc.hataKodu)
                    && !dialogMesajlari.hataMesajiGoster(kullaniciSpinnerSonuc.hataKodu)) {
                sehirSpinnerArrayList = sehirAutoCompleteSonuc.sehirSpinnerArrayList;
                for(int i=0;i<sehirSpinnerArrayList.size();i++)
                {
                    SehirSpinner sehirSpinner=sehirSpinnerArrayList.get(i);
                    sehirListe.add(sehirSpinner.getAd());
                }
                kullaniciSpinnerArrayList = kullaniciSpinnerSonuc.kullaniciSpinnerArrayList;
                for(int i=0;i<kullaniciSpinnerArrayList.size();i++) {
                    KullaniciSpinner kullaniciSpinner = kullaniciSpinnerArrayList.get(i);
                    kullaniciListe.add(kullaniciSpinner.getAd());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            dialogMesajlari.hataMesajiGoster();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_sube_ekle, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        dialogMesajlari = new DialogMesajlari(getActivity());
        spinnerDoldur();
        spinnerSubeGorevlisi = (Spinner) fragmentView.findViewById(R.id.SpinnerSubeGorevlisi);
        spinnerSubeIl = (Spinner) fragmentView.findViewById(R.id.SpinnerSubeIl);
        ArrayAdapter<String> spinnerArrayAdapterSehir = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, sehirListe);
        ArrayAdapter<String> spinnerArrayAdapterKullanici = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, kullaniciListe);
        spinnerArrayAdapterSehir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArrayAdapterKullanici.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSubeGorevlisi.setAdapter(spinnerArrayAdapterKullanici);
        spinnerSubeIl.setAdapter(spinnerArrayAdapterSehir);


        ((BootstrapButton)fragmentView.findViewById(R.id.BtnSubeEkle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<SubeEkleSonuc> subeEkleSonucCall=apiInterface.subeEkle(HesapBilgileri.androidToken,
                        sehirSpinnerArrayList.get(spinnerSubeIl.getSelectedItemPosition()).getId(),
                        kullaniciSpinnerArrayList.get(spinnerSubeGorevlisi.getSelectedItemPosition()).getId()
                );
                subeEkleSonucCall.enqueue(new Callback<SubeEkleSonuc>() {
                    @Override
                    public void onResponse(Call<SubeEkleSonuc> call, Response<SubeEkleSonuc> response) {
                        SubeEkleSonuc subeEkleSonuc=response.body();
                        if(!dialogMesajlari.hataMesajiGoster(subeEkleSonuc.hataKodu))
                        {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager
                                    .beginTransaction()
                                    .replace(
                                            R.id.ContentFrame, new SubeFragment(), "SubeFragment"
                                    ).commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<SubeEkleSonuc> call, Throwable t) {
                        call.cancel();
                        dialogMesajlari.hataMesajiGoster();
                    }
                });
            }
        });
        return fragmentView;
    }

}
