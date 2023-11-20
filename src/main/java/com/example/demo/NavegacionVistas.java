package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NavegacionVistas {

    int idUsuario = 0;
    // Clase para hacer uso de la base de datos
    Bd baseDatos = new Bd();
    HelloApplication main = new HelloApplication();

    // Para las vistas biblioteca, peliculas, series
    ArrayList<Integer> ids = new ArrayList<>();

    //------------------------------------------------------------------------

    // PARA LAS BIBLIOTECAS
    // Lista de todas las imágenes posibles
    @FXML
    ImageView imgPort1, imgPort2, imgPort3, imgPort4, imgPort5;
    @FXML
    ImageView imgPort6, imgPort7, imgPort8, imgPort9, imgPort10;
    @FXML
    ImageView imgPort11, imgPort12, imgPort13, imgPort14, imgPort15;
    @FXML
    ImageView imgPort16, imgPort17, imgPort18, imgPort19, imgPort20;
    @FXML
    ImageView imgPort21, imgPort22, imgPort23, imgPort24, imgPort25;

    // Lista de los botones relación imagen
    @FXML
    Button btnPortada1, btnPortada2, btnPortada3, btnPortada4, btnPortada5;
    @FXML
    Button btnPortada6, btnPortada7, btnPortada8, btnPortada9, btnPortada10;
    @FXML
    Button btnPortada11, btnPortada12, btnPortada13, btnPortada14, btnPortada15;
    @FXML
    Button btnPortada16, btnPortada17, btnPortada18, btnPortada19, btnPortada20;
    @FXML
    Button btnPortada21, btnPortada22, btnPortada23, btnPortada24, btnPortada25;

    @FXML
    ChoiceBox<String> MenuFiltro;
    String[] genero = {"Children", "Ciencia Ficcion", "Comedia", "Accion", "Terror", "Historica"};

    @FXML
    Text txtFiltro;

    //------------------------------------------------------------------------

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

    FXMLLoader loaderAdminControl = new FXMLLoader(getClass().getResource("controlAdminView.fxml"));

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

    //------------------------------------------------------------------------
    // Accion button mostrar contenido de cada imagen

    @FXML
    void mostrarContenidoViewUser(ActionEvent event, FXMLLoader fxmlLoader, int idCont, int idUsuario) {

        try {

            Parent root1 = (Parent) fxmlLoader.load();
            // Para pasar datos entre ventanas
            ContenidoViewController pview = fxmlLoader.getController();
            //idUser = pview.mostrarId(idUser);
            pview.mostrarId(idCont, idUsuario);

            //System.out.println("ID contenido : " + idCont);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------
    // Para las vistas donde se muestran todos los carteles
    public void mostrarCarteles(ArrayList<Integer> ids) {

        // Mostramos las imágenes gracias al array
        if (ids.get(0) != 0) {
            btnPortada1.setDisable(false);
            baseDatos.setOnlyImage(ids.get(0), imgPort1);
        } else {
            btnPortada1.setDisable(true);
            imgPort1.imageProperty().set(null);
        }
        if (ids.get(1) != 00) {
            btnPortada2.setDisable(false);
            baseDatos.setOnlyImage(ids.get(1), imgPort2);
        } else {
            btnPortada2.setDisable(true);
            imgPort2.imageProperty().set(null);
        }
        if (ids.get(2) != 0) {
            btnPortada3.setDisable(false);
            baseDatos.setOnlyImage(ids.get(2), imgPort3);
        } else {
            btnPortada3.setDisable(true);
            imgPort3.imageProperty().set(null);
        }
        if (ids.get(3) != 0) {
            btnPortada4.setDisable(false);
            baseDatos.setOnlyImage(ids.get(3), imgPort4);
        } else {
            btnPortada4.setDisable(true);
            imgPort4.imageProperty().set(null);
        }
        if (ids.get(4) != 0) {
            btnPortada5.setDisable(false);
            baseDatos.setOnlyImage(ids.get(4), imgPort5);
        } else {
            btnPortada5.setDisable(true);
            imgPort5.imageProperty().set(null);
        }
        if (ids.get(5) != 0) {
            btnPortada6.setDisable(false);
            baseDatos.setOnlyImage(ids.get(5), imgPort6);
        } else {
            btnPortada6.setDisable(true);
            imgPort6.imageProperty().set(null);
        }
        if (ids.get(6) != 0) {
            btnPortada7.setDisable(false);
            baseDatos.setOnlyImage(ids.get(6), imgPort7);
        } else {
            btnPortada7.setDisable(true);
            imgPort7.imageProperty().set(null);
        }
        if (ids.get(7) != 0) {
            btnPortada8.setDisable(false);
            baseDatos.setOnlyImage(ids.get(7), imgPort8);
        } else {
            btnPortada8.setDisable(true);
            imgPort8.imageProperty().set(null);
        }
        if (ids.get(8) != 0) {
            btnPortada9.setDisable(false);
            baseDatos.setOnlyImage(ids.get(8), imgPort9);
        } else {
            btnPortada9.setDisable(true);
            imgPort9.imageProperty().set(null);
        }
        if (ids.get(9) != 0) {
            btnPortada10.setDisable(false);
            baseDatos.setOnlyImage(ids.get(9), imgPort10);
        } else {
            btnPortada10.setDisable(true);
            imgPort10.imageProperty().set(null);
        }
        if (ids.get(10) != 0) {
            btnPortada11.setDisable(false);
            baseDatos.setOnlyImage(ids.get(10), imgPort11);
        } else {
            btnPortada11.setDisable(true);
            imgPort11.imageProperty().set(null);
        }
        if (ids.get(11) != 0) {
            btnPortada12.setDisable(false);
            baseDatos.setOnlyImage(ids.get(11), imgPort12);
        } else {
            btnPortada12.setDisable(true);
            imgPort12.imageProperty().set(null);
        }
        if (ids.get(12) != 0) {
            btnPortada13.setDisable(false);
            baseDatos.setOnlyImage(ids.get(12), imgPort13);
        } else {
            btnPortada13.setDisable(true);
            imgPort13.imageProperty().set(null);
        }
        if (ids.get(13) != 0) {
            btnPortada14.setDisable(false);
            baseDatos.setOnlyImage(ids.get(13), imgPort14);
        } else {
            btnPortada14.setDisable(true);
            imgPort14.imageProperty().set(null);
        }
        if (ids.get(14) != 0) {
            btnPortada15.setDisable(false);
            baseDatos.setOnlyImage(ids.get(14), imgPort15);
        } else {
            btnPortada15.setDisable(true);
            imgPort15.imageProperty().set(null);
        }
        if (ids.get(15) != 0) {
            btnPortada16.setDisable(false);
            baseDatos.setOnlyImage(ids.get(15), imgPort16);
        } else {
            btnPortada16.setDisable(true);
            imgPort16.imageProperty().set(null);
        }
        if (ids.get(16) != 0) {
            btnPortada17.setDisable(false);
            baseDatos.setOnlyImage(ids.get(16), imgPort17);
        } else {
            btnPortada17.setDisable(true);
            imgPort17.imageProperty().set(null);
        }
        if (ids.get(17) != 0) {
            btnPortada18.setDisable(false);
            baseDatos.setOnlyImage(ids.get(17), imgPort18);
        } else {
            btnPortada18.setDisable(true);
            imgPort18.imageProperty().set(null);
        }
        if (ids.get(18) != 0) {
            btnPortada19.setDisable(false);
            baseDatos.setOnlyImage(ids.get(18), imgPort19);
        } else {
            btnPortada19.setDisable(true);
            imgPort19.imageProperty().set(null);
        }
        if (ids.get(19) != 0) {
            btnPortada20.setDisable(false);
            baseDatos.setOnlyImage(ids.get(19), imgPort20);
        } else {
            btnPortada20.setDisable(true);
            imgPort20.imageProperty().set(null);
        }
        if (ids.get(20) != 0) {
            btnPortada21.setDisable(false);
            baseDatos.setOnlyImage(ids.get(20), imgPort21);
        } else {
            btnPortada21.setDisable(true);
            imgPort21.imageProperty().set(null);
        }
        if (ids.get(21) != 0) {
            btnPortada22.setDisable(false);
            baseDatos.setOnlyImage(ids.get(21), imgPort22);
        } else {
            btnPortada22.setDisable(true);
            imgPort22.imageProperty().set(null);
        }
        if (ids.get(22) != 0) {
            btnPortada23.setDisable(false);
            baseDatos.setOnlyImage(ids.get(22), imgPort23);
        } else {
            btnPortada23.setDisable(true);
            imgPort23.imageProperty().set(null);
        }
        if (ids.get(23) != 0) {
            btnPortada24.setDisable(false);
            baseDatos.setOnlyImage(ids.get(23), imgPort24);
        } else {
            btnPortada24.setDisable(true);
            imgPort24.imageProperty().set(null);
        }
        if (ids.get(24) != 0) {
            btnPortada25.setDisable(false);
            baseDatos.setOnlyImage(ids.get(24), imgPort25);
        } else {
            btnPortada25.setDisable(true);
            imgPort25.imageProperty().set(null);
        }
    }
    // Acciones de los botones
    public void mostrarCartelesButton(ActionEvent event, ArrayList<Integer> ids) {

        // Para cada botón nos mandará a la vista del contenido de la imagen
        /*
        En este if else lo que hacemos es habilitar o deshabilitar los botones que se encuentran dentro de la vista
        que hace uso de este método, en cuanto detecte una imagen cargada ese botón estará habilitado, en caso contrario,
        dicho botón se inhabilita y nos evitará problemas al querer pulsarlo y no detectar nada
         */
        if (event.getSource() == btnPortada1) {

            main.cerrarPagina(event, btnPortada1);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(0), idUsuario);

        } else if (event.getSource() == btnPortada2) {

            main.cerrarPagina(event, btnPortada2);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(1), idUsuario);

        } else if (event.getSource() == btnPortada3) {

            main.cerrarPagina(event, btnPortada3);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(2), idUsuario);

        } else if (event.getSource() == btnPortada4) {

            main.cerrarPagina(event, btnPortada4);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(3), idUsuario);

        } else if (event.getSource() == btnPortada5) {

            main.cerrarPagina(event, btnPortada5);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(4), idUsuario);

        } else if (event.getSource() == btnPortada6) {

            main.cerrarPagina(event, btnPortada6);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(5), idUsuario);

        } else if (event.getSource() == btnPortada7) {

            main.cerrarPagina(event, btnPortada7);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(6), idUsuario);

        } else if (event.getSource() == btnPortada8) {

            main.cerrarPagina(event, btnPortada8);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(7), idUsuario);

        } else if (event.getSource() == btnPortada9) {

            main.cerrarPagina(event, btnPortada9);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(8), idUsuario);

        } else if (event.getSource() == btnPortada10) {

            main.cerrarPagina(event, btnPortada10);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(9), idUsuario);

        } else if (event.getSource() == btnPortada11) {

            main.cerrarPagina(event, btnPortada11);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(10), idUsuario);

        } else if (event.getSource() == btnPortada12) {

            main.cerrarPagina(event, btnPortada12);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(11), idUsuario);

        } else if (event.getSource() == btnPortada13) {

            main.cerrarPagina(event, btnPortada13);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(12), idUsuario);

        } else if (event.getSource() == btnPortada14) {

            main.cerrarPagina(event, btnPortada14);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(13), idUsuario);

        } else if (event.getSource() == btnPortada15) {

            main.cerrarPagina(event, btnPortada15);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(14), idUsuario);

        } else if (event.getSource() == btnPortada16) {

            main.cerrarPagina(event, btnPortada16);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(15), idUsuario);

        } else if (event.getSource() == btnPortada17) {

            main.cerrarPagina(event, btnPortada17);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(16), idUsuario);

        } else if (event.getSource() == btnPortada18) {

            main.cerrarPagina(event, btnPortada18);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(17), idUsuario);

        } else if (event.getSource() == btnPortada19) {

            main.cerrarPagina(event, btnPortada19);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(18), idUsuario);

        } else if (event.getSource() == btnPortada20) {

            main.cerrarPagina(event, btnPortada20);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(19), idUsuario);

        } else if (event.getSource() == btnPortada21) {

            main.cerrarPagina(event, btnPortada21);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(20), idUsuario);

        } else if (event.getSource() == btnPortada22) {

            main.cerrarPagina(event, btnPortada22);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(21), idUsuario);

        } else if (event.getSource() == btnPortada23) {

            main.cerrarPagina(event, btnPortada23);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(22), idUsuario);

        } else if (event.getSource() == btnPortada24) {

            main.cerrarPagina(event, btnPortada24);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(23), idUsuario);

        } else if (event.getSource() == btnPortada25) {

            main.cerrarPagina(event, btnPortada25);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(24), idUsuario);

        }
    }
}
