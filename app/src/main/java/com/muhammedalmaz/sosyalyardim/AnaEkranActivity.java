package com.muhammedalmaz.sosyalyardim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.GirisSonuc;
import com.google.gson.Gson;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciBilgileri;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnaEkranActivity extends AppCompatActivity {
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    String TAG = AnaEkranActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);
        dialogMesajlari =new DialogMesajlari(getApplicationContext());
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<GirisSonuc> call=apiInterface.girisYap("sa","123");
        call.enqueue(new Callback<GirisSonuc>() {
            @Override
            public void onResponse(Call<GirisSonuc> call, Response<GirisSonuc> response) {
                GirisSonuc sonuc=response.body();
                if(!dialogMesajlari.hataMesajiGoster(sonuc.hataKodu))
                {

                    Gson gson=new Gson();
                    String kullaniciBilgileriJSON=gson.toJson(sonuc.kullaniciBilgileri);
                    Log.d(TAG,kullaniciBilgileriJSON);
                    KullaniciBilgileri kullaniciBilgileri=gson.fromJson(kullaniciBilgileriJSON,KullaniciBilgileri.class);
                    Log.d(TAG,kullaniciBilgileri.kullaniciSoyadi);
                }
            }

            @Override
            public void onFailure(Call<GirisSonuc> call, Throwable t) {
                Log.d(TAG,"Hata Oluştu");
                call.cancel();
            }
        });
    }
}
