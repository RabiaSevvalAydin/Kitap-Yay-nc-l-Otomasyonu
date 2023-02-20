package com.example.yayinevi_proje;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class KullanıcıGirişController {
    private static String kullanıcı;

    public String returnKullanıcı() {
        return kullanıcı;
    }

    private String şifre;
    ArrayList<Kullanici> kullaniciArrayList=DefaultController.getTumKullanicilarArrayList();
    @FXML
    private Button girişButton;
    @FXML
    private Button yeniHesapButtonu;

    @FXML
    private TextField kullanıcıAdıTextField;
    @FXML
    private PasswordField şifreTextField;
    @FXML
    private Label kullanıcıErrorLabel;
    @FXML
    private Label şifreErrorLabel;
    private static String neredenGeldi;

    public static String getNeredenGeldi() {
        return neredenGeldi;
    }

    public void setNeredenGeldi(String neredenGeldi) {
        this.neredenGeldi = neredenGeldi;
    }

    private Stage stage;
    Scene scene;

    public void onyeniHesapButonu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("yeni-kullanıcı-giriş.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,750, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void onGirişButonu(ActionEvent e) throws IOException{
//        System.out.println("ggg"+kullanıcı);
        kullanıcı=kullanıcıAdıTextField.getText();
        şifre=şifreTextField.getText();
        int index=kullaniciArrayList.size();
//        System.out.println(kullanıcı);
//        System.out.println(şifre);
        ArrayList<Kullanici> kullanıcıArrayList=DefaultController.getTumKullanicilarArrayList();
        if (kullanıcı.isEmpty()){
            kullanıcıErrorLabel.setText("Kullanıcı adı girmediniz!");
        } else if (şifre.isEmpty()) {
            şifreErrorLabel.setText("Şifre girmediniz!");
        }
        else{   //kullanıcı kontrolü
            for (int i=0;i<kullanıcıArrayList.size();i++){
                if (kullanıcı.equals(kullanıcıArrayList.get(i).getKullaniciAdi())){
                    index=i;
                    break;
                }
            }
            if (index==kullanıcıArrayList.size()){
                kullanıcıErrorLabel.setText("Kullanıcı değeri bulunamadı");
                şifreErrorLabel.setText("");
            }
            else {
                if (şifre.equals(kullanıcıArrayList.get(index).getSifre())){//nereden geldiyse oraya dönsün
                    if(neredenGeldi.equals("default")){
                        Parent root = FXMLLoader.load(getClass().getResource("hello-view-rabia.fxml"));
                        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                        scene = new Scene(root,750, 700);
                        stage.setScene(scene);
                        stage.setTitle("YayıneviListe");
                        stage.show();
                    } else if (neredenGeldi.equals("kitap")) {
                        Parent root = FXMLLoader.load(getClass().getResource("kitap-view.fxml"));
                        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                        scene = new Scene(root,750, 700);
                        stage.setScene(scene);
                        stage.setTitle("Kitap");
                        stage.show();
                    } else{
                        FXMLLoader loader = new FXMLLoader((getClass().getResource("yayinevi-view.fxml")));
                        Parent root = loader.load();
                        YayineviController yayineviController = loader.getController();
                        root = FXMLLoader.load(getClass().getResource("yayinevi-view.fxml"));
                        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                        scene = new Scene(root, 750, 700);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
                else {
                    şifreErrorLabel.setText("Şifre girişi hatalı");
                    kullanıcıErrorLabel.setText("");
                }
            }
        }
    }
}
