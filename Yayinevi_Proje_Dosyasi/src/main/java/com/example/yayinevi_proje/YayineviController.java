package com.example.yayinevi_proje;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class YayineviController implements Initializable{
    public String yayineviKullanıcı;
    @FXML
    private Label yayineviLabel;
    @FXML
    private Label beğenmeLabel;
    @FXML
    private Button kullanıcıGirişButon;
    @FXML
    private Button anaSayfaButton;

    private Stage stage;
    Scene scene;
    //public int x=0;


    @FXML

    String[] kitapİsimleri;
    ArrayList<Yayinevi> yayineviArrayList=DefaultController.getTumYayinEvleriArrayList();

    @FXML
    private TableView<Kitap> kitapTableView;
    @FXML
    private TableColumn<Kitap, String> nameColumn;
    @FXML
    private TableColumn<Kitap, String> yazarColumn;

    @FXML
    private TableColumn<Kitap, String> yıldızColumn;

    public static String SeçilenKitapİsmi;
    public static String SeçilenKitapİsmiDöndür(){
        return SeçilenKitapİsmi;
    }
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        String aaa=DefaultController.SeçilenYayıneviDöndür();
        yayineviLabel.setText(aaa);
        KullanıcıGirişController kullanıcıGirişController=new KullanıcıGirişController();
        yayineviKullanıcı=kullanıcıGirişController.returnKullanıcı();
        System.out.println("yayinevi içinde"+yayineviKullanıcı);
        if (!(yayineviKullanıcı==null)){
            kullanıcıGirişButon.setText(yayineviKullanıcı);
        }
        YeniKullanıcıGirişController yeniKullanıcıGirişController=new YeniKullanıcıGirişController();
        yayineviKullanıcı=yeniKullanıcıGirişController.returnKullanıcı();
        if (!(yayineviKullanıcı==null)){
            kullanıcıGirişButon.setText(yayineviKullanıcı);
        }
        //TableView doldurumu
        /*if (DefaultController.defaultSayac==0) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<Kitap,String>("isim"));
            yazarColumn.setCellValueFactory(new PropertyValueFactory<Kitap,String>("yazar"));
            yıldızColumn.setCellValueFactory(new PropertyValueFactory<Kitap,String>("ortYildiz"));
            kitapTableView.setItems((getKitapBilgileri()));
            DefaultController.defaultSayac=1;
            System.out.println("XXXXXXXX"+DefaultController.defaultSayac);
        }*/
        nameColumn.setCellValueFactory(new PropertyValueFactory<Kitap,String>("isim"));
        yazarColumn.setCellValueFactory(new PropertyValueFactory<Kitap,String>("yazar"));
        yıldızColumn.setCellValueFactory(new PropertyValueFactory<Kitap,String>("ortYildiz"));
        kitapTableView.setItems((getKitapBilgileri()));

        kitapTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                KitapController kitapController=new KitapController();
                SeçilenKitapİsmi=(kitapTableView.getSelectionModel().getSelectedItem().getIsim());
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("kitap-view.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                scene = new Scene(root,750, 700);
                stage.setScene(scene);
                stage.setTitle("Kitap Sayfası");
                stage.show();
            }
        });
    }
    public ObservableList<Kitap> getKitapBilgileri(){
        String degisken=DefaultController.SeçilenYayıneviDöndür();
        ObservableList<Kitap> kitaplar= FXCollections.observableArrayList();
        for (int i=0;i<9;i++){
            if (degisken.equals(yayineviArrayList.get(i).getAd())){
                Yayinevi yayinevi=yayineviArrayList.get(i);
                //System.out.println("RABİA");
                //System.out.println(yayinevi.genelYuzdeBastırma());
                //System.out.println(yayinevi.OrtYildizSayisi(yayinevi.ye_kitapArrayList.get(2).getYorumArrayList()));
                int size=yayinevi.getYe_kitapArrayList().size();
                for(int y=0; y<size;y++){
                    kitaplar.add(yayinevi.getYe_kitapArrayList().get(y));
                    //System.out.println("****"+yayinevi.OrtYildizSayisi(yayinevi.getYe_kitapArrayList().get(y).getYorumArrayList()));
                }
                String rr=yayinevi.genelYuzdeBastırma();
                beğenmeLabel.setText(rr);
                //System.out.println(yayinevi);
                break;
            }
        }
        System.out.println("SELİNNNNNN");
        return kitaplar;
    }
    public void onKullanıcıGirişButtonClick(ActionEvent e) throws IOException {
        if(kullanıcıGirişButon.getText().equals("Kullanıcı Girişi")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kullanıcı-giriş-view.fxml"));
            Parent root = loader.load();
            KullanıcıGirişController kullanıcıGirişController = loader.getController();
            kullanıcıGirişController.setNeredenGeldi(yayineviLabel.getText());
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
}
