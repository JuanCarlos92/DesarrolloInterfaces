package org.example.crudrestaurante.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class DetallePedidoController {

    private static final String URL = "jdbc:mysql://localhost:3306/RestauranteDB";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";

    @FXML
    private ComboBox<String> comboBoxProducto;
    @FXML
    private TextField tfCantidad, tfPrecio;

    private int pedidoId;

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
        cargarProductos();
    }
    @FXML
    public void initialize() {


    }


    // Cargar los productos en el ComboBox
    private void cargarProductos() {
        ObservableList<String> listaProductos = FXCollections.observableArrayList();
        String query = "SELECT nombre FROM Producto"; // Asumiendo que la tabla Producto tiene un campo 'nombre'

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                listaProductos.add(rs.getString("nombre"));
            }
            comboBoxProducto.setItems(listaProductos);
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudieron cargar los productos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void agregarProducto() {
        if (comboBoxProducto.getValue() == null || tfCantidad.getText().isEmpty() || tfPrecio.getText().isEmpty()) {
            mostrarAlerta("Campos Vacíos", "Por favor, completa todos los campos.");
            return;
        }

        String productoSeleccionado = comboBoxProducto.getValue();
        int cantidad = 0;
        float precio = 0;

        try {
            cantidad = Integer.parseInt(tfCantidad.getText());
            precio = Float.parseFloat(tfPrecio.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La cantidad o el precio no son válidos.");
            return;
        }

        // Insertar el producto en DetallePedido
        String query = "INSERT INTO DetallePedido (pedido_id, producto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pedidoId);
            stmt.setString(2, productoSeleccionado);
            stmt.setInt(3, cantidad);
            stmt.setFloat(4, precio);
            stmt.executeUpdate();
            actualizarTotalPedido(conn);
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo agregar el producto.");
            e.printStackTrace();
            return;
        }

        // Cerrar la ventana de detalles
        Stage stage = (Stage) tfCantidad.getScene().getWindow();
        stage.close();
    }

    // Actualizar el total del pedido
    private void actualizarTotalPedido(Connection conn) {
        String queryTotal = "SELECT SUM(cantidad * precio) AS total FROM DetallePedido WHERE pedido_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(queryTotal)) {
            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                float total = rs.getFloat("total");

                // Actualizar el total en la tabla Pedidos
                String updatePedido = "UPDATE Pedidos SET total = ? WHERE pedido_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updatePedido)) {
                    updateStmt.setFloat(1, total);
                    updateStmt.setInt(2, pedidoId);
                    updateStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo actualizar el total del pedido.");
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
