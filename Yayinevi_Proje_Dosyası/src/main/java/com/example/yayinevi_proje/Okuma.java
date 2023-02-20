package com.example.yayinevi_proje;

public class Okuma extends Kitap {



    public Okuma(String amac, String kategori, String yayinevi, String yazar, String isim, String sayfaSayisi, String konu, String dil, String fiyat, String stokSayisi, String basimYili, String yıldızlar) {
        super(amac,kategori, yayinevi ,yazar, isim, sayfaSayisi, konu, dil, fiyat, stokSayisi, basimYili,yıldızlar);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
