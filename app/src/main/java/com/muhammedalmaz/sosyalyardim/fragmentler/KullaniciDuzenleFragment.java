package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammedalmaz.sosyalyardim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KullaniciDuzenleFragment extends Fragment {


    public KullaniciDuzenleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kullanici_duzenle, container, false);
    }

}
