package com.muhammedalmaz.sosyalyardim.adaptorler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.pojo.Esya;

import java.util.ArrayList;

public class RecyclerViewEsyaListeAdapter extends RecyclerView.Adapter<RecyclerViewEsyaListeAdapter.MyViewHolder> {

    private ArrayList<Esya> esyaList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblEsyaAdi;
        public MyViewHolder(View view) {
            super(view);
            lblEsyaAdi = (TextView) view.findViewById(R.id.LblEsyaAdi);
        }
    }


    public RecyclerViewEsyaListeAdapter(ArrayList<Esya> esyaList, Context context) {
        this.esyaList = esyaList;
        this.context = context;
    }

    @Override
    public RecyclerViewEsyaListeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recyclerview_esya_liste, parent, false);

        return new RecyclerViewEsyaListeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewEsyaListeAdapter.MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        Esya esya = esyaList.get(position);
        holder.lblEsyaAdi.setText(esya.getEsyaAdi());
    }

    public void listeyiGuncelle() {
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        esyaList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return esyaList.size();
    }
    public Esya getItem(int position){
        return esyaList.get(position);
    }
}
