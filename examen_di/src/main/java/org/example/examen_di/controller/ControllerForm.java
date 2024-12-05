package org.example.examen_di.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerForm {
    @FXML
    private Label nombreLb;
    @FXML
    private Label apellidoLb;
    @FXML
    private Label telefonoLb;
    @FXML
    private Label dniLb;
    @FXML
    private TextField nombreTf;
    @FXML
    private TextField apellidoTf;
    @FXML
    private TextField telefonoTf;
    @FXML
    private TextField dniTf;
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
        /*nombreLb.setMnemonicParsing(true);
        apellidoLb.setMnemonicParsing(true);
        telefonoLb.setMnemonicParsing(true);
        dniLb.setMnemonicParsing(true);

        nombreLb.setText("_Nombre");
        apellidoLb.setText("_Apellido");
        telefonoLb.setText("_Telefono");
        dniLb.setText("_DNI/CIF");*/

        nombreLb.setLabelFor(nombreTf);
        apellidoLb.setLabelFor(apellidoTf);
        telefonoLb.setLabelFor(telefonoTf);
        dniLb.setLabelFor(dniTf);

    }

    @FXML
    private void volverAlMenu() {
        Stage stage = (Stage) volverMenuBt.getScene().getWindow();
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
    private void manejadorGuardar() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Guardada");
        alert.setHeaderText("Reserva registrada con éxito");
        alert.setContentText("Reserva completada");
        alert.showAndWait();
    }


}