module com.example.yayinevi_proje {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.yayinevi_proje to javafx.fxml;
    exports com.example.yayinevi_proje;
}