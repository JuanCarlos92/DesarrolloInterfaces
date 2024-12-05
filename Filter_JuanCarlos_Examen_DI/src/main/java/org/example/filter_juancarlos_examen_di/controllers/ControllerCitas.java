package org.example.filter_juancarlos_examen_di.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCitas {
    @FXML
    private Label nombreLb;
    @FXML
    private Label apellidoLb;
    @FXML
    private Label dniLb;
    @FXML
    private Label fechaLb;
    @FXML
    private TextField nombreTf;
    @FXML
    private TextField apellidoTf;
    @FXML
    private TextField dniTf;
    @FXML
    private DatePicker fechaDatePicker;
    @FXML
    private Button cancelarBt;
    @FXML
    private ComboBox especialidadCb;

    public void initialize() {
        dniTf.setTooltip(new Tooltip("Introduce el DNI"));
        nombreTf.setTooltip(new Tooltip("Introduce el nombre"));
        apellidoTf.setTooltip(new Tooltip("Introduce el apellido"));
        fechaDatePicker.setTooltip(new Tooltip("Introduce la fecha"));
        especialidadCb.setTooltip(new Tooltip("Selecciona la especialidad"));

        nombreLb.setLabelFor(nombreTf);
        apellidoLb.setLabelFor(apellidoTf);
        dniLb.setLabelFor(dniTf);
        fechaLb.setLabelFor(fechaDatePicker);

    }

    @FXML
    private void volverAlMenu() {
        Stage stage = (Stage) cancelarBt.getScene().getWindow();
        stage.close();

        //abrir el menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/filter_juancarlos_examen_di/menu.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Menú Principal");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void manejadorGuardar() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrado");
        alert.setHeaderText("Cita concertada");
        alert.setContentText("Cita registrada no olvide acudir a su cita");
        alert.showAndWait();

        nombreTf.clear();
        apellidoTf.clear();
        dniTf.clear();
        fechaDatePicker.setValue(null);

        especialidadCb.setValue(null);
    }
    @FXML
    private void manejadorlimpiar() {
        nombreTf.clear();
        apellidoTf.clear();
        dniTf.clear();
        fechaDatePicker.setValue(null);

        especialidadCb.setValue(null);
    }

}
