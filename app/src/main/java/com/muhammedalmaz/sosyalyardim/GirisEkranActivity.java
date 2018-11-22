package com.muhammedalmaz.sosyalyardim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;


import com.muhammedalmaz.sosyalyardim.api.APIClient;
import com.muhammedalmaz.sosyalyardim.api.APIInterface;
import com.muhammedalmaz.sosyalyardim.ekstralar.HesapBilgileri;
import com.muhammedalmaz.sosyalyardim.fonksiyonlar.DialogMesajlari;
import com.muhammedalmaz.sosyalyardim.pojo.GirisSonuc;
import com.google.gson.Gson;
import com.muhammedalmaz.sosyalyardim.pojo.KullaniciBilgileri;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GirisEkranActivity extends AppCompatActivity {
    int hataKodu=-1;
    public GirisEkranActivity(int hataKodu){
        this.hataKodu=hataKodu;
    }
    APIInterface apiInterface;
    DialogMesajlari dialogMesajlari;
    BootstrapButton btnGirisYap;
    EditText txtKullaniciAdi,txtSifre;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String TAG = GirisEkranActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String kayitliKullaniciBilgileri=preferences.getString("KullaniciBilgileri",null);
        if(kayitliKullaniciBilgileri!=null)
        {
            Gson gson=new Gson();
            KullaniciBilgileri kullaniciBilgileri=gson.fromJson(kayitliKullaniciBilgileri,KullaniciBilgileri.class);
            HesapBilgileri.androidToken=kullaniciBilgileri.androidToken;
            HesapBilgileri.kullaniciAdiSoyadi=kullaniciBilgileri.kullaniciAdi+" "+kullaniciBilgileri.kullaniciSoyadi;
            startActivity(new Intent(GirisEkranActivity.this,AnaEkranActivity.class));
            return;
        }
        setContentView(R.layout.activity_giris_ekran);
        viewOlustur();
        txtKullaniciAdi.setText("sa@aaa.com");
        txtSifre.setText("1234567");
        dialogMesajlari =new DialogMesajlari(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        btnGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullaniciAdi=txtKullaniciAdi.getText().toString();
                String sifre=txtSifre.getText().toString();
                if(kullaniciAdi.length()<6||!kullaniciAdi.matches(emailPattern))
                {
                    dialogMesajlari.hataMesajiGoster("Lütfen geçerli bir mail adresi giriniz...");
                }else if(sifre.length()<6){
                    dialogMesajlari.hataMesajiGoster("Şifreniz en az 6 karakter olmalıdır.");
                }else{

                    Call<GirisSonuc> call=apiInterface.girisYap(kullaniciAdi,sifre);
                    call.enqueue(new Callback<GirisSonuc>() {
                        @Override
                        public void onResponse(Call<GirisSonuc> call, Response<GirisSonuc> response) {
                            GirisSonuc sonuc=response.body();
                            if(!dialogMesajlari.hataMesajiGoster(sonuc.hataKodu))
                            {
                                Gson gson=new Gson();
                                String kullaniciBilgileriJSON=gson.toJson(sonuc.kullaniciBilgileri);
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                                editor.putString("KullaniciBilgileri", kullaniciBilgileriJSON);
                                editor.apply();
                                KullaniciBilgileri kullaniciBilgileri=gson.fromJson(kullaniciBilgileriJSON,KullaniciBilgileri.class);
                                HesapBilgileri.androidToken=kullaniciBilgileri.androidToken;
                                HesapBilgileri.kullaniciAdiSoyadi=kullaniciBilgileri.kullaniciAdi+" "+kullaniciBilgileri.kullaniciSoyadi;
                                startActivity(new Intent(GirisEkranActivity.this,AnaEkranActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<GirisSonuc> call, Throwable t) {
                            call.cancel();
                            dialogMesajlari.hataMesajiGoster();
                        }
                    });
                }
            }
        });
    }


    public void viewOlustur(){
        btnGirisYap=findViewById(R.id.BtnGirisYap);
        txtKullaniciAdi=findViewById(R.id.TxtKullaniciAdi);
        txtSifre=findViewById(R.id.TxtSifre);
    }
}
