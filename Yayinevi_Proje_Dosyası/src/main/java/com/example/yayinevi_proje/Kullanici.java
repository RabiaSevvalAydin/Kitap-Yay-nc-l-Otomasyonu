package com.example.yayinevi_proje;
import java.util.ArrayList;
public class Kullanici {
    private String kullaniciAdi;
    private String sifre;
    public ArrayList<Yorum> Yorumlar=new ArrayList<>();
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }
    public String getSifre() {
        return sifre;
    }

    public ArrayList<Yorum> getYorumlar() {
        return Yorumlar;
    }

    public Kullanici(String kullaniciAdi,String sifre){
        this.kullaniciAdi=kullaniciAdi;
        this.sifre=sifre;
    }
    public Kullanici(String kullaniciAdi, String sifre, String veriYorumlar) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        String[] yorumListe=veriYorumlar.split("\\+");
        for (int i=0;i<yorumListe.length;i++) {
            String[] aa=yorumListe[i].split("\\*");
            ArrayList<Kitap> arrayList=DefaultController.getTumKitaplarArrayList();
            for (int y=0; y<arrayList.size();y++) {
                System.out.println(aa[0]);
                if (aa[0].equals(arrayList.get(y).getIsim())) {
                    System.out.println(aa[0]);
                    System.out.println("kitap eşleşti");
                    int sayi=Integer.parseInt(aa[1]);
                    Yorum yorum=new Yorum(arrayList.get(y),sayi);
                    this.Yorumlar.add(yorum);    //kullanıcının yorum listesine eklendi
                    DefaultController.yorumEkle(y,sayi);
                    break;
                }
            }
        }
    }
    @Override
    public String toString() {
        return ("\nKullanıcı:" + kullaniciAdi + " Şifre:" + sifre+" Yorumlar: "+Yorumlar);
    }
}
