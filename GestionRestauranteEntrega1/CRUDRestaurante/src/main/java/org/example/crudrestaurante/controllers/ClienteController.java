package org.example.crudrestaurante.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.crudrestaurante.models.Cliente;

import java.sql.*;

public class ClienteController {

    private static final String URL = "jdbc:mysql://localhost:3306/RestauranteDB";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";

    @FXML
    private TextField tfNombre, tfTelefono, tfDireccion;
    @FXML
    private TableView<Cliente> tableView;
    @FXML
    private TableColumn<Cliente, Integer> tableId;
    @FXML
    private TableColumn<Cliente, String> tableNombre, tableTelefono, tableDireccion;

    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        tableId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tableNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        tableTelefono.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelefono()));
        tableDireccion.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDireccion()));

        // Cargar los datos desde la base de datos
        cargarClientes();
        tableView.setItems(listaClientes);
    }
    @FXML
    private void cargarClientes() {
        listaClientes.clear();
        String query = "SELECT * FROM Cliente";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo cargar la lista de clientes.");
            e.printStackTrace();
        }
    }

    @FXML
    private void crearCliente() {
        if (!tfNombre.getText().isEmpty() && !tfTelefono.getText().isEmpty() && !tfDireccion.getText().isEmpty()) {
            String query = "INSERT INTO Cliente (nombre, telefono, direccion) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, tfNombre.getText());
                stmt.setString(2, tfTelefono.getText());
                stmt.setString(3, tfDireccion.getText());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo crear el cliente.");
                e.printStackTrace();
                return;
            }

            cargarClientes();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
        }
    }

    @FXML
    private void modificarCliente() {
        Cliente clienteSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            String nuevoNombre = tfNombre.getText().isEmpty() ? clienteSeleccionado.getNombre() : tfNombre.getText();
            String nuevoTelefono = tfTelefono.getText().isEmpty() ? clienteSeleccionado.getTelefono() : tfTelefono.getText();
            String nuevaDireccion = tfDireccion.getText().isEmpty() ? clienteSeleccionado.getDireccion() : tfDireccion.getText();

            String query = "UPDATE Cliente SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, nuevoNombre);
                stmt.setString(2, nuevoTelefono);
                stmt.setString(3, nuevaDireccion);
                stmt.setInt(4, clienteSeleccionado.getId());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo modificar el cliente.");
                e.printStackTrace();
                return;
            }

            cargarClientes();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un cliente para modificar.");
        }
    }


    @FXML
    private void buscarCliente() {
        String nombreBuscar = tfNombre.getText();
        if (!nombreBuscar.isEmpty()) {
            String query = "SELECT * FROM Cliente WHERE nombre = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, nombreBuscar);
                try (ResultSet rs = stmt.executeQuery()) {
                    listaClientes.clear(); // Limpiar la tabla antes de agregar el cliente encontrado
                    if (rs.next()) {
                        Cliente cliente = new Cliente();
                        cliente.setId(rs.getInt("id"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setTelefono(rs.getString("telefono"));
                        cliente.setDireccion(rs.getString("direccion"));

                        listaClientes.add(cliente);
                    } else {
                        mostrarAlerta("Información", "No se encontró el cliente.");
                    }
                }
            } catch (SQLException e) {
                mostrarAlerta("Error", "Error al buscar el cliente.");
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "Ingrese un nombre para buscar.");
        }
    }


    @FXML
    private void borrarCliente() {
        Cliente clienteSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            String query = "DELETE FROM Cliente WHERE id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, clienteSeleccionado.getId());
                stmt.executeUpdate();

            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo eliminar el cliente.");
                e.printStackTrace();
                return;
            }

            cargarClientes();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Seleccione un cliente para eliminar.");
        }
    }

    private void limpiarCampos() {
        tfNombre.clear();
        tfTelefono.clear();
        tfDireccion.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
