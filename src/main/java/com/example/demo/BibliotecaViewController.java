package com.example.demo;

public class BibliotecaViewController extends NavegacionVistas {

    // Recoge el id del usuario que inicia sesión en todas las vistas
    void mostrarId(int id) {

        txtId.setText(String.valueOf(id));
        idUsuario = Integer.parseInt(txtId.getText());
        //System.out.println("el valor recogido es : " + idUsuario);
    }
}