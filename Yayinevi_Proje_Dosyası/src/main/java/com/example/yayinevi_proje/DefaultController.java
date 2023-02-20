package com.example.yayinevi_proje;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DefaultController implements Initializable {
    public static String SeçilenYayınyevi;
    public String defaultKullanıcı;
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
    public static ArrayList<Kullanici> getTumKullanicilarArrayList() {
        return tumKullanicilarArrayList;
    }
    public static String SeçilenYayıneviDöndür(){
        return SeçilenYayınyevi;
    }
    public static void dosyalariOkuma() throws FileNotFoundException {
        yazarOku("yazarListe.txt", tumYazarlarArrayList);
        yayineviOku("yayıneviListe.txt", tumYayinEvleriArrayList);
        kitapOku("kitapListe.txt", tumKitaplarArrayList, tumYayinEvleriArrayList, tumYazarlarArrayList);
        kullaniciOku("kullanıcıListe.txt",tumKullanicilarArrayList);
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
                        yayinevi.OrtYildizSayisi(okuma.getYorumArrayList());
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
                        yayinevi.OrtYildizSayisi(egitim.getYorumArrayList());
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
            if (veriler.length==3){
                Kullanici kullanici = new Kullanici(veriler[0], veriler[1], veriler[2]);
                listekullanici.add(kullanici);
            }
            else {
                System.out.println("202333");
                Kullanici kullanici = new Kullanici(veriler[0], veriler[1]);//yorumu olmayan kullanıcı
                listekullanici.add(kullanici);
            }
        }
    }
    public static ArrayList<Yayinevi> yayineviList=getTumYayinEvleriArrayList();

    private Stage stage;
    Parent root;
    Scene scene;
    @FXML
    private Button yayınevi1Button;

    @FXML
    private Button yayınevi2Button;

    @FXML
    private Button yayınevi3Button;

    @FXML
    private Button yayınevi4Button;

    @FXML
    private Button yayınevi5Button;

    @FXML
    private Button yayınevi6Button;

    @FXML
    private Button yayınevi7Button;

    @FXML
    private Button yayınevi8Button;

    @FXML
    private Button yayınevi9Button;
    @FXML
    private Button kullanıcıGirişButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setYayineviLabels();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        KullanıcıGirişController kullanıcıGirişController=new KullanıcıGirişController();
        defaultKullanıcı=kullanıcıGirişController.returnKullanıcı();
        System.out.println("default içinde:"+defaultKullanıcı);
        if (!(defaultKullanıcı==null)){
            kullanıcıGirişButton.setText(defaultKullanıcı);
        }
        YeniKullanıcıGirişController yeniKullanıcıGirişController=new YeniKullanıcıGirişController();
        defaultKullanıcı=yeniKullanıcıGirişController.returnKullanıcı();
        if (!(defaultKullanıcı==null)){
            kullanıcıGirişButton.setText(defaultKullanıcı);
        }
    }
    public void onKullanıcıGirişButtonClick(ActionEvent e) throws IOException{
        if (kullanıcıGirişButton.getText().equals("Kullanıcı Girişi")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanıcı-giriş-view.fxml"));
            root = loader.load();
            KullanıcıGirişController kullanıcıGirişController=loader.getController();
            kullanıcıGirişController.setNeredenGeldi("default");
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root,750, 700);
            stage.setScene(scene);
            stage.setTitle("Kullanıcı Girişi");
            stage.show();
        }
        else{   //kullanıcı ana sayfasına yönlendirir
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanıcı-anasayfa.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root,750, 700);
            stage.setScene(scene);
            stage.setTitle("Kullanıcı Hesap Sayfası");
            stage.show();
        }

    }
    private void setYayineviLabels() throws FileNotFoundException {
        dosyalariOkuma();
        yayınevi1Button.setText(yayineviList.get(0).getAd());
        yayınevi2Button.setText(yayineviList.get(1).getAd());
        yayınevi3Button.setText(yayineviList.get(2).getAd());
        yayınevi4Button.setText(yayineviList.get(3).getAd());
        yayınevi5Button.setText(yayineviList.get(4).getAd());
        yayınevi6Button.setText(yayineviList.get(5).getAd());
        yayınevi7Button.setText(yayineviList.get(6).getAd());
        yayınevi8Button.setText(yayineviList.get(7).getAd());
        yayınevi9Button.setText(yayineviList.get(8).getAd());
    }
    @FXML
    public void onYayineviButton1Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi1Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton2Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi2Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton3Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi3Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton4Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi4Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton5Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi5Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton6Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi6Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton7Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi7Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onYayineviButton8Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi8Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void onYayineviButton9Click(ActionEvent event) throws IOException {
        SeçilenYayınyevi=yayınevi9Button.getText();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
        root = loader.load();
        YayineviController yayineviController = loader.getController();
        Parent root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 750, 700);
        stage.setScene(scene);
        stage.show();
    }
}
