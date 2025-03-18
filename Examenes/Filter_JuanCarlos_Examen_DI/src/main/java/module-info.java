module org.example.filter_juancarlos_examen_di {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.filter_juancarlos_examen_di to javafx.fxml;
    exports org.example.filter_juancarlos_examen_di;
    exports org.example.filter_juancarlos_examen_di.controllers;
    opens org.example.filter_juancarlos_examen_di.controllers to javafx.fxml;
}