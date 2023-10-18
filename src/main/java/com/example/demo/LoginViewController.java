package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class LoginViewController {

    @FXML
    private Button btnMostrarViewRegistro2;
    @FXML
    private Button btnMostrarViewPrincipal2;

    HelloApplication main = new HelloApplication();

    //Dirección de las diferentes vistas que se podrán usar
    FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("registroView.fxml"));
    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));

    @FXML
    void showViewRegistro2(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewRegistro2);
            main.mostrarPagina(event, loaderRegistro);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnMostrarViewPrincipal2(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewPrincipal2);
            main.mostrarPagina(event, loaderPrincipal);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
