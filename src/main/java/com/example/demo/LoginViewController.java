package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private Button btnMostrarViewRegistro, btnMostrarViewPrincipal2;

    @FXML
    private TextField titMail, titPassword;

    @FXML
    private Label txtError;

    int idUser = 0;

    // Clase para hacer uso de la base de datos
    Bd baseDatos = new Bd();
    HelloApplication main = new HelloApplication();

    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));
    FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("registroView.fxml"));
    FXMLLoader loaderAdminLogin = new FXMLLoader(getClass().getResource("loginAdminView.fxml"));

    @FXML
    void btnMostrarViewPrincipal2(ActionEvent event) {

        int encontrado = 0;

        try {

            /* Inserto el nombre del usuario cuando se empieza la partida */
            if (!titMail.getText().isEmpty() && !titPassword.getText().isEmpty()) {

                encontrado = baseDatos.iniciarSesion(titMail, titPassword, txtError);

                switch (encontrado) {
                    case 1:

                        // Guardamos el id del usuario para navegabilidad entre ventanas
                        idUser = baseDatos.getIdUser(titMail, titPassword);
                        System.out.println("Hola user : " + idUser);

                        // Mostramos la pantalla principal una vez comprobamos en la bd
                        main.cerrarPagina(event, btnMostrarViewPrincipal2);
                        mostrarPrincipalViewUser(event, loaderPrincipal, idUser);

                        break;
                    case 2:
                        // Guardamos el id del usuario para navegabilidad entre ventanas
                        idUser = baseDatos.getIdUser(titMail, titPassword);
                        System.out.println("Hola admin : " + idUser);

                        // Mostramos la pantalla principal una vez comprobamos en la bd
                        main.cerrarPagina(event, btnMostrarViewPrincipal2);
                        mostrarViewLoginAdmin(event, loaderAdminLogin, idUser);

                        break;
                    default:
                        txtError.setText("Usuario no encontrado");
                        break;
                }

            } else {
                txtError.setText("Faltan datos");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mostrarPrincipalViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            PrincipalViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista login : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mostrarViewLoginAdmin(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            LoginAdminViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista login Admin : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnMostrarViewRegistro(ActionEvent event) {
        try {

            // En caso de no tener cuenta nos dirige a la vista registro
            main.cerrarPagina(event, btnMostrarViewRegistro);
            main.mostrarPagina(event, loaderRegistro);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
