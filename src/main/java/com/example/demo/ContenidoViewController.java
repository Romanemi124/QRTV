package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import javafx.util.Duration;

import javafx.scene.control.Slider;

public class ContenidoViewController extends NavegacionVistas {

    @FXML
    private MediaView mediaView;

    @FXML
    private Label txtTitulo, txtValoracion, txtYear, txtDuration, txtSinopsis;

    @FXML
    private TextField txtId, txtIdCont;

    @FXML
    private Slider slider;

    int idCont = 0;
    String urlVideo = "";

    File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int idContAux, int id) {

        // Para el id del usuario
        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        System.out.println("El id del user en VistaContenido es : " + idUsuario);

        // Para el id del contenido
        txtIdCont.setText(String.valueOf(idContAux));
        idCont = Integer.parseInt(txtIdCont.getText());
        System.out.println("El id del contenido en VistaContenido es : " + idCont);

        // Sacamos la información de la base de datos
        urlVideo = baseDatos.getDatosImageCompl(idCont, txtTitulo, txtValoracion, txtYear, txtDuration, txtSinopsis);

        // Mostramos el video en la pantalla principal de la vista
        file = new File(urlVideo);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        // Para el slider
        mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
            slider.setValue(newValue.toSeconds());

            // En el caso de poner el tiempo restante del video
            txtYear.setText("Time : " + (int)slider.getValue() + " / " + (int)media.getDuration().toSeconds() + "s");
        }));
        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = media.getDuration();
            slider.setMax(totalDuration.toSeconds());

            // En el caso de poner el tiempo restante del video
            txtYear.setText("Time : 00 " + (int)media.getDuration().toSeconds() + "s");
        });
    }

    // ACCIÓN DE LOS BOTONES PARA EL CONTENIDO
    @FXML
    void pauseVideo(MouseEvent event) { mediaPlayer.pause(); }
    @FXML
    void playVideo(MouseEvent event) { mediaPlayer.play(); }
    @FXML
    void resetVideo(MouseEvent event) {
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }
    @FXML
    void sliderPressed(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    //------------------------------------------------------------------------
    // Accion button mostrar pantalla principal
    @FXML
    void showViewHome2(ActionEvent event) {
        try {
            mediaPlayer.pause();
            main.cerrarPagina(event, btnMostrarViewHome);
            mostrarPrincipalViewUser(event, loaderPrincipal, idUsuario);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
