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

/**
 * Controlador para la vista de contenido, que reproduce videos y muestra información relacionada.
 */
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

    /**
     * Muestra la información del contenido y reproduce el video al recibir el id del contenido y del usuario.
     *
     * @param idContAux El ID del contenido.
     * @param id El ID del usuario.
     */
    void mostrarId(int idContAux, int id) {

        // Para el ID del usuario
        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        //System.out.println("El id del user en VistaContenido es : " + idUsuario);

        // Para el ID del contenido
        txtIdCont.setText(String.valueOf(idContAux));
        idCont = Integer.parseInt(txtIdCont.getText());
        //System.out.println("El id del contenido en VistaContenido es : " + idCont);

        // Obtenemos la información de la base de datos
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

    /**
     * Pausa la reproducción del video al hacer clic en la pantalla.
     *
     * @param event El evento del mouse.
     */
    @FXML
    void pauseVideo(MouseEvent event) { mediaPlayer.pause(); }

    /**
     * Inicia la reproducción del video al hacer clic en la pantalla.
     *
     * @param event El evento del mouse.
     */
    @FXML
    void playVideo(MouseEvent event) { mediaPlayer.play(); }

    /**
     * Reinicia la reproducción del video al hacer clic en la pantalla.
     *
     * @param event El evento del mouse.
     */
    @FXML
    void resetVideo(MouseEvent event) {

        // Cargamos de nuevo el video para resetearlo
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    /**
     * Controla la posición del slider al arrastrarlo.
     *
     * @param event El evento del mouse.
     */
    @FXML
    void sliderPressed(MouseEvent event) {

        // Mostramos en el slider la duración del video que estamos cargando
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    //------------------------------------------------------------------------
    /**
     * Muestra la vista principal al hacer clic en el botón correspondiente.
     *
     * @param event El evento de acción.
     */
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
