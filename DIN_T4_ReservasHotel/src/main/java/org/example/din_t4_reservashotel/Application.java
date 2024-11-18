package org.example.din_t4_reservashotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.din_t4_reservashotel.controllers.Controller;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //Cargar el archivo FXML para la ventana de reservas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.fxml"));
            Scene scene = new Scene(loader.load());

            //Obtener el controlador y configurar la escena
            Controller controller = loader.getController();
            controller.setScene(scene);

            //Configuración de la escena para la ventana de reservas
            primaryStage.setTitle("Formulario de Reserva de Hotel");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}