package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepoGuncelleSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaSpinnerSonuc;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsyaDepoDuzenleFragment extends Fragment {


    private String TAG = EsyaDepoDuzenleFragment.class.getName();
    private EsyaDepo esyaDepo;
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    EditText txtMiktar;
    Spinner spinnerEsya;
    ArrayList<String> esyaListe = new ArrayList<String>();
    ArrayList<Esya> esyaSpinnerArrayList;
    public EsyaDepoDuzenleFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public EsyaDepoDuzenleFragment(EsyaDepo esyaDepo) {
        this.esyaDepo=esyaDepo;
    }

    int seciliEsyaPozisyon=0;
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
                    if(esya.getEsyaId()==esyaDepo.getEsyaId())
                    {
                        seciliEsyaPozisyon=i;
                    }
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
        View fragmentView=  inflater.inflate(R.layout.fragment_esya_depo_duzenle, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        txtMiktar=(EditText)fragmentView.findViewById(R.id.TxtMiktar);
        spinnerEsya=(Spinner) fragmentView.findViewById(R.id.SpinnerEsya);
        dialogMesajlari = new DialogMesajlari(getActivity());

        spinnerDoldur();

        ArrayAdapter<String> spinnerArrayAdapterSehir = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, esyaListe);
        spinnerArrayAdapterSehir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEsya.setAdapter(spinnerArrayAdapterSehir);

        txtMiktar.setText(esyaDepo.getMiktar()+"");
        spinnerEsya.setSelection(seciliEsyaPozisyon);


        ((BootstrapButton)fragmentView.findViewById(R.id.BtnEsyaDepoDuzenle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<EsyaDepoGuncelleSonuc> esyaDepoDuzenleSonucCall=apiInterface.esyaDepoGuncelle(HesapBilgileri.androidToken,
                        esyaDepo.getEsyaDepoId(),
                        Integer.parseInt(txtMiktar.getText().toString()),
                        esyaSpinnerArrayList.get(spinnerEsya.getSelectedItemPosition()).getEsyaId()
                        );
                esyaDepoDuzenleSonucCall.enqueue(new Callback<EsyaDepoGuncelleSonuc>() {
                    @Override
                    public void onResponse(Call<EsyaDepoGuncelleSonuc> call, Response<EsyaDepoGuncelleSonuc> response) {
                        EsyaDepoGuncelleSonuc esyaDepoDuzenleSonuc=response.body();
                        if(!dialogMesajlari.hataMesajiGoster(esyaDepoDuzenleSonuc.hataKodu))
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
                    public void onFailure(Call<EsyaDepoGuncelleSonuc> call, Throwable t) {
                        call.cancel();
                        dialogMesajlari.hataMesajiGoster();
                    }
                });
            }
        });
        return fragmentView;
    }

}
