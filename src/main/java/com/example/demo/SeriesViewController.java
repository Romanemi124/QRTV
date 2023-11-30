package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de series.
 */
public class SeriesViewController extends NavegacionVistas implements Initializable {

    /**
     * Recoge el ID del usuario que ha iniciado sesión en todas las vistas.
     *
     * @param id El ID del usuario que ha iniciado sesión.
     */
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        //System.out.println("el valor recogido es : " + idUsuario);
    }

    /**
     * Inicializa la vista de series.
     *
     * @param location  La ubicación relativa al código fuente de la vista.
     * @param resources Los recursos utilizados para localizar la vista.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Rellenamos nuestro array con las posiciones obtenidas de la BD
        ids = baseDatos.getOnlyIdsSeries();

        // Rellenamos de 0's las posiciones las cuales no tengan contenido previo
        for (int i = ids.size() - 1; i < 24; i++) {
            ids.add(0);
        }

        mostrarCarteles(ids);

        // Mostrar opciones de filtrado
        MenuFiltro.getItems().addAll(genero);
        // Cada vez que accionamos mostramos según filtro
        MenuFiltro.setOnAction(this::getGenero);
    }

    /**
     * Muestra las portadas de las series.
     *
     * @param event El evento de acción.
     */
    @FXML
    void showPortada(ActionEvent event) {

        mostrarCartelesButton(event, ids);
    }

    /**
     * Obtiene el género elegido y muestra las portadas de las series correspondientes.
     *
     * @param event El evento de acción.
     */
    public void getGenero(ActionEvent event) {

        // Recogemos el valor del filtro
        String generoAux = MenuFiltro.getValue();
        txtFiltro.setText(generoAux);

        // Rellenamos nuestro array con el genero solicitado
        ids = baseDatos.getOnlyIdsGenero("Serie", generoAux);

        // Rellenamos de 0's las posiciones las cuales no tengan contenido previo
        for (int i = ids.size() - 1; i < 24; i++) {
            ids.add(0);
        }

        mostrarCarteles(ids);
    }
}