package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class AjustesViewController {

    @FXML
    private Button btnPrueba, btnMostrarViewHome, btnMostrarViewPeliculas, btnMostrarViewSeries;

    @FXML
    private Button btnMostrarViewBiblioteca, btnMostrarViewBuscador, btnMostrarViewAjustes;

    HelloApplication main = new HelloApplication();

    //Dirección de las diferentes vistas que se podrán usar
    FXMLLoader loaderContenido = new FXMLLoader(getClass().getResource("contenidoView.fxml"));
    FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("principalView.fxml"));
    FXMLLoader loaderPeliculas = new FXMLLoader(getClass().getResource("peliculasView.fxml"));
    FXMLLoader loaderSeries = new FXMLLoader(getClass().getResource("seriesView.fxml"));
    FXMLLoader loaderBiblioteca = new FXMLLoader(getClass().getResource("bibliotecaView.fxml"));
    FXMLLoader loaderBuscador = new FXMLLoader(getClass().getResource("buscadorView.fxml"));
    FXMLLoader loaderAjustes = new FXMLLoader(getClass().getResource("ajustesView.fxml"));

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

    @FXML
    void showViewAjustes(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewAjustes);
            main.mostrarPagina(event, loaderAjustes);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewBiblioteca(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewBiblioteca);
            main.mostrarPagina(event, loaderBiblioteca);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewBuscador(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewBuscador);
            main.mostrarPagina(event, loaderBuscador);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewHome(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewHome);
            main.mostrarPagina(event, loaderHome);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewPeliculas(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewPeliculas);
            main.mostrarPagina(event, loaderPeliculas);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showViewSeries(ActionEvent event) {
        try {
            /* creamos objeto del Main para poder llamar al metodo start2 */
            main.cerrarPagina(event, btnMostrarViewSeries);
            main.mostrarPagina(event, loaderSeries);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}