package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la vista de inicio de sesión del administrador.
 */
public class LoginAdminViewController extends NavegacionVistas {

    @FXML
    private Button btnLogin, btnLoginAdmin;

    @FXML
    private TextField txtCode, txtId;

    @FXML
    private Label txtError;

    /**
     * Recoge el ID del usuario que inicia sesión en todas las vistas.
     *
     * @param id El ID del usuario.
     */
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        System.out.println("el valor recogido es : " + idUsuario);
    }

    /**
     * Muestra la vista principal como un usuario normal.
     *
     * @param event El evento de acción.
     */
    @FXML
    void showSesion(ActionEvent event) {
        try {
            main.cerrarPagina(event, btnLogin);
            mostrarHome(event, loaderPrincipal, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista principal del usuario.
     *
     * @param event      El evento de acción.
     * @param fxmlLoader El FXMLLoader para cargar el archivo FXML.
     * @param idUser     El ID del usuario.
     */
    @FXML
    void mostrarHome(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            PrincipalViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista home : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    // Si quiere entrar como admin
    /**
     * Intenta iniciar sesión como administrador.
     *
     * @param event El evento de acción.
     */
    @FXML
    void showSesionAdmin(ActionEvent event) {

        boolean encontrado = false;

        try {

            // En esta sentencia sabemos el resultado de la búsqueda
            encontrado = baseDatos.iniciarSesionAdmin(txtCode, txtError, idUsuario);

            if (encontrado == true) {

                // Se muestra en el caso de que haya puesto su id
                main.cerrarPagina(event, btnLoginAdmin);
                mostrarControlAdmin(event, loaderAdminControl, idUsuario);
            } else {
                txtError.setText("Incorrect admin ID");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista de control del administrador.
     *
     * @param event      El evento de acción.
     * @param fxmlLoader El FXMLLoader para cargar el archivo FXML.
     * @param idUser     El ID del usuario administrador.
     */
    @FXML
    void mostrarControlAdmin(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            ControlAdminViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista control admin : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

