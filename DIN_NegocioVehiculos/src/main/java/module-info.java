module org.example.din_negociovehiculos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.din_negociovehiculos to javafx.fxml;
    exports org.example.din_negociovehiculos;
}