package com.example.yayinevi_proje;

import java.util.ArrayList;

public class Yayinevi implements EnMetodlari {
    private String ad;
    private int kitapSayisi;
    public ArrayList<Yazar> yazarArrayList = new ArrayList<>();
    public ArrayList<Kitap> ye_kitapArrayList = new ArrayList<>();
    private String yayineviOrt;
    private double toplam=0;
    private double yorumSayi=0;


    public ArrayList<Kitap> getYe_kitapArrayList() {
        return ye_kitapArrayList;
    }

    public String getAd() {
        return ad;
    }

    public Yayinevi(String ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        kitapSayisi=ye_kitapArrayList.size();
        return ("Yayınevi adı:" + ad + "->Kitap sayısı:" + kitapSayisi+"->Beğenilme: %"+ toplam);
    }
    @Override
    public String OrtYildizSayisi(ArrayList<Integer> liste) {
        //double toplam=0;
        double ttoplam=0;
        double yyorumSayi=0;
        for (int i=0;i<5;i++) {
            yyorumSayi+= liste.get(i);
            ttoplam+=liste.get(i)*(i+1);
        }
        double ortalama=(ttoplam*100)/(5*yyorumSayi);
        toplam+=ortalama;
        yorumSayi+=yyorumSayi;
        System.out.println("buraya girildi "+toplam+" "+ortalama+"*"+liste);
        return ("Yayınevinin beğenilme yüzdesi: "+toplam/100);
    }

    public String genelYuzdeBastırma() {
        //String sonuc=String.format("%.1f", (toplam*100)/(5*yorumSayi));
        String sonuc=String.format("%.1f", toplam/(ye_kitapArrayList.size()));

        System.out.println(toplam+"****"+yorumSayi+"*****"+(toplam/yorumSayi));
        //return String.format("%.1f", (toplam * 100) / (5 * yorumSayi));
        //return String.format("%.1f", toplam  /  yorumSayi);
        return ("Beğenilme Yüzdesi: %"+sonuc);
    }
}

