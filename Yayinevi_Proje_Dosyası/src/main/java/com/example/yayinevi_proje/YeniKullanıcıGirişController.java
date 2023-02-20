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

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class YeniKullanıcıGirişController {
        @FXML
        private TextField kullanıcıAdıTextField;

        @FXML
        private Label kullanıcıErrorLabel;

        @FXML
        private Button yeniHesapButtonu;

        @FXML
        private Label şifreErrorLabel;

        @FXML
        private PasswordField şifreTextField;
        private static String kullanıcı;

    public String returnKullanıcı() {
        return kullanıcı;
    }

    private String şifre;
    private Stage stage;
    Scene scene;
    @FXML
    void onyeniHesapButonu(ActionEvent e) throws IOException {
        şifreErrorLabel.setText("");
        kullanıcıErrorLabel.setText("");
        boolean küçükHarf=false;
        boolean büyükHarf=false;
        boolean sayiHarf=false;
        boolean uzunluk=false;
        kullanıcı=kullanıcıAdıTextField.getText();
        şifre=şifreTextField.getText();
        ArrayList<Kullanici> kullanıcıArrayList=DefaultController.getTumKullanicilarArrayList();
        if(kullanıcı.isEmpty()){
            kullanıcıErrorLabel.setText("Kullanıcı adı girmediniz!");
        }else if(şifre.isEmpty()){
            şifreErrorLabel.setText("Şifre girmediniz!");
        }else{  //yeni kullanıcı-şifre kontrolü
            for (int i=0; i<kullanıcıArrayList.size();i++){
                if (kullanıcı.equals(kullanıcıArrayList.get(i).getKullaniciAdi())){
                    kullanıcıErrorLabel.setText("Bu kullanıcı adı alınmış");
                    break;
                }
            }
            //şifre kontrolü
            if (şifre.length()>=5){
                uzunluk=true;
            }
            for (int i=0;i<şifre.length();i++){
                char c=şifre.charAt(i);
                if (Character.isUpperCase(c)){
                    büyükHarf=true;
                }
                if (Character.isLowerCase(c)){
                    küçükHarf=true;
                }
                if (Character.isDigit(c)){
                    sayiHarf=true;
                }
            }
            if (!büyükHarf) {
                şifreErrorLabel.setText("Şifreniz büyük harf içermeli");
            } else if (!küçükHarf) {
                şifreErrorLabel.setText("Şifreniz küçük harf içermeli");
            } else if (!sayiHarf) {
                şifreErrorLabel.setText("Şifreniz rakam içermeli");
            } else if (!uzunluk) {
                şifreErrorLabel.setText("Şifreniz 5 karakterden uzun olmalı");
            } else if (büyükHarf&&küçükHarf&&sayiHarf) {
                //kullanıcıyı dosyaya işle
                FileWriter myWriter=new FileWriter("kullanıcıListe.txt",true);
                BufferedWriter bufferedWriter=new BufferedWriter(myWriter);
                bufferedWriter.write("\n"+kullanıcı+";"+şifre); //yeni kullanıcı dosyaya işlendi
                bufferedWriter.close();
                File dosya=new File("kullanıcıListe.txt");
                Scanner myReader=new Scanner(dosya);
                while (myReader.hasNextLine()){
                    System.out.println(myReader.nextLine());
                }
                Kullanici kullanici=new Kullanici(kullanıcı,şifre); //yeni kullanıcı oluşturldu
                DefaultController.tumKullanicilarArrayList.add(kullanici);
//                System.out.println(DefaultController.tumKullanicilarArrayList);
                //geldiği yere gönder
                String gelinenYer= KullanıcıGirişController.getNeredenGeldi();
                if(gelinenYer.equals("default")){
                    Parent root = FXMLLoader.load(getClass().getResource("hello-view-rabia.fxml"));
                    stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                    scene = new Scene(root,750, 700);
                    stage.setScene(scene);
                    stage.setTitle("YayıneviListe");
                    stage.show();
                }
                else if (gelinenYer.equals("kitap")) {
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

        }

    }
}
