package com.example.yayinevi_proje;

public class Yorum {
    private Kitap kitapAdı;
    private int yildiz;
    private String kitapİsmi;
    private String yazarİsmi;

    public Yorum(Kitap kitapAdı, int yildiz) {
        this.kitapAdı = kitapAdı;
        kitapİsmi=kitapAdı.getIsim();
        yazarİsmi=kitapAdı.getYazar();
        this.yildiz = yildiz;
    }

    public Kitap getKitapAdı() {
        return kitapAdı;
    }

    public int getYildiz() {
        return yildiz;
    }

    public String getKitapİsmi() {
        return kitapİsmi;
    }

    public String getYazarİsmi() {
        return yazarİsmi;
    }

    @Override
    public String toString() {
        return "Yorum:{" + "kitapAdı=" + kitapAdı.getIsim() + ", yildiz=" + yildiz + '}';
    }
}
