package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador para la vista de ajustes de usuario.
 */
public class AjustesViewController extends NavegacionVistas {

    @FXML
    private TextField txtId, txtName, txtSurname, txtBirth, txtGender, txtMail, txtPassword;

    @FXML
    private Label txtError;

    @FXML
    private Button btnCerrarSesion;

    /**
     * Muestra el ID del usuario y carga los datos del usuario que ha iniciado sesión.
     *
     * @param id El ID del usuario.
     */
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        System.out.println("el valor recogido es : " + idUsuario);

        // Al iniciar la vista cargamos los datos del usuario que ha iniciado sesión
        baseDatos.mostrarUsuario(idUsuario, txtName, txtSurname, txtBirth, txtGender, txtMail, txtPassword);
    }

    /**
     * Actualiza los datos del usuario en la base de datos.
     *
     * @param event El evento de acción.
     */
    @FXML
    void updateDatos(ActionEvent event) {

        // Comprobamos que los TextField tienen contenido
        if (!txtName.getText().isEmpty() && !txtSurname.getText().isEmpty() && !txtBirth.getText().isEmpty() && !txtGender.getText().isEmpty() && !txtMail.getText().isEmpty() && !txtPassword.getText().isEmpty()) {

            // Cuando se hace la comprobación se actualizan los datos de la bd
            baseDatos.modificarUsuario(idUsuario, txtName, txtSurname, txtBirth, txtGender, txtMail, txtPassword, txtError);

        } else {
            txtError.setText("Faltan datos");
        }
    }

    /**
     * Cierra la sesión del usuario y redirige a la vista principal.
     *
     * @param event El evento de acción.
     */
    @FXML
    void cerrarSesion(ActionEvent event) {

        try {

            // En caso de no tener cuenta nos dirige a la vista registro
            main.cerrarPagina(event, btnCerrarSesion);
            main.mostrarPagina(event, loaderMain);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

