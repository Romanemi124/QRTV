package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlAdminViewController extends NavegacionVistas {

    @FXML
    private Button btnAdd, btnAdd1, btnDelete, btnDelete1, btnModify, btnModify1, btnSearch, btnSearch1;

    @FXML
    private TextField txtIdU, txtNameU, txtBirthU, txtGenderU, txtSurnameU, txtMailU, txtPasswordU, txtIdAdminU;

    @FXML
    private TextField txtIdC, txtNameC, txtSinopsisC, txtTypeC, txtGenderC, txtDurationC, txtValorationC;
    @FXML
    private TextField txtYearC, txtIdActor1, txtIdActor2, txtDirectorC, txtUrlImagenC, txtUrlVideoC, txtUrlImagenPeC;

    @FXML
    private TextField txtId;

    @FXML
    private Label txtMessage;

    Bd baseDatos = new Bd();

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        System.out.println("el valor recogido en vista admin es : " + idUsuario);
    }

    @FXML
    void add(ActionEvent event) {

        int encontrado = 0;
        boolean encontradoCont = false;

        // Para añadir un usuario
        if (event.getSource() == btnAdd) {

            // Compruebo que los datos están rellenos antes de hacer la acción
            if (!txtNameU.getText().isEmpty() && !txtSurnameU.getText().isEmpty() && !txtBirthU.getText().isEmpty() && !txtGenderU.getText().isEmpty() && !txtMailU.getText().isEmpty() && !txtPasswordU.getText().isEmpty()) {

                encontrado = baseDatos.iniciarSesion(txtMailU, txtPasswordU, txtMessage);

                // Como ha encontrado el usuario no vuelve ha añadir
                if (encontrado == 1 || encontrado == 2) {
                    txtMessage.setText("User exists");
                } else {
                    baseDatos.guardarUsuario(txtNameU, txtSurnameU, txtBirthU, txtGenderU, txtMailU, txtPasswordU);

                    txtIdU.setText("");
                    txtNameU.setText("");
                    txtSurnameU.setText("");
                    txtBirthU.setText("");
                    txtGenderU.setText("");
                    txtMailU.setText("");
                    txtPasswordU.setText("");
                    txtMessage.setText("User add");
                }

            } else {
                txtMessage.setText("Faltan datos");
            }

            // Para añadir un contenido
        } else if (event.getSource() == btnAdd1) {

            // Compruebo que los datos están rellenos antes de hacer la acción
            if (!txtNameC.getText().isEmpty() && !txtSinopsisC.getText().isEmpty() && !txtTypeC.getText().isEmpty() && !txtGenderC.getText().isEmpty() && !txtDurationC.getText().isEmpty() && !txtValorationC.getText().isEmpty() && !txtYearC.getText().isEmpty() && !txtIdActor1.getText().isEmpty() && !txtIdActor2.getText().isEmpty() && !txtDirectorC.getText().isEmpty() && !txtUrlImagenC.getText().isEmpty() && !txtUrlVideoC.getText().isEmpty() && !txtUrlImagenPeC.getText().isEmpty()) {

                // Primero comprobamos que el contenido existe o no
                encontradoCont = baseDatos.buscarCont(txtNameC, txtUrlImagenC, txtUrlVideoC, txtUrlImagenPeC, txtMessage);

                // En este caso se podrá añadir un contenido nuevo
                if (!encontradoCont) {

                    baseDatos.guardarContent(txtNameC, txtSinopsisC, txtTypeC, txtGenderC, txtDurationC, txtValorationC, txtYearC, txtIdActor1, txtIdActor2, txtDirectorC, txtUrlImagenC, txtUrlVideoC, txtUrlImagenPeC, txtMessage);

                    /*
                    txtIdC.setText("");
                    txtNameC.setText("");
                    txtSinopsisC.setText("");
                    txtTypeC.setText("");
                    txtGenderC.setText("");
                    txtDurationC.setText("");
                    txtValorationC.setText("");
                    txtYearC.setText("");
                    txtIdActor1.setText("");
                    txtIdActor2.setText("");
                    txtDirectorC.setText("");
                    txtUrlImagenC.setText("");
                    txtUrlVideoC.setText("");
                    txtUrlImagenPeC.setText("");

                     */
                }

            } else {
                txtMessage.setText("Faltan datos");
            }
        }
    }

    @FXML
    void delete(ActionEvent event) {

        // Para eliminar un usuario
        if (event.getSource() == btnDelete) {

            int idUser = Integer.parseInt(txtIdU.getText());

            if (!txtIdU.getText().isEmpty()) {

                baseDatos.eliminarUsuario(idUser);

                txtIdU.setText("");
                txtNameU.setText("");
                txtSurnameU.setText("");
                txtBirthU.setText("");
                txtGenderU.setText("");
                txtMailU.setText("");
                txtPasswordU.setText("");
                txtMessage.setText("User delete");

            } else {
                txtMessage.setText("Please, insert user ID");
            }

            // Eliminar un contenido
        } else if (event.getSource() == btnDelete1) {

            int idCont = Integer.parseInt(txtIdC.getText());

            if (!txtIdC.getText().isEmpty()) {

                baseDatos.eliminarContenido(idCont);

                txtIdC.setText("");
                txtNameC.setText("");
                txtSinopsisC.setText("");
                txtTypeC.setText("");
                txtGenderC.setText("");
                txtDurationC.setText("");
                txtValorationC.setText("");
                txtYearC.setText("");
                txtIdActor1.setText("");
                txtIdActor2.setText("");
                txtDirectorC.setText("");
                txtUrlImagenC.setText("");
                txtUrlVideoC.setText("");
                txtUrlImagenPeC.setText("");
                txtMessage.setText("Content delete");

            } else {
                txtMessage.setText("Please, insert content ID");
            }
        }
    }

    @FXML
    void modify(ActionEvent event) {

        // Modificar un usuario
        if (event.getSource() == btnModify) {

            int idUser = Integer.parseInt(txtIdU.getText());

            // Compruebo que los datos están rellenos antes de hacer la acción
            if (!txtNameU.getText().isEmpty() && !txtSurnameU.getText().isEmpty() && !txtBirthU.getText().isEmpty() && !txtGenderU.getText().isEmpty() && !txtMailU.getText().isEmpty() && !txtPasswordU.getText().isEmpty()) {

                baseDatos.modificarUsuario(idUser, txtNameU, txtSurnameU, txtBirthU, txtGenderU, txtMailU, txtPasswordU, txtMessage);

                txtIdU.setText("");
                txtNameU.setText("");
                txtSurnameU.setText("");
                txtBirthU.setText("");
                txtGenderU.setText("");
                txtMailU.setText("");
                txtPasswordU.setText("");

            } else {
                txtMessage.setText("Faltan datos");
            }


            // Modificar un contenido
        } else if (event.getSource() == btnModify1) {

            int idCont = Integer.parseInt(txtIdC.getText());

            // Compruebo que los datos están rellenos antes de hacer la acción
            if (!txtNameC.getText().isEmpty() && !txtSinopsisC.getText().isEmpty() && !txtTypeC.getText().isEmpty() && !txtGenderC.getText().isEmpty() && !txtDurationC.getText().isEmpty() && !txtValorationC.getText().isEmpty() && !txtYearC.getText().isEmpty() && !txtIdActor1.getText().isEmpty() && !txtIdActor2.getText().isEmpty() && !txtDirectorC.getText().isEmpty() && !txtUrlImagenC.getText().isEmpty() && !txtUrlVideoC.getText().isEmpty() && !txtUrlImagenPeC.getText().isEmpty()) {

                baseDatos.modificarContent(idCont, txtNameC, txtSinopsisC, txtTypeC, txtGenderC, txtDurationC, txtValorationC, txtYearC, txtIdActor1, txtIdActor2, txtDirectorC, txtUrlImagenC, txtUrlVideoC, txtUrlImagenPeC, txtMessage);

                txtIdC.setText("");
                txtNameC.setText("");
                txtSinopsisC.setText("");
                txtTypeC.setText("");
                txtGenderC.setText("");
                txtDurationC.setText("");
                txtValorationC.setText("");
                txtYearC.setText("");
                txtIdActor1.setText("");
                txtIdActor2.setText("");
                txtDirectorC.setText("");
                txtUrlImagenC.setText("");
                txtUrlVideoC.setText("");
                txtUrlImagenPeC.setText("");

            } else {
                txtMessage.setText("Faltan datos");
            }
        }
    }

    @FXML
    void search(ActionEvent event) {

        // Buscar un id del usuario
        if (event.getSource() == btnSearch) {

            int idUser = Integer.parseInt(txtIdU.getText());

            txtNameU.setText("");
            txtSurnameU.setText("");
            txtBirthU.setText("");
            txtGenderU.setText("");
            txtMailU.setText("");
            txtPasswordU.setText("");
            txtMessage.setText("");

            // Muestra los datos del user sólo con el id
            if (!txtIdU.getText().isEmpty()) {
                baseDatos.mostrarUsuario(idUser, txtNameU, txtSurnameU, txtBirthU, txtGenderU, txtMailU, txtPasswordU);

                if (txtNameU.getText().isEmpty()) {
                    txtMessage.setText("User not found");
                }
            }

            // Buscar un contenido
        } else if (event.getSource() == btnSearch1) {

            int idCont = Integer.parseInt(txtIdC.getText());

            txtNameC.setText("");
            txtSinopsisC.setText("");
            txtTypeC.setText("");
            txtGenderC.setText("");
            txtDurationC.setText("");
            txtValorationC.setText("");
            txtYearC.setText("");
            txtIdActor1.setText("");
            txtIdActor2.setText("");
            txtDirectorC.setText("");
            txtUrlImagenC.setText("");
            txtUrlVideoC.setText("");
            txtUrlImagenPeC.setText("");
            txtMessage.setText("");

            // Muestra los datos del user sólo con el id
            if (!txtIdC.getText().isEmpty()) {
                baseDatos.mostrarContent(idCont, txtNameC, txtSinopsisC, txtTypeC, txtGenderC, txtDurationC, txtValorationC, txtYearC, txtIdActor1, txtIdActor2, txtDirectorC, txtUrlImagenC, txtUrlVideoC, txtUrlImagenPeC);

                if (txtNameC.getText().isEmpty()) {
                    txtMessage.setText("Content not found");
                }
            }
        }
    }
}



