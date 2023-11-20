package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BuscadorViewController extends NavegacionVistas {

    @FXML
    private TextField txtBuscador;

    @FXML
    private Text txtError;

    int idAux = 0;

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        //System.out.println("el valor recogido es : " + idUsuario);
    }

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

    @FXML
    void mostrarBusqueda(ActionEvent event) {

        // En caso de que el buscador encuentre algo método activado
        main.cerrarPagina(event, btnPortada1);
        mostrarContenidoViewUser(event, loaderContenido, idAux, idUsuario);
    }
}