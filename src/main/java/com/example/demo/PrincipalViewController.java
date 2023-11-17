package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.net.URL;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalViewController extends NavegacionVistas implements Initializable {

    int show = 0;

    int idAux = 0;
    ArrayList<Integer> ids = new ArrayList<>();

    @FXML
    private ImageView img1,img2, img3, img4, imgPort1, imgPort2, imgPort3, imgPort4, imgPort5;

    @FXML
    private Label txtTitulo, txtDuracion;

    @FXML
    private Button btnPortada1, btnPortada2, btnPortada3, btnPortada4, btnPortada5;

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        System.out.println("el valor recogido es : " + idUsuario);
    }

    public void translateAnimation(double duration, Node node, double width) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Insertamos las imágenes desde la base de datos para el slider
        idAux = baseDatos.getOnlyImage(1, img1, "urlImagen");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(2, img2, "urlImagen");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(3, img3, "urlImagen");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(4, img4, "urlImagen");
        ids.add(idAux);

        // Insertamos las imágenes desde la base de datos para las pequeñas
        idAux = baseDatos.getOnlyImage(12, imgPort1, "urlImagenPe");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(6, imgPort2, "urlImagenPe");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(13, imgPort3, "urlImagenPe");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(16, imgPort4, "urlImagenPe");
        ids.add(idAux);
        idAux = baseDatos.getOnlyImage(15, imgPort5, "urlImagenPe");
        ids.add(idAux);

        // Para la comprobación de los ids guardados
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("El id es : " + ids.get(i));
        }

        // Definimos las posiciones de todas las imágenes de la vista principal
        translateAnimation(0.5, img1, 0);
        translateAnimation(0.5, img2, 1185);
        translateAnimation(0.5, img3, 1185);
        translateAnimation(0.5, img4, 1185);

        // Insertamos el text de la primera imagen
        baseDatos.getDatosImage(1, txtTitulo, txtDuracion);
    }

    // MOSTRAR LAS PELÍCULAS PULSANDO EN BOTÓN
    @FXML
    void showPortada(ActionEvent event) {

        // Para cada botón nos mandará a la vista del contenido de la imagen
        if (event.getSource() == btnPortada1) {

            main.cerrarPagina(event, btnPortada1);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(4), idUsuario);

        } else if (event.getSource() == btnPortada2) {

            main.cerrarPagina(event, btnPortada2);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(5), idUsuario);

        } else if (event.getSource() == btnPortada3) {

            main.cerrarPagina(event, btnPortada3);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(6), idUsuario);

        } else if (event.getSource() == btnPortada4) {

            main.cerrarPagina(event, btnPortada4);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(7), idUsuario);

        } else if (event.getSource() == btnPortada5) {

            main.cerrarPagina(event, btnPortada5);
            mostrarContenidoViewUser(event, loaderContenido, ids.get(8), idUsuario);

        }
    }

    // ACCIÓN DE LOS BOTONES PARA LA GALERÍA DE PELÍCULAS
    @FXML
    void next(ActionEvent event) {

        if (show == 0) {
            translateAnimation(0.5, img2, -1185);
            baseDatos.getDatosImage(2, txtTitulo, txtDuracion);
            show++;
        } else if (show == 1) {
            translateAnimation(0.5, img3, -1185);
            baseDatos.getDatosImage(3, txtTitulo, txtDuracion);
            show++;
        } else if (show == 2) {
            translateAnimation(0.5, img4, -1185);
            baseDatos.getDatosImage(4, txtTitulo, txtDuracion);
            show++;
        } else {

            //Una vez se han mostrado todas las imágenes se vuelve a empezar
            translateAnimation(0.5, img2, 1185);
            translateAnimation(0.5, img3, 1185);
            translateAnimation(0.5, img4, 1185);

            baseDatos.getDatosImage(1, txtTitulo, txtDuracion);
            show = 0;
        }
    }
    @FXML
    void prev(ActionEvent event) {

        if (show == 1) {
            translateAnimation(0.5, img2, 1185);
            baseDatos.getDatosImage(1, txtTitulo, txtDuracion);
            show--;
        } else  if (show == 2) {
            translateAnimation(0.5, img3, 1185);
            baseDatos.getDatosImage(2, txtTitulo, txtDuracion);
            show--;
        } else  if (show == 3) {
            translateAnimation(0.5, img4, 1185);
            baseDatos.getDatosImage(3, txtTitulo, txtDuracion);
            show--;
        } else {
            translateAnimation(0.5, img2, -1185);
            translateAnimation(0.5, img3, -1185);
            translateAnimation(0.5, img4, -1185);

            baseDatos.getDatosImage(4, txtTitulo, txtDuracion);
            show = 3;
        }
    }
}