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

/**
 * Clase controladora para la vista de inicio de sesión.
 */
public class LoginViewController {

    @FXML
    private Button btnMostrarViewRegistro, btnMostrarViewPrincipal2;

    @FXML
    private TextField titMail, titPassword;

    @FXML
    private Label txtError;

    // ID de usuario
    int idUser = 0;

    // Clase para hacer uso de la base de datos
    Bd baseDatos = Bd.getInstance();
    HelloApplication main = HelloApplication.getInstance();

    // Instancias de FXMLLoader para diferentes vistas
    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));
    FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("registroView.fxml"));
    FXMLLoader loaderAdminLogin = new FXMLLoader(getClass().getResource("loginAdminView.fxml"));

    /**
     * Manejador de eventos para el botón que muestra la vista principal.
     *
     * @param event El evento de acción.
     */
    @FXML
    void btnMostrarViewPrincipal2(ActionEvent event) {

        int encontrado = 0;

        try {

            // Inserto el nombre del usuario cuando se empieza la partida
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

    /**
     * Método para mostrar la vista principal para un usuario.
     *
     * @param event      El evento de acción.
     * @param fxmlLoader El FXMLLoader para cargar el archivo FXML.
     * @param idUser     El ID del usuario.
     */
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

    /**
     * Método para mostrar la vista de inicio de sesión del administrador.
     *
     * @param event      El evento de acción.
     * @param fxmlLoader El FXMLLoader para cargar el archivo FXML.
     * @param idUser     El ID del usuario administrador.
     */
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

    /**
     * Manejador de eventos para el botón que muestra la vista de registro.
     *
     * @param event El evento de acción.
     */
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