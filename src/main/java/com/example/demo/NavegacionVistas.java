package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NavegacionVistas {

    int idUsuario = 0;
    // Clase para hacer uso de la base de datos
    Bd baseDatos = new Bd();
    HelloApplication main = new HelloApplication();

    // Botones de la barra de navegación //cambiar privbate
    @FXML
    public Button btnPrueba, btnMostrarViewHome, btnMostrarViewPeliculas, btnMostrarViewSeries, btnMostrarViewBiblioteca, btnMostrarViewBuscador, btnMostrarViewAjustes;

    // Recoge el id del usuario que inicia sesión en todas las vistas
    @FXML
    public TextField txtId;
    //------------------------------------------------------------------------
    // Dirección de las diferentes vistas que se podrán usar

    FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("mainView.fxml"));
    FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("registroView.fxml"));
    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));
    FXMLLoader loaderAjustes = new FXMLLoader(getClass().getResource("ajustesView.fxml"));
    FXMLLoader loaderBiblioteca = new FXMLLoader(getClass().getResource("bibliotecaView.fxml"));
    FXMLLoader loaderBuscador = new FXMLLoader(getClass().getResource("buscadorView.fxml"));
    FXMLLoader loaderContenido = new FXMLLoader(getClass().getResource("contenidoView.fxml"));
    FXMLLoader loaderPeliculas = new FXMLLoader(getClass().getResource("peliculasView.fxml"));
    FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
    FXMLLoader loaderSeries = new FXMLLoader(getClass().getResource("seriesView.fxml"));

    //------------------------------------------------------------------------
    // Accion button mostrar pantalla principal
    @FXML
    void showViewHome(ActionEvent event) {
        try {
            main.cerrarPagina(event, btnMostrarViewHome);
            mostrarPrincipalViewUser(event, loaderPrincipal, idUsuario);

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

    //------------------------------------------------------------------------
    // Accion button mostrar pantalla peliculas
    @FXML
    void showViewPeliculas(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewPeliculas);
            mostrarPeliculasViewUser(event, loaderPeliculas, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mostrarPeliculasViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            PeliculasViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista peliculas : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    // Accion button mostrar pantalla series
    @FXML
    void showViewSeries(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewSeries);
            mostrarSeriesViewUser(event, loaderSeries, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mostrarSeriesViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            SeriesViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista series : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    // Accion button mostrar pantalla biblioteca
    @FXML
    void showViewBiblioteca(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewBiblioteca);
            mostrarBibliotecaViewUser(event, loaderBiblioteca, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mostrarBibliotecaViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            BibliotecaViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista biblioteca : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    // Accion button mostrar pantalla biblioteca
    @FXML
    void showViewBuscador(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewBuscador);
            mostrarBuscadorViewUser(event, loaderBuscador, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mostrarBuscadorViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            BuscadorViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista buscador : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    //Accion button mostrar pantalla principal
    @FXML
    void showViewAjustes(ActionEvent event) {

        try {

            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewAjustes);
            mostrarAjustesViewUser(event, loaderAjustes, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mostrarAjustesViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idUser) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            AjustesViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idUser);

            System.out.println("Usuario en vista ajustes : " + idUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    // Mostrar Vista Contenido

    @FXML
    void mostrarVideo(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnPrueba);
            main.mostrarPagina(event, loaderContenido);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
