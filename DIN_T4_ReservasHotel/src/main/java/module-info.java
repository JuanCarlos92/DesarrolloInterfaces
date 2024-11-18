module org.example.din_t4_reservashotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;

    opens org.example.din_t4_reservashotel to javafx.fxml;
    opens org.example.din_t4_reservashotel.model to com.google.gson;
    exports org.example.din_t4_reservashotel;
    exports org.example.din_t4_reservashotel.controllers;
    opens org.example.din_t4_reservashotel.controllers to javafx.fxml;
}