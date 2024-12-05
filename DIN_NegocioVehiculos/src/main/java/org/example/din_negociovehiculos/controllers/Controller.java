package org.example.din_negociovehiculos.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button cocheElectricoBt;

    @FXML
    private void handleCocheElectrico() {
        abrirFormularioCocheElectrico();
    }

    private void abrirFormularioCocheElectrico() {
        try {
            Stage stagePrincipal = (Stage) cocheElectricoBt.getScene().getWindow();
            stagePrincipal.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/din_negociovehiculos/cocheElectricoForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Formulario - Coche Eléctrico");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar el formulario");
            alert.setContentText("No se pudo abrir el formulario de coche eléctrico. Verifica la ruta del archivo FXML.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBicicletasElectricas() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Bicicletas Eléctricas");
        alert.setContentText("NO DISPONEMOS DE BICICLETAS EN ESTE MOMENTO.");
        alert.showAndWait();
    }
    @FXML
    private void handleExit(){
        Platform.exit();

    }

}