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

import java.util.ResourceBundle;

public class PrincipalViewController extends NavegacionVistas implements Initializable {

    int show = 0;
    @FXML
    private ImageView img1,img2, img3, img4, imgPort1, imgPort2, imgPort3, imgPort4, imgPort5;

    @FXML
    private Label txtTitulo, txtDuracion;

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
        baseDatos.getOnlyImage(1, img1, "urlImagen");
        baseDatos.getOnlyImage(2, img2, "urlImagen");
        baseDatos.getOnlyImage(3, img3, "urlImagen");
        baseDatos.getOnlyImage(4, img4, "urlImagen");

        // Insertamos las imágenes desde la base de datos para las pequeñas
        baseDatos.getOnlyImage(5, imgPort1, "urlImagenPe");
        baseDatos.getOnlyImage(6, imgPort2, "urlImagenPe");
        baseDatos.getOnlyImage(7, imgPort3, "urlImagenPe");
        baseDatos.getOnlyImage(8, imgPort4, "urlImagenPe");
        baseDatos.getOnlyImage(9, imgPort5, "urlImagenPe");

        // Definimos las posiciones de todas las imágenes de la vista principal
        translateAnimation(0.5, img1, 0);
        translateAnimation(0.5, img2, 1185);
        translateAnimation(0.5, img3, 1185);
        translateAnimation(0.5, img4, 1185);

        // Insertamos el text de la primera imagen
        baseDatos.getDatosImage(1, txtTitulo, txtDuracion);
    }

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