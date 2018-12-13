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
import com.muhammedalmaz.sosyalyardim.pojo.EsyaDepo;

import java.util.ArrayList;

public class RecyclerViewEsyaDepoListeAdapter extends RecyclerView.Adapter<RecyclerViewEsyaDepoListeAdapter.MyViewHolder> {

    private ArrayList<EsyaDepo> esyaEsyaDepoList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblEsyaAdi;
        public TextView lblMiktar;
        public MyViewHolder(View view) {
            super(view);
            lblEsyaAdi= (TextView) view.findViewById(R.id.LblEsyaAdi);
            lblMiktar= (TextView) view.findViewById(R.id.LblMiktar);
        }
    }


    public RecyclerViewEsyaDepoListeAdapter(ArrayList<EsyaDepo> esyaEsyaDepoList, Context context) {
        this.esyaEsyaDepoList = esyaEsyaDepoList;
        this.context = context;
    }

    @Override
    public RecyclerViewEsyaDepoListeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recyclerview_esya_depo_liste, parent, false);

        return new RecyclerViewEsyaDepoListeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewEsyaDepoListeAdapter.MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        EsyaDepo esyaEsyaDepo = esyaEsyaDepoList.get(position);
        holder.lblEsyaAdi.setText(esyaEsyaDepo.getEsyaAdi());
        holder.lblMiktar.setText(esyaEsyaDepo.getMiktar()+"");
    }

    public void listeyiGuncelle() {
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        esyaEsyaDepoList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return esyaEsyaDepoList.size();
    }
    public EsyaDepo getItem(int position){
        return esyaEsyaDepoList.get(position);
    }
}
