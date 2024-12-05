package org.example.filter_juancarlos_examen_di.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {
    @FXML
    private Button cancelarBt;
    @FXML
    private TextField usuarioTf;
    @FXML
    private PasswordField contraPf;
    @FXML
    private Button aceptarBt;

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

    private void abrirCitas() {
        Stage stagePrincipal = (Stage) aceptarBt.getScene().getWindow();
        stagePrincipal.close();

        //abrir el formulario de citas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/filter_juancarlos_examen_di/citas.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Formulario de citas");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void manejadorLogin() {
        String usuario = usuarioTf.getText().trim();
        String contra = contraPf.getText().trim();

        // Verificar si los campos están vacíos
        if (usuario.isEmpty() || contra.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Introduzca usuario o contraseña.");
            return;
        }

        // Validar usuario y contraseña
        if (usuario.equals("admin") && contra.equals("1234")) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Login Correcto", "Usuario y contraseña correctos.");
            abrirCitas();

        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Login Incorrecto", "Usuario o contraseña incorrectos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}

