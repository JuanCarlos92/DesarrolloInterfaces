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

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class PedidoController {

    private static final String URL = "jdbc:mysql://localhost:3306/RestauranteDB";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";

    @FXML
    private TextField tfClienteNombre;
    @FXML
    private ComboBox<String> comboBoxEstado;
    @FXML
    private TableView<Pedido> tableView;
    @FXML
    private TableColumn<Pedido, Integer> tableId;
    @FXML
    private TableColumn<Pedido, String> tableClienteNombre, tableFecha, tableHora, tableTotal, tableEstado;
    @FXML
    private Button btVolver;

    private final ObservableList<Pedido> listaPedidos = FXCollections.observableArrayList();
    private ObservableList<String> listaProductos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tableClienteNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
        tableFecha.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFechaPedido().toString()));
        tableHora.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getHoraPedido().toString()));
        tableTotal.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getTotal())));
        tableEstado.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstado()));

        comboBoxEstado.setItems(FXCollections.observableArrayList("Pendiente", "En preparación", "Entregado"));
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
        if (!tfClienteNombre.getText().isEmpty() && comboBoxEstado.getValue() != null) {

            String nombreCliente = tfClienteNombre.getText();
            String estado = comboBoxEstado.getValue();

            String queryCliente = "SELECT cliente_id FROM Clientes WHERE nombre = ?";

            String queryPedido = "INSERT INTO Pedidos (cliente_id, fecha_pedido, hora_pedido, total, estado) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {

                try (PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
                    stmtCliente.setString(1, nombreCliente);
                    ResultSet rs = stmtCliente.executeQuery();

                    if (rs.next()) {
                        int clienteId = rs.getInt("cliente_id");

                        try (PreparedStatement stmtPedido = conn.prepareStatement(queryPedido)) {
                            stmtPedido.setInt(1, clienteId);
                            stmtPedido.setDate(2, Date.valueOf(LocalDate.now()));
                            stmtPedido.setTime(3, Time.valueOf(LocalTime.now()));
                            stmtPedido.setDouble(4, 0); // Total inicial en 0
                            stmtPedido.setString(5, estado);
                            stmtPedido.executeUpdate();
                        }

                        cargarPedidos();
                        limpiarCampos();

                    } else {
                        mostrarAlerta("Error", "No se encontró un cliente con ese nombre.");
                    }
                } catch (SQLException e) {
                    mostrarAlerta("Error", "Error al buscar el cliente.");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo conectar a la base de datos.");
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
        }
    }

    @FXML
    private void modificarPedido() {
        Pedido pedidoSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            String nuevoEstado = comboBoxEstado.getValue() != null ? comboBoxEstado.getValue() : pedidoSeleccionado.getEstado();

            String query = "UPDATE Pedidos SET estado = ? WHERE id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, nuevoEstado);
                stmt.setInt(2, pedidoSeleccionado.getId());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo modificar el pedido.");
                e.printStackTrace();
            }

            cargarPedidos();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un pedido para modificar.");
        }
    }

    @FXML
    private void buscarPedido() {
        String nombreClienteBuscar = tfClienteNombre.getText();
        if (!nombreClienteBuscar.isEmpty()) {
            String queryCliente = "SELECT cliente_id FROM Clientes WHERE nombre = ?";

            String queryPedidos = "SELECT * FROM Pedidos WHERE cliente_id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {

                try (PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
                    stmtCliente.setString(1, nombreClienteBuscar);
                    ResultSet rsCliente = stmtCliente.executeQuery();

                    if (rsCliente.next()) {
                        int clienteId = rsCliente.getInt("cliente_id");

                        try (PreparedStatement stmtPedidos = conn.prepareStatement(queryPedidos)) {
                            stmtPedidos.setInt(1, clienteId);
                            try (ResultSet rsPedidos = stmtPedidos.executeQuery()) {
                                listaPedidos.clear();

                                while (rsPedidos.next()) {
                                    Pedido pedido = new Pedido();
                                    pedido.setId(rsPedidos.getInt("id"));
                                    pedido.setCliente(new Cliente(rsPedidos.getInt("cliente_id"), "", "", ""));
                                    pedido.setFechaPedido(rsPedidos.getDate("fecha_pedido").toLocalDate());
                                    pedido.setHoraPedido(rsPedidos.getTime("hora_pedido").toLocalTime());
                                    pedido.setTotal(rsPedidos.getFloat("total"));
                                    pedido.setEstado(rsPedidos.getString("estado"));
                                    listaPedidos.add(pedido);
                                }

                                if (listaPedidos.isEmpty()) {
                                    mostrarAlerta("Información", "No se encontraron pedidos para este cliente.");
                                }
                            }
                        }
                    } else {
                        mostrarAlerta("Error", "No se encontró un cliente con ese nombre.");
                    }
                } catch (SQLException e) {
                    mostrarAlerta("Error", "Error al buscar los pedidos.");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo conectar a la base de datos.");
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "Ingrese un nombre de cliente para buscar.");
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
    private void seleccionarPedido() {
        Pedido pedidoSeleccionado = tableView.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado != null) {
            // Abrir la ventana de detalles para agregar productos
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ruta/a/detallePedido-view.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Añadir Producto al Pedido");

                // Pasar el ID del pedido seleccionado al controlador de la ventana de detalle
                DetallePedidoController detalleController = loader.getController();
                detalleController.setPedidoId(pedidoSeleccionado.getId());

                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo abrir la ventana de detalle del pedido.");
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "Selecciona un pedido primero.");
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
        tfClienteNombre.clear();
        comboBoxEstado.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



}
