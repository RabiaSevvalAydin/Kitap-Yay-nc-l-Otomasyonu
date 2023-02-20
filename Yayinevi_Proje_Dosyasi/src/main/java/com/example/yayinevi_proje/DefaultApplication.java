package com.example.yayinevi_proje;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DefaultApplication extends Application{
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DefaultApplication.class.getResource("hello-view-rabia.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 700);
        stage.setTitle("Yayınevi Listesi");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }

}
