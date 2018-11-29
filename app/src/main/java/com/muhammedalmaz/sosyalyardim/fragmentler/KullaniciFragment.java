package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.adaptorler.RecyclerViewKullaniciListeAdapter;
import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.Kullanici;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciListeSonuc;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciSilmeSonuc;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KullaniciFragment extends Fragment {

    String TAG=KullaniciFragment.class.getName();
    APIInterface apiInterface;
    RecyclerViewKullaniciListeAdapter recyclerViewKullaniciListeAdapter;
    RecyclerView recyclerKullaniciListe;
    DialogMesajlari dialogMesajlari;

    public KullaniciFragment() {
        // Required empty public constructor
    }

    public void kullanicileriYukle() {
        Call<KullaniciListeSonuc> kullaniciListeSonucCall = apiInterface.kullaniciListe(HesapBilgileri.androidToken);
        kullaniciListeSonucCall.enqueue(new Callback<KullaniciListeSonuc>() {
            @Override
            public void onResponse(Call<KullaniciListeSonuc> call, Response<KullaniciListeSonuc> response) {
                KullaniciListeSonuc kullaniciListeSonuc = response.body();
                if (!dialogMesajlari.hataMesajiGoster(kullaniciListeSonuc.hataKodu)) {
                    recyclerViewKullaniciListeAdapter = new RecyclerViewKullaniciListeAdapter(kullaniciListeSonuc.kullaniciListe, getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerKullaniciListe.setLayoutManager(mLayoutManager);
                    recyclerKullaniciListe.setItemAnimator(new DefaultItemAnimator());
                    recyclerKullaniciListe.setAdapter(recyclerViewKullaniciListeAdapter);
                }

            }

            @Override
            public void onFailure(Call<KullaniciListeSonuc> call, Throwable t) {
                Log.e(TAG,"Hata Oldu");
                Log.e(TAG,t.getMessage());
                call.cancel();
                dialogMesajlari.hataMesajiGoster();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_kullanici, container, false);



        recyclerKullaniciListe = fragmentView.findViewById(R.id.RecyclerKullaniciListe);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        dialogMesajlari = new DialogMesajlari(getActivity());
        kullanicileriYukle();

        ItemTouchHelper.SimpleCallback simpleCallbackSil = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Kullanici kullanici = recyclerViewKullaniciListeAdapter.getItem(position);
                if (direction == ItemTouchHelper.LEFT) {
                    recyclerViewKullaniciListeAdapter.listeyiGuncelle();
                    dialogMesajlari.evetHayirDialogGoster("Şubeyi Silmek Istediğinizden Emin Misiniz", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            Call<KullaniciSilmeSonuc> kullaniciSilmeSonucCall = apiInterface.kullaniciSil(HesapBilgileri.androidToken
                                    , kullanici.getKullaniciID());
                            kullaniciSilmeSonucCall.enqueue(new Callback<KullaniciSilmeSonuc>() {
                                @Override
                                public void onResponse(Call<KullaniciSilmeSonuc> call, Response<KullaniciSilmeSonuc> response) {
                                    KullaniciSilmeSonuc kullaniciListeSonuc = response.body();
                                    if (!dialogMesajlari.hataMesajiGoster(kullaniciListeSonuc.hataKodu)) {
                                        kullanicileriYukle();
                                        dialogMesajlari.basariliIslemDialogGoster();
                                    }
                                }

                                @Override
                                public void onFailure(Call<KullaniciSilmeSonuc> call, Throwable t) {
                                    call.cancel();
                                    dialogMesajlari.hataMesajiGoster();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                try {
                    Bitmap icon;
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        View itemView = viewHolder.itemView;
                        float height = (float) itemView.getBottom() - (float) itemView.getTop();
                        float width = height / 5;
                        viewHolder.itemView.setTranslationX(dX);
                        Paint paint = new Paint();
                        paint.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, paint);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_sil);
                        RectF icon_dest = new RectF((float) (itemView.getRight() + dX / 5), (float) itemView.getTop() + width, (float) itemView.getRight() + dX / 20, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, paint);
                    } else {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };

        ItemTouchHelper.SimpleCallback simpleCallbackDuzenle = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT) {


                   /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .replace(
                                    R.id.ContentFrame, new KullaniciDuzenleFragment(
                                            recyclerViewKullaniciListeAdapter.getItem(position))
                                    , "KullaniciDuzenleFragment"
                            ).commit();*/
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                try {
                    Bitmap icon;
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        View itemView = viewHolder.itemView;
                        float height = (float) itemView.getBottom() - (float) itemView.getTop();
                        float width = height / 5;
                        viewHolder.itemView.setTranslationX(dX);
                        Paint paint = new Paint();
                        paint.setColor(Color.parseColor("#224AF3"));
                        RectF background = new RectF((float) itemView.getLeft() + dX, (float) itemView.getTop(), (float) itemView.getLeft(), (float) itemView.getBottom());
                        c.drawRect(background, paint);
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_duzenle);
                        RectF icon_dest = new RectF((float) (itemView.getLeft() + dX / 5), (float) itemView.getTop() + width, (float) itemView.getLeft() + dX / 20, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, paint);
                    } else {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };

        ItemTouchHelper itemTouchHelperSil = new ItemTouchHelper(simpleCallbackSil);
        ItemTouchHelper itemTouchHelperDuzenle = new ItemTouchHelper(simpleCallbackDuzenle);
        itemTouchHelperSil.attachToRecyclerView(recyclerKullaniciListe);
        itemTouchHelperDuzenle.attachToRecyclerView(recyclerKullaniciListe);

        ((BootstrapButton)fragmentView.findViewById(R.id.BtnKullaniciEkle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(
                                R.id.ContentFrame, new KullaniciEkleFragment(), "KullaniciEkleFragment"
                        ).commit();*/
            }
        });
        
        
        return fragmentView;
    }

}
