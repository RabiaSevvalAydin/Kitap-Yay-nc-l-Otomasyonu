package com.example.yayinevi_proje;

import java.util.ArrayList;
public abstract class Kitap implements EnMetodlari{//sadece kitap oluşturmak istemiyoruz alt sınıflardan kitap oluşturulsun
    private String amac;//Eğitim-Okuma buna göre subclassa atanacak
    private String kategori;
    private String isim;
    private String sayfaSayisi;
    private String konu;
    private String dil;
    private String fiyat;
    private String stokSayisi;
    private String basimYili;
    private String yazar;
    private String yayinevi;
    private String ortYildiz;//beğenilme yüzdesi+yorum sayısını tutar
    private ArrayList<Integer> yorumArrayList = new ArrayList<>();
    private ArrayList<Okuma> okumaArrayList=new ArrayList<>();

    private ArrayList<Egitim> egitimArrayList=new ArrayList<>();
    private double intOrtYildizSayisi;//sadece beğenilme yüzdesini tutar

    public void okumaEkle(Okuma okuma){
        okumaArrayList.add(okuma);
    }
    public void egitimEkle(Egitim egitim){
        egitimArrayList.add(egitim);
    }

    public ArrayList<Egitim> getEgitimArrayList() {
        return egitimArrayList;
    }
    public ArrayList<Okuma> getOkumaArrayList() {
        return okumaArrayList;
    }

    public void setOrtYildiz(String ortYildiz) {
        this.ortYildiz = ortYildiz;
    }

    public ArrayList<Integer> getYorumArrayList() {
        return yorumArrayList;
    }

    //    public void setYorumArrayList(ArrayList<Integer> yorumArrayList) {
//        this.yorumArrayList = yorumArrayList;
//    }
    public String getAmac() {
        return amac;
    }
    public String getIsim() {
        return isim;
    }
    public String getYayinevi() {
        return yayinevi;
    }
    public String getYazar() {
        return yazar;
    }

    public String getKategori() {
        return kategori;
    }

    public String getOrtYildiz() {
        return ortYildiz;
    }
    public String getsayfaSayisi() {
        return sayfaSayisi;
    }

    public String getKonu() {
        return konu;
    }

    public String getDil() {
        return dil;
    }

    public String getFiyat() {
        return fiyat;
    }

    public String getStokSayisi() {
        return stokSayisi;
    }

    public String getBasimYili() {
        return basimYili;
    }

    public double getIntOrtYildizSayisi() {
        return intOrtYildizSayisi;
    }

    public void setEgitimArrayList(Egitim egitim) {
        getEgitimArrayList().add(egitim);
    }
    public Kitap(String amac,String kategori, String yayinevi, String yazar , String isim, String sayfaSayisi, String konu, String dil, String fiyat, String stokSayisi, String basimYili, String yıldızlar) {

        this.amac=amac;
        this.kategori=kategori;
        this.isim = isim;
        this.sayfaSayisi = sayfaSayisi;
        this.konu = konu;
        this.dil = dil;
        this.fiyat = fiyat;
        this.stokSayisi = stokSayisi;
        this.basimYili = basimYili;
        this.yazar = yazar;
        this.yayinevi = yayinevi;
        String[] yildizlar=yıldızlar.split("\\*");
        for (int i=0;i<5;i++) {
            int a=Integer.parseInt(yildizlar[i]);
            yorumArrayList.add(a);
        }
        ortYildiz=OrtYildizSayisi(yorumArrayList);
    }
    public void setOkumaArrayList(Okuma okuma) {
        getOkumaArrayList().add(okuma);
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    @Override
    public String toString() {
        return ("\nKitap adı:"+isim+" ,Yazarı:"+yazar+" ,Yayınevi:"+yayinevi+",Türü:"+amac+",Sayfa:"+sayfaSayisi+",Ortalama yıldız:"+ortYildiz);
    }

    @Override
    public String OrtYildizSayisi(ArrayList<Integer> liste) {
        double toplam=0;
        double yorumSayi=0;
        for (int i=0;i<5;i++) {
            yorumSayi+= liste.get(i);
            toplam+=liste.get(i)*(i+1);
        }
        intOrtYildizSayisi=(toplam*100)/(5*yorumSayi);
        String sonuc=String.format("%.1f", (toplam*100)/(5*yorumSayi));
        return ("%"+sonuc+" ("+(int)yorumSayi+" kişi oy verdi)");
    }
    //    public String EnCokSatanKitap(){
//        return "deneme";
//    }
//    public String EnSonCikanKitap(){
//        return "deneme2";
//    }
//    public String EnIyiYorum(){
//        return "YORUM DENEME";
//    }

}
