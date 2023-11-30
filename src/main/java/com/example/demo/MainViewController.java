package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

/**
 * Controlador principal que gestiona la navegación entre las vistas principal, de inicio de sesión y de registro.
 */
public class MainViewController {

    @FXML
    private Button btnMostrarViewLogin, btnMostrarViewRegistro;

    HelloApplication main = HelloApplication.getInstance();

    // Dirección de las diferentes vistas que se podrán usar
    FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
    FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("registroView.fxml"));

    /**
     * Muestra la vista de inicio de sesión al hacer clic en el botón correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    void showViewLogin(ActionEvent event) {
        try {
            // creamos objeto del Main para poder llamar al metodo start2
            main.cerrarPagina(event, btnMostrarViewLogin);
            main.mostrarPagina(event, loaderLogin);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista de registro al hacer clic en el botón correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    void showViewRegistro(ActionEvent event) {
        try {
            // creamos objeto del Main para poder llamar al metodo start2
            main.cerrarPagina(event, btnMostrarViewRegistro);
            main.mostrarPagina(event, loaderRegistro);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}