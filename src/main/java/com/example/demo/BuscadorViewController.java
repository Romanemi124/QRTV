package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class BuscadorViewController extends NavegacionVistas {

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        //System.out.println("el valor recogido es : " + idUsuario);
    }
}