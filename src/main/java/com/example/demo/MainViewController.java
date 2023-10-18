package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class MainViewController {

    @FXML
    private Button btnMostrarViewLogin;

    @FXML
    private Button btnMostrarViewRegistro;

    HelloApplication main = new HelloApplication();

    //Dirección de las diferentes vistas que se podrán usar
    FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
    FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("registroView.fxml"));

    @FXML
    void showViewLogin(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewLogin);
            main.mostrarPagina(event, loaderLogin);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewRegistro(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewRegistro);
            main.mostrarPagina(event, loaderRegistro);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
