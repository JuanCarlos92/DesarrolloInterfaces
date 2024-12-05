package org.example.examen_di.controller;

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
    private Button volverBt;
    @FXML
    private TextField usuarioTf;
    @FXML
    private PasswordField contraPf;
    @FXML
    private Button loginBt;
    @FXML
    private Button formularioBt;


    @FXML
    private void volverAlMenu() {
        Stage stage = (Stage) volverBt.getScene().getWindow();
        stage.close();

        //abrir el menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/examen_di/menu.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Menú Principal");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void manejadorFormulario() {
        abrirFormulario();
    }

    private void abrirFormulario() {
        Stage stagePrincipal = (Stage) formularioBt.getScene().getWindow();
        stagePrincipal.close();

        //abrir el menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/examen_di/formulario.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.setTitle("Menú Principal");
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
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "No puede dejar los campos en blanco.");
            return;
        }

        // Validar usuario y contraseña
        if (usuario.equals("dam") && contra.equals("dam1234")) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Login Correcto", "Usuario y contraseña correctos.");
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

