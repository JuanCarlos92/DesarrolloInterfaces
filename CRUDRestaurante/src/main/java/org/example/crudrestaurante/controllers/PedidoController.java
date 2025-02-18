package org.example.crudrestaurante.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.crudrestaurante.models.Pedido;
import org.example.crudrestaurante.models.Cliente;
import org.example.crudrestaurante.models.Producto;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PedidoController {

    private static final String URL = "jdbc:mysql://localhost:3306/RestauranteDB";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";

    @FXML
    private TextField tfClienteId, tfTotal, tfEstado;
    @FXML
    private TableView<Pedido> tableView;
    @FXML
    private TableColumn<Pedido, Integer> tableId, tableClienteId;
    @FXML
    private TableColumn<Pedido, String> tableFecha, tableHora, tableTotal, tableEstado;
    @FXML
    private Button btVolver;

    private final ObservableList<Pedido> listaPedidos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tableClienteId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCliente().getId()).asObject());
        tableFecha.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFechaPedido().toString()));
        tableHora.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getHoraPedido().toString()));
        tableTotal.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getTotal())));
        tableEstado.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstado()));

        cargarPedidos();
        tableView.setItems(listaPedidos);
    }

    @FXML
    private void cargarPedidos() {
        listaPedidos.clear();
        String query = "SELECT * FROM Pedidos";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCliente(new Cliente(rs.getInt("cliente_id"), "", "", ""));
                pedido.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());
                pedido.setHoraPedido(rs.getTime("hora_pedido").toLocalTime());
                pedido.setTotal(rs.getFloat("total"));
                pedido.setEstado(rs.getString("estado"));
                listaPedidos.add(pedido);
            }
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo cargar la lista de pedidos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void crearPedido() {
        if (!tfClienteId.getText().isEmpty() && !tfTotal.getText().isEmpty() && !tfEstado.getText().isEmpty()) {
            String query = "INSERT INTO Pedidos (cliente_id, fecha_pedido, hora_pedido, total, estado) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, Integer.parseInt(tfClienteId.getText()));
                stmt.setDate(2, Date.valueOf(LocalDate.now()));
                stmt.setTime(3, Time.valueOf(LocalTime.now()));
                stmt.setDouble(4, Float.parseFloat(tfTotal.getText()));
                stmt.setString(5, tfEstado.getText());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo crear el pedido.");
                e.printStackTrace();
                return;
            }

            cargarPedidos();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
        }
    }

    @FXML
    private void modificarPedido() {
        Pedido pedidoSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            int nuevoClienteId = tfClienteId.getText().isEmpty() ? pedidoSeleccionado.getCliente().getId() : Integer.parseInt(tfClienteId.getText());
            float nuevoTotal = tfTotal.getText().isEmpty() ? pedidoSeleccionado.getTotal() : Float.parseFloat(tfTotal.getText());
            String nuevoEstado = tfEstado.getText().isEmpty() ? pedidoSeleccionado.getEstado() : tfEstado.getText();

            Cliente nuevoCliente = obtenerClientePorId(nuevoClienteId);

            if (nuevoCliente == null) {
                mostrarAlerta("Error", "El cliente no existe.");
                return;
            }

            String query = "UPDATE Pedidos SET cliente_id = ?, total = ?, estado = ? WHERE id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, nuevoClienteId);
                stmt.setFloat(2, nuevoTotal);
                stmt.setString(3, nuevoEstado);
                stmt.setInt(4, pedidoSeleccionado.getId());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo modificar el pedido.");
                e.printStackTrace();
                return;
            }

            cargarPedidos();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un pedido para modificar.");
        }
    }

    private Cliente obtenerClientePorId(int clienteId) {
        Cliente cliente = null;
        String query = "SELECT * FROM Clientes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @FXML
    private void buscarPedido() {
        String clienteIdBuscar = tfClienteId.getText();
        if (!clienteIdBuscar.isEmpty()) {
            String query = "SELECT * FROM Pedidos WHERE cliente_id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, Integer.parseInt(clienteIdBuscar));
                try (ResultSet rs = stmt.executeQuery()) {
                    listaPedidos.clear();
                    while (rs.next()) {
                        Pedido pedido = new Pedido();
                        pedido.setId(rs.getInt("id"));
                        pedido.setCliente(new Cliente(rs.getInt("cliente_id"), "", "", ""));
                        pedido.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());
                        pedido.setHoraPedido(rs.getTime("hora_pedido").toLocalTime());
                        pedido.setTotal(rs.getFloat("total"));
                        pedido.setEstado(rs.getString("estado"));
                        listaPedidos.add(pedido);
                    }

                    if (listaPedidos.isEmpty()) {
                        mostrarAlerta("Información", "No se encontraron pedidos para este cliente.");
                    }
                }
            } catch (SQLException e) {
                mostrarAlerta("Error", "Error al buscar los pedidos.");
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "Ingrese un ID de cliente para buscar.");
        }
    }

    @FXML
    private void borrarPedido() {
        Pedido pedidoSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            String query = "DELETE FROM Pedidos WHERE id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, pedidoSeleccionado.getId());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo eliminar el pedido.");
                e.printStackTrace();
                return;
            }

            cargarPedidos();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un pedido para eliminar.");
        }
    }

    @FXML
    private void volverAlMenu() {
        try {
            Stage stageActual = (Stage) btVolver.getScene().getWindow();
            stageActual.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/crudrestaurante/menu-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void limpiarCampos() {
        tfClienteId.clear();
        tfTotal.clear();
        tfEstado.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
