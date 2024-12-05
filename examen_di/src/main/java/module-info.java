module org.example.examen_di {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examen_di to javafx.fxml;
    exports org.example.examen_di;
    exports org.example.examen_di.controller;
    opens org.example.examen_di.controller to javafx.fxml;
}