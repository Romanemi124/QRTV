package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    private Stage stage1;

    @FXML
    private Button btnMostrarViewRegistro;

    @FXML
    void showViewRegistro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registroView.fxml"));
        Parent root = loader.load();
        RegistroViewController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
    }

    public void setStage(Stage stage) {
        stage1 = stage;
    }
}
