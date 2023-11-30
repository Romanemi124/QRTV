package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * Clase que ejecuta el programa mostrando la vista principal.
 * Esta clase extiende la clase Application de JavaFX.
 */
public class HelloApplication extends Application {

    private static final HelloApplication instance = new HelloApplication();

    /**
     * Método para obtener la única instancia de la clase HelloApplication.
     *
     * @return La instancia de HelloApplication.
     */
    public static HelloApplication getInstance() {
        return instance;
    }

    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {

        Parent root;

        try {

            // Guardamos la dirección de la vista principal del main
            root = FXMLLoader.load(getClass().getResource("mainView.fxml"));

            // Establecemos el ancho y el alto
            primaryStage.setScene(new Scene(root));

            // Mostramos la ventana
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar una página mediante un evento.
     *
     * @param event      El evento que desencadena la acción.
     * @param fxmlLoader El cargador de FXML para cargar la página.
     */
    @FXML
    void mostrarPagina(ActionEvent event, FXMLLoader fxmlLoader) {

        try {
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para cerrar una página mediante un evento.
     *
     * @param event      El evento que desencadena la acción.
     * @param btnEmpezar El botón que se utiliza para obtener la escena y cerrar la página.
     */
    @FXML
    public void cerrarPagina(ActionEvent event, Button btnEmpezar) {

        try {
            // Selecciona la vista a la que pertenece btnEmpezar
            Stage stage = (Stage) btnEmpezar.getScene().getWindow();

            // Haciendo .hide cierra la ventana con la opción de reabrir la vista
            stage.hide();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
