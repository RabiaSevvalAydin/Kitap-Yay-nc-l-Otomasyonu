package com.example.yayinevi_proje;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class KitapController implements Initializable {
    public String kitapKullanıcı;
    private Stage stage;
    Scene scene;
    @FXML
    private Label amaçKategoriLabel;

    @FXML
    private Label basımYılıLabel;

    @FXML
    private Label beğenmeLabel;

    @FXML
    private Label dilLabel;

    @FXML
    private Label fiyatLabel;

    @FXML
    private Label kitapBaşlıkLabel;

    @FXML
    private Label kitapİsimLabel;

    @FXML
    private Label konuLabel;


    @FXML
    private Button kullanıcıGirişButon;
    @FXML
    private Button anaSayfaButton;

    @FXML
    private Label sayfaSayısıLabel;

    @FXML
    private Label stokLabel;

    @FXML
    private Label stokUyarıLabel;

    @FXML
    private Label yazarYayıneviLabel;

    @FXML
    private Label yorumUyarıLabel;

    @FXML
    private ChoiceBox<Integer> yorumYapmaChoiceBox;
    private Integer[] yorumBoxSeçenek={5,4,3,2,1};
    public int index;
    Kitap kitap;

    @FXML
    void onKullanıcıGirişButtonClick(ActionEvent e) throws IOException {
        if(kullanıcıGirişButon.getText().equals("Kullanıcı Girişi")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanıcı-giriş-view.fxml"));
            Parent root = loader.load();
            KullanıcıGirişController kullanıcıGirişController = loader.getController();
            kullanıcıGirişController.setNeredenGeldi("kitap");
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root, 750, 700);
            stage.setScene(scene);
            stage.setTitle("Kullanıcı Girişi");
            stage.show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanıcı-anasayfa.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root,750, 700);
            stage.setScene(scene);
            stage.setTitle("Kullanıcı Hesap Sayfası");
            stage.show();
        }
    }

    @FXML
    void onAnaSayfaButtonClick(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("hello-view-rabia.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,750, 700);
        stage.setScene(scene);
        stage.setTitle("YayıneviListe");
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String aa=YayineviController.SeçilenKitapİsmiDöndür();
        kitapBaşlıkLabel.setText(aa);
        KullanıcıAnasayfaController kullanıcıAnasayfaController=new KullanıcıAnasayfaController();
        if (!(kullanıcıAnasayfaController.SeçilenKitapİsmiDöndür()==null)) {
            kitapBaşlıkLabel.setText(kullanıcıAnasayfaController.SeçilenKitapİsmiDöndür());
        }

        KullanıcıGirişController kullanıcıGirişController=new KullanıcıGirişController();
        kitapKullanıcı=kullanıcıGirişController.returnKullanıcı();
        if (!(kitapKullanıcı==null)){
            kullanıcıGirişButon.setText(kitapKullanıcı);
        }
        YeniKullanıcıGirişController yeniKullanıcıGirişController=new YeniKullanıcıGirişController();
        kitapKullanıcı=yeniKullanıcıGirişController.returnKullanıcı();
        if (!(kitapKullanıcı==null)){
            kullanıcıGirişButon.setText(kitapKullanıcı);
        }
        labelDoldur();
        yorumYapmaChoiceBox.getItems().addAll(yorumBoxSeçenek);
        yorumYapmaChoiceBox.setOnAction(this::getYYorum);
    }
    public void getYYorum(ActionEvent event){
        if (kullanıcıGirişButon.getText().equals("Kullanıcı Girişi")){
            yorumUyarıLabel.setText("Yıldız verebilmek için kullanıcı girişi yapmalısınız");
        }
        else {
            Integer yapılanYorum=yorumYapmaChoiceBox.getValue();
            DefaultController.yorumEkle(index,yapılanYorum);
            ArrayList<Integer> eskiYorumListe=DefaultController.getTumKitaplarArrayList().get(index).getYorumArrayList();
            DefaultController.getTumKitaplarArrayList().get(index).setOrtYildiz(DefaultController.tumKitaplarArrayList.get(index).OrtYildizSayisi(eskiYorumListe));
            beğenmeLabel.setText("Beğenme: "+kitap.getOrtYildiz());
            //yorum kullanıcıya eklemek için
            int size=DefaultController.tumKullanicilarArrayList.size();
            for(int i=0;i<size;i++){
                if(kullanıcıGirişButon.getText().equals(DefaultController.tumKullanicilarArrayList.get(i).getKullaniciAdi())){
                    Yorum yorum=new Yorum(DefaultController.getTumKitaplarArrayList().get(index),yapılanYorum);
                    DefaultController.tumKullanicilarArrayList.get(i).Yorumlar.add(yorum);
                }
            }

        }

    }
    public void labelDoldur(){
        String kitapisim=kitapBaşlıkLabel.getText();

        int size=DefaultController.tumKitaplarArrayList.size();
        for (int i=0; i<size;i++) {
            if (kitapisim.equals(DefaultController.tumKitaplarArrayList.get(i).getIsim())) {
                //aranan kitap bulundu
                index=i;
                kitap=DefaultController.tumKitaplarArrayList.get(i);
                kitapİsimLabel.setText(kitap.getIsim());
                yazarYayıneviLabel.setText("Yazar: "+kitap.getYazar()+"    Yayınevi: "+kitap.getYayinevi());
                amaçKategoriLabel.setText("Amaç: "+kitap.getAmac()+"    Kategori: "+kitap.getKategori());
                sayfaSayısıLabel.setText("Sayfa Sayisi: "+kitap.getsayfaSayisi());
                dilLabel.setText("Dil: "+kitap.getDil());
                basımYılıLabel.setText("Basım Yılı: "+kitap.getBasimYili());
                stokLabel.setText("Stok Sayısı: "+kitap.getStokSayisi());;
                fiyatLabel.setText("Fiyat: "+kitap.getFiyat()+"TL");
                beğenmeLabel.setText("Beğenme: "+kitap.getOrtYildiz());
                konuLabel.setText("Konu: "+kitap.getKonu());
                konuLabel.setWrapText(true);
                if (kitap.getStokSayisi().equals("0")){
                    stokUyarıLabel.setText("Stok kalmadı");
                }

                break;

            }
        }
    }
}
