package org.example.crudrestaurante.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    public Button btCliente;
    @FXML
    public Button btProducto;
    @FXML
    public Button btPedido;

    @FXML
    public void onClickCliente() throws IOException {
            Stage stagePrincipal = (Stage) btCliente.getScene().getWindow();
            stagePrincipal.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/crudrestaurante/cliente-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Clientes");
            stage.show();

    }

    @FXML
    public void onClickProducto() throws IOException {
        Stage stagePrincipal = (Stage) btProducto.getScene().getWindow();
        stagePrincipal.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/crudrestaurante/producto-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Productos");
        stage.show();

    }

    @FXML
    public void onClickPedido() {
    }

    @FXML
    public void onClickClose() {
        Stage stage = (Stage) btCliente.getScene().getWindow();
        stage.close();
    }
}