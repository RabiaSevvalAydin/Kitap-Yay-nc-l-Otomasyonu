package com.example.yayinevi_proje;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static public ArrayList<Yazar> tumYazarlarArrayList = new ArrayList<>();
    static public ArrayList<Yayinevi> tumYayinEvleriArrayList = new ArrayList<>();
    static public ArrayList<Kitap> tumKitaplarArrayList = new ArrayList<>();
    static public ArrayList<Kullanici> tumKullanicilarArrayList=new ArrayList<>();
    public static ArrayList<Kitap> getTumKitaplarArrayList() {
        return tumKitaplarArrayList;
    }
    public static ArrayList<Yayinevi> getTumYayinEvleriArrayList(){
        return tumYayinEvleriArrayList;
    }
    public static ArrayList<Yazar> getTumYazarlarArrayList() {
        return tumYazarlarArrayList;
    }
    public static ArrayList<Kullanici> getTumKullanicilarArrayList() {
        return tumKullanicilarArrayList;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Main main=new Main();
        yazarOku("yazarListe.txt", tumYazarlarArrayList);
        yayineviOku("yayıneviListe.txt", tumYayinEvleriArrayList);
        kitapOku("kitapListe.txt", tumKitaplarArrayList, tumYayinEvleriArrayList, tumYazarlarArrayList);
        System.out.println("Yazarlar="+tumYazarlarArrayList);
        System.out.println("Yayinevleri="+tumYayinEvleriArrayList);
        System.out.println("Kitaplar="+tumKitaplarArrayList);
        System.out.println("Kullanıcılar="+tumKullanicilarArrayList);
//        Scanner input=new Scanner(System.in);
//        System.out.println("Yıldızını öğrenmek istediğiniz kitap adınız giriniz:");
//        String isim=input.nextLine();
        String isim="Kayıp Tanrılar Ülkesi";
        for(int i =0; i<tumKitaplarArrayList.size();i++){
            Kitap kitap=tumKitaplarArrayList.get(i);
            if (kitap.getIsim().equals(isim)) {
                System.out.println(kitap.getYorumArrayList());
                System.out.println(kitap.getOrtYildiz());
            }
        }
        kullaniciOku("kullanıcıListe.txt",tumKullanicilarArrayList);
        for(int i =0; i<tumKitaplarArrayList.size();i++){
            Kitap kitap=tumKitaplarArrayList.get(i);
            if (kitap.getIsim().equals(isim)) {
                System.out.println(kitap.getYorumArrayList());
                System.out.println(kitap.getOrtYildiz());
            }
        }
    }

    public static void yorumEkle(int index,int yorumDegeri) {
        int x=tumKitaplarArrayList.get(index).getYorumArrayList().get(yorumDegeri-1);   //eski yorum değeri alındı
        tumKitaplarArrayList.get(index).getYorumArrayList().set(yorumDegeri-1,x+1);     //yeni yorum eklendi
    }
    public static void yazarOku(String dosyaAdı, ArrayList<Yazar> liste) throws FileNotFoundException {
        File dosya = new File(dosyaAdı);
        Scanner myReader = new Scanner(dosya);
        String line;
        while (myReader.hasNextLine()) {
            line = myReader.nextLine();
            String[] veriler = line.split("-");
            Yazar yazar = new Yazar(veriler[0]);
            liste.add(yazar);
        }
    }
    public static void yayineviOku(String dosyaAdı, ArrayList<Yayinevi> liste) throws FileNotFoundException {
        File dosya = new File(dosyaAdı);
        Scanner myReader = new Scanner(dosya);
        String line;
        while (myReader.hasNextLine()) {
            line = myReader.nextLine();
            String[] veriler = line.split("-");
            Yayinevi yayinevi = new Yayinevi(veriler[0]);
            liste.add(yayinevi);
        }
    }
    public static void kitapOku(String dosyaAdı, ArrayList<Kitap> listeKitap, ArrayList<Yayinevi> listeYayinevi, ArrayList<Yazar> listeYazar) throws FileNotFoundException {
        File dosya = new File(dosyaAdı);
        Scanner myReader = new Scanner(dosya);
        String line;
        while (myReader.hasNextLine()) {
            line = myReader.nextLine();
            String[] veriler = line.split(";");
            if (veriler[0].equals("Okuma")) {
                Okuma okuma = new Okuma(veriler[0], veriler[1], veriler[2], veriler[3], veriler[4], veriler[5], veriler[6], veriler[7], veriler[8], veriler[9],veriler[10],veriler[11]);
                int i = -1;
                Yayinevi yayinevi;
                Yazar yazar;
                do {    //kitap hangi yayınevine ait ise onun arraylistine eklenir
                    i += 1;
                    yayinevi = listeYayinevi.get(i);
                    if (okuma.getYayinevi().equals(yayinevi.getAd())) {
                        yayinevi.ye_kitapArrayList.add(okuma);
                        break;
                    }
                } while (listeYayinevi.get(i) != null);
                i = -1;
                do {    //kitap hangi yazara ait ise onun arraylistine eklenir
                    i += 1;
                    yazar = listeYazar.get(i);
                    if (okuma.getYazar().equals(yazar.getAdSoyad())) {
                        yazar.kitapArrayList.add(okuma);
                        break;
                    }
                }while(listeYazar.get(i)!=null);
                listeKitap.add(okuma);

            }
            else {   //egitim kitabı
                Egitim egitim = new Egitim(veriler[0], veriler[1], veriler[2], veriler[3], veriler[4], veriler[5], veriler[6], veriler[7], veriler[8], veriler[9], veriler[10],veriler[11]);
                int i = -1;
                Yayinevi yayinevi;
                Yazar yazar;
                do {    //kitap hangi yayınevine ait ise onun arraylistine eklenir
                    i += 1;
                    yayinevi = listeYayinevi.get(i);
                    if (egitim.getYayinevi().equals(yayinevi.getAd())) {
                        yayinevi.ye_kitapArrayList.add(egitim);
                        break;
                    }
                } while (listeYayinevi.get(i) != null);
                i = -1;
                do {    //kitap hangi yazara ait ise onun arraylistine eklenir
                    i += 1;
                    yazar = listeYazar.get(i);
                    if (egitim.getYazar().equals(yazar.getAdSoyad())) {
                        yazar.kitapArrayList.add(egitim);
                        break;
                    }
                }while(listeYazar.get(i)!=null);
                listeKitap.add(egitim);
            }
        }
    }
    public static void kullaniciOku(String dosyaAdı, ArrayList<Kullanici> listekullanici) throws FileNotFoundException {
        File dosya =new File(dosyaAdı);
        Scanner myReader=new Scanner(dosya);
        String line;
        while (myReader.hasNextLine()){
            line=myReader.nextLine();
            String[] veriler=line.split(";");
            Kullanici kullanici=new Kullanici(veriler[0],veriler[1],veriler[2]);
            listekullanici.add(kullanici);
        }
    }
}


