package com.example.demo;

import javafx.scene.control.Label;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bd {
	
	private static Connection conexion = null;
	private static Statement sentenciaSQL = null;
	
	public Bd() {
		super();
	}

	/*----------------------------------------------------------------------------------------------------------*/
	
	/* Conectar a la base de datos */
	
	final void conectar() throws SQLException {
		
        try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/QR_TV",
			"root", "Geronimo20");
		    
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		}
	}
	
	/* Desconectarse */
	
	final void desconectar() throws SQLException {
		
		try {
			sentenciaSQL.close();
			conexion.close();
		} catch (SQLException ex) {
			System.out.println("Error");
		}
		
	}
	
	/*----------------------------------------------------------------------------------------------------------*/

}
