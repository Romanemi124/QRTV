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

public class LoginAdminViewController extends NavegacionVistas {

    @FXML
    private Button btnLogin, btnLoginAdmin;

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

    // En caso de que no quiero iniciar sesión como admin podrá entrar como usuario normal
    @FXML
    void showSesion(ActionEvent event) {
        try {
            main.cerrarPagina(event, btnLogin);
            mostrarHome(event, loaderPrincipal, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
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

