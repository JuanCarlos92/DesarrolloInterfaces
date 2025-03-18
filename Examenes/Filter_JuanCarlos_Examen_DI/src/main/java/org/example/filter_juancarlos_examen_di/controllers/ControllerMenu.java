package org.example.filter_juancarlos_examen_di.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMenu {
    @FXML
    private Button citasBt;
    @FXML
    private Button loginBt;

    @FXML
    private void manejadorCitas() {
        abrirCitas();
    }

    @FXML
    private void manejadorLogin() {
        abrirLogin();
    }


    private void abrirCitas() {
        try {
            Stage stagePrincipal = (Stage) citasBt.getScene().getWindow();
            stagePrincipal.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/filter_juancarlos_examen_di/citas.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Formulario de citas");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar el formulario de citas");
            alert.setContentText("No se pudo abrir el formulario. Verifica la ruta del archivo FXML.");
            alert.showAndWait();
        }
    }

    @FXML
    private void abrirLogin() {
        try {
            Stage stagePrincipal = (Stage) loginBt.getScene().getWindow();
            stagePrincipal.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/filter_juancarlos_examen_di/login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Login");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar el login");
            alert.setContentText("No se pudo abrir el login. Verifica la ruta del archivo FXML.");
            alert.showAndWait();
        }
    }

    @FXML
    private void manejadorSalir() {
        Platform.exit();

    }
}
