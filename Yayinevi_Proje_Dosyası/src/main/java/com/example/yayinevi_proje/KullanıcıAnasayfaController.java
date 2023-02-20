package com.example.yayinevi_proje;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KullanıcıAnasayfaController implements Initializable {
    @FXML
    private Label kullaniciİsimLabel;
    @FXML
    private TableView<Yorum> kitapTableView;
    @FXML
    private TableColumn<Yorum, String> nameColumn;
    @FXML
    private TableColumn<Yorum, String> yazarColumn;
    @FXML
    private TableColumn<Yorum,Integer> yıldızColumn;
    ArrayList<Kullanici> kullaniciArrayList=DefaultController.getTumKullanicilarArrayList();

    public static String SeçilenKitapİsmi;
    public static String SeçilenKitapİsmiDöndür(){
        return SeçilenKitapİsmi;
    }
    private Stage stage;
    Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KullanıcıGirişController kullanıcıGirişController=new KullanıcıGirişController();
        YeniKullanıcıGirişController yeniKullanıcıGirişController=new YeniKullanıcıGirişController();
        if (kullanıcıGirişController.returnKullanıcı()==null){
            kullaniciİsimLabel.setText(yeniKullanıcıGirişController.returnKullanıcı());
        }else{
            kullaniciİsimLabel.setText(kullanıcıGirişController.returnKullanıcı());
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<Yorum,String>("kitapİsmi"));
        yazarColumn.setCellValueFactory(new PropertyValueFactory<Yorum,String>("yazarİsmi"));
        yıldızColumn.setCellValueFactory(new PropertyValueFactory<Yorum,Integer>("yildiz"));
        kitapTableView.setItems((getYorumBilgileri()));
        kitapTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SeçilenKitapİsmi=(kitapTableView.getSelectionModel().getSelectedItem().getKitapAdı().getIsim());
                System.out.println("RRR"+SeçilenKitapİsmi);
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
                stage.show();
            }
        });
    }

    public ObservableList<Yorum> getYorumBilgileri(){
        String degisken=kullaniciİsimLabel.getText();
        ObservableList<Yorum> yorumlar= FXCollections.observableArrayList();
        for (int i=0;i<9;i++){
            if (degisken.equals(kullaniciArrayList.get(i).getKullaniciAdi())){
                Kullanici kullanici=kullaniciArrayList.get(i);
                int size=kullanici.getYorumlar().size();
                for(int y=0; y<size;y++){
                    yorumlar.add(kullanici.getYorumlar().get(y));
                }
                break;
            }
        }
        return yorumlar;
    }

}
