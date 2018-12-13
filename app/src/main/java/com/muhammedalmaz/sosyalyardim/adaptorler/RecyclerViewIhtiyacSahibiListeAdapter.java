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
import com.muhammedalmaz.sosyalyardim.pojo.IhtiyacSahibi;

import java.util.ArrayList;


public class RecyclerViewIhtiyacSahibiListeAdapter extends RecyclerView.Adapter<RecyclerViewIhtiyacSahibiListeAdapter.MyViewHolder> {

    private ArrayList<IhtiyacSahibi> ihtiyacSahibiList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView
                lblAdSoyad,
                lblTel,
                lblSehir,
                lblAdres;

        public MyViewHolder(View view) {
            super(view);
            lblAdSoyad = (TextView) view.findViewById(R.id.LblAdSoyad);
            lblTel = (TextView) view.findViewById(R.id.LblTel);
            lblSehir = (TextView) view.findViewById(R.id.LblSehir);
            lblAdres = (TextView) view.findViewById(R.id.LblAdres);
        }
    }


    public RecyclerViewIhtiyacSahibiListeAdapter(ArrayList<IhtiyacSahibi> ihtiyacSahibiList, Context context) {
        this.ihtiyacSahibiList = ihtiyacSahibiList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recyclerview_ihtiyacsahibi_liste, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        IhtiyacSahibi IhtiyacSahibi = ihtiyacSahibiList.get(position);
        holder.lblAdSoyad .setText(IhtiyacSahibi.getAdi()+" "+IhtiyacSahibi.getSoyadi());
        holder.lblTel .setText(IhtiyacSahibi.getTelNo());
        holder.lblSehir .setText(IhtiyacSahibi.getSehirAd());
        holder.lblAdres .setText(IhtiyacSahibi.getAdres());
    }

    public void listeyiGuncelle() {
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        ihtiyacSahibiList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return ihtiyacSahibiList.size();
    }

    public IhtiyacSahibi getItem(int position) {
        return ihtiyacSahibiList.get(position);
    }
}