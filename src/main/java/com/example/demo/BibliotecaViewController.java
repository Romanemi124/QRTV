package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class BibliotecaViewController extends NavegacionVistas implements Initializable {

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Rellenamos nuestro array con las posiciones obtenidas de la BD
        ids = baseDatos.getOnlyIds();

        // Rellenamos de 0's las posiciones las cuales no tengan contenido previo
        for (int i = ids.size() - 1; i < 24; i++) {
            ids.add(0);
        }

        mostrarCarteles(ids);
    }

    @FXML
    void showPortada(ActionEvent event) {

        mostrarCartelesButton(event, ids);
    }
}