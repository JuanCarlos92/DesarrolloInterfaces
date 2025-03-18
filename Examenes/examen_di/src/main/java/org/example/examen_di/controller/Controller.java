package org.example.examen_di.controller;

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
    private Button formularioBt;
    @FXML
    private Button loginBt;

    @FXML
    private void manejadorFormulario() {
        abrirFormulario();
    }
    @FXML
    private void manejadorLogin() {
        abrirLogin();
    }


    private void abrirFormulario() {
        try {
            Stage stagePrincipal = (Stage) formularioBt.getScene().getWindow();
            stagePrincipal.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/examen_di/formulario.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Formulario");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar el formulario");
            alert.setContentText("No se pudo abrir el formulario. Verifica la ruta del archivo FXML.");
            alert.showAndWait();
        }
    }

    @FXML
    private void abrirLogin() {
        try {
            Stage stagePrincipal = (Stage) loginBt.getScene().getWindow();
            stagePrincipal.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/examen_di/login.fxml"));
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