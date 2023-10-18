package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class RegistroViewController {

    @FXML
    private Button btnMostrarViewLogin2;
    @FXML
    private Button btnMostrarViewPrincipal;

    HelloApplication main = new HelloApplication();

    //Dirección de las diferentes vistas que se podrán usar
    FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));

    @FXML
    void showViewLogin2(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewLogin2);
            main.mostrarPagina(event, loaderLogin);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewPrincipal(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewPrincipal);
            main.mostrarPagina(event, loaderPrincipal);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
