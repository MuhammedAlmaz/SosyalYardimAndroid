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
import com.muhammedalmaz.sosyalyardim.pojo.Kullanici;
import com.muhammedalmaz.sosyalyardim.pojo.Kullanici;

import java.util.ArrayList;

/**
 * Created by muham on 18.11.2018.
 */

public class RecyclerViewKullaniciListeAdapter extends RecyclerView.Adapter<RecyclerViewKullaniciListeAdapter.MyViewHolder> {

    private ArrayList<Kullanici> kullaniciList;
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


    public RecyclerViewKullaniciListeAdapter(ArrayList<Kullanici> kullaniciList, Context context) {
        this.kullaniciList = kullaniciList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recyclerview_kullanici_liste, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        Kullanici Kullanici = kullaniciList.get(position);
        holder.lblAdSoyad .setText(Kullanici.getKullaniciAdi()+" "+Kullanici.getKullaniciSoyadi());
        holder.lblTel .setText(Kullanici.getTel());
        holder.lblSehir .setText(Kullanici.getSehir());
        holder.lblAdres .setText(Kullanici.getAdres());
    }

    public void listeyiGuncelle() {
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        kullaniciList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return kullaniciList.size();
    }

    public Kullanici getItem(int position) {
        return kullaniciList.get(position);
    }
}