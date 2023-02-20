package com.example.yayinevi_proje;

public class Egitim extends Kitap {

    public Egitim(String amac, String egitimTuru, String yayinevi, String yazar, String isim, String sayfaSayisi, String konu, String dil, String fiyat, String stokSayisi, String basimYili, String yıldızlar) {
        super(amac, egitimTuru, yayinevi, yazar ,isim, sayfaSayisi, konu, dil, fiyat, stokSayisi, basimYili, yıldızlar);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
