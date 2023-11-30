package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controlador para la vista de búsqueda.
 */
public class BuscadorViewController extends NavegacionVistas {

    @FXML
    private TextField txtBuscador;

    @FXML
    private Text txtError;

    int idAux = 0;

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
     * Realiza la búsqueda de contenido al hacer clic en el botón de búsqueda.
     *
     * @param event El evento de acción.
     */
    @FXML
    void buscar(ActionEvent event) {

        idAux = baseDatos.getOnlyIdBuscador(txtBuscador);

        // En caso de que no encuentre nada muestra mensaje
        if(idAux != 0) {

            btnPortada1.setDisable(false);
            baseDatos.setOnlyImage(idAux, imgPort1);

            txtError.setText("");

        } else {

            btnPortada1.setDisable(true);
            imgPort1.imageProperty().set(null);

            // Indicamos que no se ha encontrado nada
            txtError.setText("Not found");
        }
    }

    /**
     * Muestra el resultado de la búsqueda al hacer clic en el botón de visualización.
     *
     * @param event El evento de acción.
     */
    @FXML
    void mostrarBusqueda(ActionEvent event) {

        // En caso de que el buscador encuentre algo método activado
        main.cerrarPagina(event, btnPortada1);
        mostrarContenidoViewUser(event, loaderContenido, idAux, idUsuario);
    }
}