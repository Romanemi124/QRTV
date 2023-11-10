package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlAdminViewController extends NavegacionVistas {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnLoginAdmin;

    @FXML
    private TextField txtCode;

    @FXML
    private Label txtError;

    @FXML
    private TextField txtId;

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        System.out.println("el valor recogido es : " + idUsuario);
    }

    @FXML
    void showSesion(ActionEvent event) {

    }

    @FXML
    void showSesionAdmin(ActionEvent event) {

    }
}


