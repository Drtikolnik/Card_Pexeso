module com.example.card_pexeso {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.card_pexeso to javafx.fxml;
    exports com.example.card_pexeso;
}