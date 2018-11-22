package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.adaptorler.TabAdapter;

public class AnaSayfaFragment extends Fragment {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public AnaSayfaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_ana_sayfa, container, false);

        viewPager = (ViewPager) fragmentView.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) fragmentView.findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new TABFragment(), "TAB");
        adapter.addFragment(new TEBFragment(), "TEB");
        adapter.addFragment(new OBISFragment(), "OBİS");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        return fragmentView;
    }


}
