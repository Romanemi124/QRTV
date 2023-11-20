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

public class RegistroViewController {

    @FXML
    private Button btnMostrarViewLogin2, btnMostrarViewPrincipal;

    @FXML
    private TextField titNombre, titUsername, titDate, titGender, titMail, titPassword;

    @FXML
    private Label txtError;

    int idUser = 0;
    Bd baseDatos = new Bd();
    HelloApplication main = new HelloApplication();

    //Dirección de las diferentes vistas que se podrán usar
    FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));

    @FXML
    void showViewPrincipal(ActionEvent event) {

        try {

            /* Inserto el nombre del usuario cuando se empieza la partida */
            if (!titNombre.getText().isEmpty() && !titUsername.getText().isEmpty() && !titDate.getText().isEmpty() && !titGender.getText().isEmpty() && !titMail.getText().isEmpty() && !titPassword.getText().isEmpty()) {

                baseDatos.guardarUsuario(titNombre, titUsername, titDate, titGender, titMail, titPassword);
                /* creamos objeto del Main para poder llamar al metodo start2 */
                main.cerrarPagina(event, btnMostrarViewPrincipal);
                mostrarPrincipalViewUser(event, loaderPrincipal, idUser);

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
    void showViewLogin2(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewLogin2);
            main.mostrarPagina(event, loaderLogin);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
