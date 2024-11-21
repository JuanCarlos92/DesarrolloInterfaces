package org.example.din_negociovehiculos.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CocheElectricoFormController {

    @FXML
    private CheckBox cablecargaCheckbox;

    @FXML
    private Label cableCargaLb;

    @FXML
    private Button volverMenuBt;

    public void initialize() {
        // Agregar un listener para el CheckBox
        cablecargaCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cableCargaLb.setText("Sí, es necesario un cable de carga.");
            } else {
                cableCargaLb.setText("");
            }
        });
    }
    @FXML
    private void volverAlMenu(){
        Stage stage = (Stage) volverMenuBt.getScene().getWindow();
        stage.close();

        //abrir el menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/din_negociovehiculos/menuForm.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Menú Principal");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @FXML
    private void handleGuardar() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Guardada");
        alert.setHeaderText("Reserva registrada con éxito");
        alert.setContentText("Reserva completada");
        alert.showAndWait();
    }

}
