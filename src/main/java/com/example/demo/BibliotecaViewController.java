package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de la biblioteca.
 */
public class BibliotecaViewController extends NavegacionVistas implements Initializable {

    /**
     * Recoge el ID del usuario que ha iniciado sesión en todas las vistas.
     *
     * @param id El ID del usuario que ha iniciado sesión.
     */
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
    }

    /**
     * Inicializa la vista de la biblioteca.
     *
     * @param location  La ubicación relativa al código fuente de la vista.
     * @param resources Los recursos utilizados para localizar la vista.
     */
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

    /**
     * Muestra las portadas de la biblioteca.
     *
     * @param event El evento de acción.
     */
    @FXML
    void showPortada(ActionEvent event) {
        mostrarCartelesButton(event, ids);
    }
}