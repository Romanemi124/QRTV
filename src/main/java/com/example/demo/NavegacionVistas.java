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

/**
 * La clase NavegacionVistas representa el controlador de navegación para varias vistas
 * en una aplicación multimedia.
 *
 * Maneja la navegación entre diferentes pantallas, la autenticación de usuarios y
 * la visualización de imágenes y botones de contenido.
 */
public class NavegacionVistas {

    int idUsuario = 0;

    // Recoge el id del usuario que inicia sesión en todas las vistas
    @FXML
    public TextField txtId;

    // Clase para hacer uso de la base de datos
    Bd baseDatos = Bd.getInstance();
    HelloApplication main = HelloApplication.getInstance();

    // Para las vistas biblioteca, peliculas, series
    ArrayList<Integer> ids = new ArrayList<>();

    //------------------------------------------------------------------------

    // PARA LAS BIBLIOTECAS

    /**
     * Lista de vistas de imágenes para mostrar imágenes de contenido
     * en la biblioteca.
     */
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
    public ImageView[] listImage = new ImageView[25];

    /**
     * Lista de botones relacionados con las imágenes de contenido
     * en la biblioteca.
     */
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
    public Button[] listButton = new Button[25];

    /**
     * Cuadro de elección para filtrar contenido por género en la biblioteca.
     */
    @FXML
    ChoiceBox<String> MenuFiltro;
    String[] genero = {"Horror", "Action", "Science-Fiction", "Comedy", "Children", "History", "Drama", "Animated"};

    /**
     * Texto que indica el filtro seleccionado en la biblioteca.
     */
    @FXML
    Text txtFiltro;

    //------------------------------------------------------------------------

    /**
     * Botones de la barra de navegación para acceder a diferentes vistas.
     */
    @FXML
    public Button btnPrueba, btnMostrarViewHome, btnMostrarViewPeliculas, btnMostrarViewSeries, btnMostrarViewBiblioteca, btnMostrarViewBuscador, btnMostrarViewAjustes;
    public Button[] listButtonMenu = new Button[6];

    //------------------------------------------------------------------------
    /**
     * Cargadores de vistas para las diferentes pantallas que se pueden utilizar.
     */
    FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("mainView.fxml"));
    FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("principalView.fxml"));
    FXMLLoader loaderAjustes = new FXMLLoader(getClass().getResource("ajustesView.fxml"));
    FXMLLoader loaderBiblioteca = new FXMLLoader(getClass().getResource("bibliotecaView.fxml"));
    FXMLLoader loaderBuscador = new FXMLLoader(getClass().getResource("buscadorView.fxml"));
    FXMLLoader loaderContenido = new FXMLLoader(getClass().getResource("contenidoView.fxml"));
    FXMLLoader loaderPeliculas = new FXMLLoader(getClass().getResource("peliculasView.fxml"));
    FXMLLoader loaderSeries = new FXMLLoader(getClass().getResource("seriesView.fxml"));
    FXMLLoader loaderAdminControl = new FXMLLoader(getClass().getResource("controlAdminView.fxml"));
    public FXMLLoader[] listFxmlMenu = new FXMLLoader[6];

    //------------------------------------------------------------------------
    /**
     * Agrega los botones al array de botones para la biblioteca.
     */
    private void addArrayButton() {
        listButton[0] = btnPortada1; listButton[1] = btnPortada2; listButton[2] = btnPortada3;
        listButton[3] = btnPortada4; listButton[4] = btnPortada5; listButton[5] = btnPortada6;
        listButton[6] = btnPortada7; listButton[7] = btnPortada8; listButton[8] = btnPortada9;
        listButton[9] = btnPortada10; listButton[10] = btnPortada11; listButton[11] = btnPortada12;
        listButton[12] = btnPortada13; listButton[13] = btnPortada14; listButton[14] = btnPortada15;
        listButton[15] = btnPortada16; listButton[16] = btnPortada17; listButton[17] = btnPortada18;
        listButton[18] = btnPortada19; listButton[19] = btnPortada20; listButton[20] = btnPortada21;
        listButton[21] = btnPortada22; listButton[22] = btnPortada23; listButton[23] = btnPortada24;
        listButton[24] = btnPortada25;
    }

    /**
     * Agrega las imágenes al array de imágenes para la biblioteca.
     */
    private void addArrayImg() {
        listImage[0] = imgPort1; listImage[1] = imgPort2; listImage[2] = imgPort3; listImage[3] = imgPort4;
        listImage[4] = imgPort5; listImage[5] = imgPort6; listImage[6] = imgPort7; listImage[7] = imgPort8;
        listImage[8] = imgPort9; listImage[9] = imgPort10; listImage[10] = imgPort11; listImage[11] = imgPort12;
        listImage[12] = imgPort13; listImage[13] = imgPort14; listImage[14] = imgPort15; listImage[15] = imgPort16;
        listImage[16] = imgPort17; listImage[17] = imgPort18; listImage[18] = imgPort19; listImage[19] = imgPort20;
        listImage[20] = imgPort21;listImage[21] = imgPort22; listImage[22] = imgPort23; listImage[23] = imgPort24;
        listImage[24] = imgPort25;
    }

    /**
     * Agrega los botones y las vistas al array correspondiente del menú.
     */
    private void addArrayMenu() {

        // Botones del menu
        listButtonMenu[0] = btnMostrarViewHome; listButtonMenu[1] = btnMostrarViewPeliculas; listButtonMenu[2] = btnMostrarViewSeries;
        listButtonMenu[3] = btnMostrarViewBiblioteca; listButtonMenu[4] = btnMostrarViewBuscador; listButtonMenu[5] = btnMostrarViewAjustes;

        // Vistas del menu
        listFxmlMenu[0] = loaderPrincipal; listFxmlMenu[1] = loaderPeliculas; listFxmlMenu[2] = loaderSeries;
        listFxmlMenu[3] = loaderBiblioteca; listFxmlMenu[4] = loaderBuscador; listFxmlMenu[5] = loaderAjustes;
    }

    //------------------------------------------------------------------------

    /**
     * Muestra la vista correspondiente al botón del menú seleccionado.
     *
     * @param event Evento de acción que desencadena la función.
     */
    @FXML
    void showViewMenu(ActionEvent event) {

        // Cargamos los Arrays
        addArrayMenu();

        try {

            // Recorremos la lista de botones del array
            for (int i = 0; i < listButtonMenu.length; i++) {

                // Comprobamos caul es el botón que se ha presionado
                if (event.getSource() == listButtonMenu[i]) {

                    // Cerramos la vista actual para mostrar la siguiente
                    main.cerrarPagina(event, listButtonMenu[i]);
                    Parent root1 = listFxmlMenu[i].load();

                    // Dependiendo del botón que sea se cargará su controlador para acceder al método de pasar ID
                    if (listButtonMenu[i] == btnMostrarViewHome) {
                        PrincipalViewController pview = loaderPrincipal.getController();
                        pview.mostrarId(idUsuario);
                    } else if (listButtonMenu[i] == btnMostrarViewPeliculas) {
                        PeliculasViewController pview = loaderPeliculas.getController();
                        pview.mostrarId(idUsuario);
                    } else if (listButtonMenu[i] == btnMostrarViewSeries) {
                        SeriesViewController pview = loaderSeries.getController();
                        pview.mostrarId(idUsuario);
                    } else if (listButtonMenu[i] == btnMostrarViewBiblioteca) {
                        BibliotecaViewController pview = loaderBiblioteca.getController();
                        pview.mostrarId(idUsuario);
                    } else if (listButtonMenu[i] == btnMostrarViewBuscador) {
                        BuscadorViewController pview = loaderBuscador.getController();
                        pview.mostrarId(idUsuario);
                    } else if (listButtonMenu[i] == btnMostrarViewAjustes) {
                        AjustesViewController pview = loaderAjustes.getController();
                        pview.mostrarId(idUsuario);
                    }

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista principal del usuario con el ID proporcionado.
     *
     * @param event       Evento de acción que desencadena la función.
     * @param fxmlLoader  Cargador de la vista FXML.
     * @param idUser      ID del usuario para mostrar en la vista.
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

    //------------------------------------------------------------------------

    /**
     * Muestra la vista de contenido de video.
     *
     * @param event Evento de acción que desencadena la función.
     */
    @FXML
    void mostrarVideo(ActionEvent event) {
        try {
            // creamos objeto del Main para poder llamar al metodo start2
            main.cerrarPagina(event, btnPrueba);
            main.mostrarPagina(event, loaderContenido);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------

    /**
     * Muestra la vista de contenido para un usuario específico.
     *
     * @param event       Evento de acción que desencadena la función.
     * @param fxmlLoader  Cargador de la vista FXML.
     * @param idCont      ID del contenido a mostrar.
     * @param idUsuario   ID del usuario para pasar a la vista.
     */
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

    /**
     * Muestra los carteles en la vista de la biblioteca.
     *
     * @param ids Lista de IDs de contenidos a mostrar.
     */
    public void mostrarCarteles(ArrayList<Integer> ids) {

        addArrayButton();
        addArrayImg();

        // Habilitar o deshabilitar los botones que se encuentran dentro de la vista,
        // en cuanto detecte una imagen cargada ese botón estará habilitado, en caso contrario,
        // dicho botón se inhabilita y nos evitará problemas al querer pulsarlo y no detectar nada
        for (int i = 0; i < ids.size(); i++) {

            // Mostramos las imágenes gracias al array
            if (ids.get(i) != 0) {
                listButton[i].setDisable(false);
                baseDatos.setOnlyImage(ids.get(i), listImage[i]);
            } else {
                listButton[i].setDisable(true);
                listImage[i].imageProperty().set(null);
            }
        }
    }

    /**
     * Muestra la vista de contenido asociada al botón de la biblioteca seleccionado.
     *
     * @param event      Evento de acción que desencadena la función.
     * @param ids        Lista de IDs de contenidos asociados a los botones.
     */
    public void mostrarCartelesButton(ActionEvent event, ArrayList<Integer> ids) {

        addArrayButton();

        // Para cada botón nos mandará a la vista del contenido de la imagen
        for (int i = 0; i < ids.size(); i++) {

            if (event.getSource() == listButton[i]) {
                main.cerrarPagina(event, listButton[i]);
                mostrarContenidoViewUser(event, loaderContenido, ids.get(i), idUsuario);
            }
        }
    }
}