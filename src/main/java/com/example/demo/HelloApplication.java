package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class HelloApplication extends Application {

    //Cargamos la vista principal del programa
    @Override
    public void start(Stage primaryStage) {

        Parent root;

        try {

            //Guardamos la dirección de la vista principal del main
            root = FXMLLoader.load(getClass().getResource("mainView.fxml"));

            //Con esta línea se aplican los estilos que le damos a la interfaz
            //root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Establecemos el título de la ventana
            //primaryStage.setTitle("QR TV+");
            // Establecemos el ancho y el alto
            primaryStage.setScene(new Scene(root));
            // Mostramos la ventana
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mostrarPagina(ActionEvent event, FXMLLoader fxmlLoader) {

        try {
            Parent root1 = (Parent) fxmlLoader.load();
            //root1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Para comprobar el personaje obtenido
    @FXML
    public void cerrarPagina(ActionEvent event, Button btnEmpezar) {

        try {
            // selecciona la vista a la que pertenece btn1
            Stage stage = (Stage) btnEmpezar.getScene().getWindow();
            // haciendo .hide cierra la ventana con la opción de reabrir la vista
            stage.hide();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
