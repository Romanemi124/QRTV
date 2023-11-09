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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bd {
	
	private static Connection conexion = null;
	private static Statement sentenciaSQL = null;
	
	public Bd() {
		super();
	}

	//----------------------------------------------------------------------------------------------------------
	// CONEXIÓN CON LA BASE DE DATOS
	
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
	
	//----------------------------------------------------------------------------------------------------------
	// MÉTODOS USADOS PARA LA ADMINISTRACIÓN DEL USUARIO

	public boolean iniciarSesion(TextField email, TextField password, Label txtError, int idUser) {

		boolean encontrado = false;

		ResultSet result;
		String sql;

		String emailAux = email.getText();
		String passwordAux = password.getText();

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT userMail, userPassword FROM Usuarios where userMail = '" + emailAux + "' and userPassword = '" + passwordAux + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {
				System.out.println("User found");
				encontrado = true;
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR at login");
			txtError.setText("User or password incorrect");
		}

		return encontrado;
	}

	public int getIdUser(TextField email, TextField password) {

		int id = 0;

		ResultSet result;
		String sql;

		String emailAux = email.getText();
		String passwordAux = password.getText();

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT idUsuarios FROM Usuarios where userMail = '" + emailAux + "' and userPassword = '" + passwordAux + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {
				id = result.getInt("idUsuarios");
				System.out.println("El id del usuario en el sql es : " + id);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR at login");
		}

		return id;
	}

	// Añadir un usuario nuevo al registrarse
	public void guardarUsuario(TextField nombre, TextField apellidos, TextField date, TextField genero, TextField email, TextField password) {

		int result;
		String sql;

		String nombreAux = nombre.getText();
		String apellidosAux = apellidos.getText();
		String dateAux = date.getText();
		String generoAux = genero.getText();
		String emailAux = email.getText();
		String passwordAux = password.getText();

		try {

			// ANTES TENEMOS QUE VERIFICAR SI EL USUARIO EXISTE

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "INSERT INTO Usuarios (userName, userSurname, userBirth, userGender, userMail, userPassword) VALUES ('" + nombreAux + "', '" + apellidosAux + "', '" + dateAux + "', '" + generoAux + "', '" + emailAux + "', '" + passwordAux + "')";
			result = sentenciaSQL.executeUpdate(sql);

			// Se incrementa el valor de los personajes almacenados en el arrayList
			System.out.println("Usuario añadido");

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar el usuario");
		}
	}

	public void mostrarUsuario(int idUser, TextField userName, TextField userSurname, TextField userBirth, TextField userGender, TextField userMail, TextField userPassword) {

		ResultSet result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT * FROM Usuarios where idUsuarios = '" + idUser + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {

				// Insertamos en cada TextField los datos que sacamos de la BD
				userName.setText(result.getString("userName"));
				userSurname.setText(result.getString("userSurname"));
				userBirth.setText(result.getString("userBirth"));
				userGender.setText(result.getString("userGender"));
				userMail.setText(result.getString("userMail"));
				userPassword.setText(result.getString("userPassword"));
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar user");
		}
	}

	public void eliminarUsuario(int idUser) {

		ResultSet result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			System.out.println("el user a eliminar es : " + idUser);
			// Sentencia para añadir usuarios a la tabla
			sql = "DELETE FROM Usuarios where idUsuarios = '" + idUser + "'";
			result = sentenciaSQL.executeQuery(sql);

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al eliminar user");
		}
	}

	public void modificarUsuario(int idUser, TextField nombre, TextField apellidos, TextField date, TextField genero, TextField email, TextField password, Label txtError) {

		int result;
		String sql;

		String nombreAux = nombre.getText();
		String apellidosAux = apellidos.getText();
		String dateAux = date.getText();
		String generoAux = genero.getText();
		String emailAux = email.getText();
		String passwordAux = password.getText();

		System.out.println("datos : " + idUser + " " + nombreAux + " " + apellidosAux + " " + generoAux + " " + dateAux + " " + emailAux + " " + passwordAux);
		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "UPDATE Usuarios SET userName = '" + nombreAux + "', userSurname = '" + apellidosAux + "', userBirth = '" + dateAux + "', userGender = '" + generoAux + "', userMail = '" + emailAux + "', userPassword = '" + passwordAux + "' WHERE idUsuarios = '" + idUser + "'";
			result = sentenciaSQL.executeUpdate(sql);

			// Se incrementa el valor de los personajes almacenados en el arrayList
			System.out.println("Usuario modificado");
			txtError.setText("Updated user");

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al modificar el usuario");
		}
	}

	//----------------------------------------------------------------------------------------------------------
	// MÉTODOS USADOS PARA EL HOME DE LA APLICACIÓN

	public void getOnlyImage(int idContenido, ImageView img, String urlImagen) {

		File file;
		ResultSet result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT " + urlImagen + " FROM Contenido WHERE idContenido = '" + idContenido + "'";
			result = sentenciaSQL.executeQuery(sql);

			if(result.next()) {

				if (urlImagen == "urlImagen") {

					// Cuando recibimos el dato de la base de datos lo añadimos a la imagen
					file = new File(result.getString("urlImagen"));
					Image image = new Image(file.toURI().toString());
					img.setImage(image);
					System.out.println(file);

				} else if (urlImagen == "urlImagenPe") {

					// Cuando recibimos el dato de la base de datos lo añadimos a la imagen
					file = new File(result.getString("urlImagenPe"));
					Image image = new Image(file.toURI().toString());
					img.setImage(image);
					System.out.println(file);

				}
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar la imagen del home");
		}
	}

	public void getDatosImage(int idContenido, Label txtTitulo, Label txtDuracion) {

		ResultSet result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT nameCont, yearCont, duracionCont FROM Contenido WHERE idContenido = '" + idContenido + "'";
			result = sentenciaSQL.executeQuery(sql);

			if(result.next()) {

				// Insertamos los datos obtenidos en los textos de la vista
				txtTitulo.setText(result.getString("nameCont"));
				txtDuracion.setText(result.getString("yearCont") + " / " + result.getString("duracionCont"));
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar datos de la imagen del home");
		}
	}

}
