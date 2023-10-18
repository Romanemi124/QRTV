package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ContenidoViewController implements Initializable {

    @FXML
    private Button btnMenu, btnPause, btnPlay, btnReset;

    @FXML
    private MediaView mediaView;

    @FXML
    private Label txtDuration, txtSinopsis;

    File file = new File("/Users/emilioroman/Desktop/QRTV/src/main/resources/com/example/demo/videos/prueba.mp4");
    private Media media;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        /* Botones de relleno */
        //mediaPlayer.play();
    }

    @FXML
    void pauseVideo(MouseEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void playVideo(MouseEvent event) { mediaPlayer.play(); }

    @FXML
    void resetVideo(MouseEvent event) {

    }
}
