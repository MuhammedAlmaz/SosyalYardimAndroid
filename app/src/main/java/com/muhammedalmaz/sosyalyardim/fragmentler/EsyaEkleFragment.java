package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.Esya;
import com.muhammedalmaz.sosyalyardim.pojo.EsyaEkleSonuc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsyaEkleFragment extends Fragment {


    private String TAG = EsyaEkleFragment.class.getName();
    private Esya esya;
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    EditText txtEsyaAdi;
    public EsyaEkleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView=  inflater.inflate(R.layout.fragment_esya_ekle, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        txtEsyaAdi=(EditText)fragmentView.findViewById(R.id.TxtEsyaAdi);
        dialogMesajlari = new DialogMesajlari(getActivity());

        ((BootstrapButton)fragmentView.findViewById(R.id.BtnEsyaEkle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<EsyaEkleSonuc> esyaEkleSonucCall=apiInterface.esyaEkle(HesapBilgileri.androidToken,
                        txtEsyaAdi.getText().toString()
                );
                esyaEkleSonucCall.enqueue(new Callback<EsyaEkleSonuc>() {
                    @Override
                    public void onResponse(Call<EsyaEkleSonuc> call, Response<EsyaEkleSonuc> response) {
                        EsyaEkleSonuc esyaEkleSonuc=response.body();
                        if(!dialogMesajlari.hataMesajiGoster(esyaEkleSonuc.hataKodu))
                        {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager
                                    .beginTransaction()
                                    .replace(
                                            R.id.ContentFrame, new EsyaFragment(), "EsyaFragment"
                                    ).commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<EsyaEkleSonuc> call, Throwable t) {
                        call.cancel();
                        dialogMesajlari.hataMesajiGoster();
                    }
                });
            }
        });
        return fragmentView;
    }

}
