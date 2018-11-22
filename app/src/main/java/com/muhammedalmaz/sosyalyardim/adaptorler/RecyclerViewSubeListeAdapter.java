package com.muhammedalmaz.sosyalyardim.adaptorler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.pojo.Sube;

import java.util.ArrayList;

/**
 * Created by muham on 18.11.2018.
 */

public class RecyclerViewSubeListeAdapter extends RecyclerView.Adapter<RecyclerViewSubeListeAdapter.MyViewHolder> {

    private ArrayList<Sube> subeList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblSubeIl, lblSubeGorevlisi;
        public MyViewHolder(View view) {
            super(view);
            lblSubeIl = (TextView) view.findViewById(R.id.LblSubeIl);
            lblSubeGorevlisi = (TextView) view.findViewById(R.id.LblSubeGorevlisi);
        }
    }


    public RecyclerViewSubeListeAdapter(ArrayList<Sube> subeList, Context context) {
        this.subeList = subeList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recyclerview_sube_liste, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        Sube sube = subeList.get(position);
        holder.lblSubeGorevlisi.setText(sube.getSubeSorumlusu());
        holder.lblSubeIl.setText(sube.getSubeIl());
    }

    public void removeItem(int position) {
        subeList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return subeList.size();
    }
    public Sube getItem(int position){
        return subeList.get(position);
    }
}