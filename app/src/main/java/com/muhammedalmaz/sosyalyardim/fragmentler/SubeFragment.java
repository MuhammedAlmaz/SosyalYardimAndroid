package com.muhammedalmaz.sosyalyardim.fragmentler;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammedalmaz.sosyalyardim.R;
import com.muhammedalmaz.sosyalyardim.adaptorler.RecyclerViewSubeListeAdapter;
import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.Sube;
import com.muhammedalmaz.sosyalyardim.pojo.SubeListeSonuc;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubeFragment extends Fragment {
    APIInterface apiInterface;
    RecyclerViewSubeListeAdapter recyclerViewSubeListeAdapter;
    RecyclerView recyclerSubeListe;
    DialogMesajlari dialogMesajlari;

    public SubeFragment() {}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_sube, container, false);
        recyclerSubeListe=fragmentView.findViewById(R.id.RecyclerSubeListe);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        dialogMesajlari=new DialogMesajlari(getActivity());
        Call<SubeListeSonuc> subeListeSonucCall=apiInterface.subeListe(HesapBilgileri.androidToken);
        subeListeSonucCall.enqueue(new Callback<SubeListeSonuc>() {
            @Override
            public void onResponse(Call<SubeListeSonuc> call, Response<SubeListeSonuc> response) {
                SubeListeSonuc subeListeSonuc=response.body();
                if(!dialogMesajlari.hataMesajiGoster(subeListeSonuc.hataKodu))
                {
                    recyclerViewSubeListeAdapter= new RecyclerViewSubeListeAdapter(subeListeSonuc.subeListe,getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerSubeListe.setLayoutManager(mLayoutManager);
                    recyclerSubeListe.setItemAnimator(new DefaultItemAnimator());
                    recyclerSubeListe.setAdapter(recyclerViewSubeListeAdapter);
                }

            }

            @Override
            public void onFailure(Call<SubeListeSonuc> call, Throwable t) {
                call.cancel();
                dialogMesajlari.hataMesajiGoster();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallbackSil = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Sube sube=recyclerViewSubeListeAdapter.getItem(position);
                if (direction == ItemTouchHelper.LEFT) {
                    dialogMesajlari.evetHayirDialogGoster("Şubeyi Silmek Istediğinizden Emin Misiniz", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
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
                        RectF icon_dest = new RectF((float) (itemView.getRight() + dX /5), (float) itemView.getTop()+width, (float) itemView.getRight()+dX/20, (float) itemView.getBottom()-width);
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


                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .replace(
                                    R.id.ContentFrame, new SubeDuzenleFragment(
                                    recyclerViewSubeListeAdapter.getItem(position))
                                    , "SubeDuzenleFragment"
                            ).commit();
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
                        RectF icon_dest = new RectF((float) (itemView.getLeft() + dX /5), (float) itemView.getTop()+width, (float) itemView.getLeft()+dX/20, (float) itemView.getBottom()-width);
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
        itemTouchHelperSil.attachToRecyclerView(recyclerSubeListe);
        itemTouchHelperDuzenle.attachToRecyclerView(recyclerSubeListe);

        return fragmentView;
    }

}
