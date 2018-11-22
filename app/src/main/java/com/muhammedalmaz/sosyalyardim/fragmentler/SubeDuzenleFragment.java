package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.pojo.Sube;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubeDuzenleFragment extends Fragment {


    private String TAG=SubeDuzenleFragment.class.getName();
    private Sube sube;
    ArrayList<HashMap<Integer, String>> sehirArray=new ArrayList<HashMap<Integer, String> >();

    @SuppressLint("UseSparseArrays")
    HashMap<Integer, String> sehir=new HashMap<Integer, String>();
    public SubeDuzenleFragment() {
        // Required empty public constructor
        this.sube=sube;
    }

    @SuppressLint("ValidFragment")
    public SubeDuzenleFragment(Sube sube) {
        // Required empty public constructor
        this.sube=sube;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_sube_duzenle, container, false);


        return fragmentView;
    }

}
