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
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.Esya;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepo;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepoEkleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSpinner;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSpinnerSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinner;
import com.muhammedalmaz.sosyalyardim.pojo.SehirSpinnerSonuc;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsyaDepoEkleFragment extends Fragment {


    private String TAG = EsyaDepoEkleFragment.class.getName();
    private EsyaDepo esyaDepo;
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    EditText txtMiktar;
    Spinner spinnerEsya;
    ArrayList<String> esyaListe = new ArrayList<String>();
    ArrayList<Esya> esyaSpinnerArrayList;
    public EsyaDepoEkleFragment() {
        // Required empty public constructor
    }

    public void spinnerDoldur() {
        Call<EsyaSpinnerSonuc> esyaSpinnerSonucCall = apiInterface.esyaSpinnerGetir(HesapBilgileri.androidToken);
        try {
            EsyaSpinnerSonuc esyaAutoCompleteSonuc = esyaSpinnerSonucCall.execute().body();
            if (!dialogMesajlari.hataMesajiGoster(esyaAutoCompleteSonuc.hataKodu)) {
                esyaSpinnerArrayList = esyaAutoCompleteSonuc.esyaSpinnerArrayList;
                for(int i=0;i<esyaSpinnerArrayList.size();i++)
                {
                    Esya esya=esyaSpinnerArrayList.get(i);
                    esyaListe.add(esya.getEsyaAdi());
                    Log.e("Esya",esya.getEsyaAdi());
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
        View fragmentView=  inflater.inflate(R.layout.fragment_esya_depo_ekle, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        txtMiktar=(EditText)fragmentView.findViewById(R.id.TxtMiktar);
        spinnerEsya=(Spinner) fragmentView.findViewById(R.id.SpinnerEsya);
        dialogMesajlari = new DialogMesajlari(getActivity());

        spinnerDoldur();

        ArrayAdapter<String> spinnerArrayAdapterEsya = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, esyaListe);
        spinnerArrayAdapterEsya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEsya.setAdapter(spinnerArrayAdapterEsya);
        
        
        ((BootstrapButton)fragmentView.findViewById(R.id.BtnEsyaDepoEkle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<EsyaDepoEkleSonuc> esyaDepoEkleSonucCall=apiInterface.esyaDepoEkle(HesapBilgileri.androidToken,
                        esyaSpinnerArrayList.get(spinnerEsya.getSelectedItemPosition()).getEsyaId(),
                        Integer.parseInt(txtMiktar.getText().toString())
                );
                esyaDepoEkleSonucCall.enqueue(new Callback<EsyaDepoEkleSonuc>() {
                    @Override
                    public void onResponse(Call<EsyaDepoEkleSonuc> call, Response<EsyaDepoEkleSonuc> response) {
                        EsyaDepoEkleSonuc esyaDepoEkleSonuc=response.body();
                        if(!dialogMesajlari.hataMesajiGoster(esyaDepoEkleSonuc.hataKodu))
                        {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager
                                    .beginTransaction()
                                    .replace(
                                            R.id.ContentFrame, new EsyaDepoFragment(), "EsyaDepoFragment"
                                    ).commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<EsyaDepoEkleSonuc> call, Throwable t) {
                        call.cancel();
                        dialogMesajlari.hataMesajiGoster();
                    }
                });
            }
        });
        return fragmentView;
    }

}
