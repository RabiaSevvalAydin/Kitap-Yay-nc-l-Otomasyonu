package com.example.yayinevi_proje;

import java.util.ArrayList;

public class Yazar implements EnMetodlari {
    private String adSoyad;
    private int kitapSayisi;
    public ArrayList<Kitap> kitapArrayList = new ArrayList<>();
    private String yayineviOrt;


    public String getAdSoyad() {
        return adSoyad;
    }

    public Yazar(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    @Override
    public String toString() {
        kitapSayisi = kitapArrayList.size();
        return ("Yazar adı:" + adSoyad + "->Kitap sayısı:" + kitapSayisi);
    }

    @Override
    public String OrtYildizSayisi(ArrayList<Integer> liste) {
        double toplam=0;
        for(int i=0;i<kitapArrayList.size();i++){
            toplam+=kitapArrayList.get(i).getIntOrtYildizSayisi();
        }
        return ("Yayınevinin beğenilme yüzdesi: "+toplam/100);    }







/*@Override
    public double OrtYildizSayisi() {
        return 0;
    }

    public String EnCokSatanKitap() {
        return "deneme";
    }

    public String EnSonCikanKitap() {
        return "deneme2";
    }*/
}

