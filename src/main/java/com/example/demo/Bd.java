package com.example.demo;

import javafx.scene.control.Label;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

/**
 * Esta clase representa la gestión de la base de datos para la aplicación.
 * Proporciona métodos para conectar con la base de datos, gestionar sesiones de usuario
 * y realizar diversas operaciones relacionadas con usuarios y contenido.
 *
 * <p> La clase sigue el patrón Singleton para garantizar que se utilice una única instancia
 * en toda la aplicación.
 */
public class Bd {

	// Variables para la conexión y declaración SQL
	private static Connection conexion = null;
	private static Statement sentenciaSQL = null;

	// Instancia única de la clase (patrón Singleton)
	private static final Bd instance = new Bd();

	/**
	 * Constructor privado para evitar instanciación externa.
	 */
	public Bd() {
		super();
	}

	/**
	 * Método para obtener la instancia única de la clase.
	 *
	 * @return La única instancia de la clase Bd
	 */
	public static Bd getInstance() {
		return instance;
	}

	//----------------------------------------------------------------------------------------------------------
	// CONEXIÓN CON LA BASE DE DATOS

	/**
	 * Establece una conexión con la base de datos.
	 *
	 * @throws SQLException si hay un error al conectar con la base de datos
	 */
	final void conectar() throws SQLException {
		
        try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/QR_TV", "root", "Geronimo20");
		    
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		}
	}

	/**
	 * Desconecta de la base de datos.
	 *
	 * @throws SQLException si hay un error al desconectar de la base de datos
	 */
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

	/**
	 * Intenta iniciar sesión de un usuario con el correo electrónico y la contraseña proporcionados.
	 *
	 * @param email    El correo electrónico del usuario
	 * @param password La contraseña del usuario
	 * @param txtError El etiqueta para mostrar mensajes de error
	 * @return Un entero que representa el resultado del inicio de sesión
	 */
	public int iniciarSesion(TextField email, TextField password, Label txtError) {

		int encontrado = 0;

		ResultSet result;
		String sql;

		String emailAux = email.getText();
		String passwordAux = password.getText();

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT userMail, userPassword, isAdmin FROM Usuarios where userMail = '" + emailAux + "' and userPassword = '" + passwordAux + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {

				// Mostramos el id del usuario para comprobar funcionamiento
				System.out.println("Admin? : " + result.getString("isAdmin"));

				// Solo lo dará como encontrado en el caso de un usuario normal
				if (result.getString("isAdmin") == null) {
					System.out.println("User found");
					encontrado = 1;
				} else {

					// En caso de que sea admin
					encontrado = 2;
				}
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR at login");
			txtError.setText("User or password incorrect");
		}

		return encontrado;
	}

	/**
	 * Busca un contenido en la base de datos.
	 *
	 * @param nombre      El nombre del contenido
	 * @param txtUrlImagenC URL de la imagen del contenido
	 * @param txtUrlVideoC URL del video del contenido
	 * @param txtUrlImagenPeC URL de la imagen pequeña del contenido
	 * @param txtError    El etiqueta para mostrar mensajes de error
	 * @return `true` si se encuentra el contenido, `false` en caso contrario
	 */
	public boolean buscarCont(TextField nombre, TextField txtUrlImagenC, TextField txtUrlVideoC, TextField txtUrlImagenPeC, Label txtError) {

		boolean encontrado = false;

		ResultSet result;
		String sql;

		String nombreAux = nombre.getText();
		String imagenAux = txtUrlImagenC.getText();
		String videoAux = txtUrlVideoC.getText();
		String imagenPeAux = txtUrlImagenPeC.getText();

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT nameCont, urlImagen, urlVideo, urlImagenPe FROM Contenido WHERE nameCont = '" + nombreAux + "' AND urlImagen = '" + imagenAux + "' AND urlVideo = '" + videoAux + "' AND urlImagenPe = '" + imagenPeAux + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {

				// Mostramos el id del usuario para comprobar funcionamiento
				System.out.println("Nombre encontrado : " + result.getString("nameCont"));

				txtError.setText("Content exists");
				encontrado = true;
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR at buscar contenido");
			txtError.setText("ERROR to find a content");
		}

		return encontrado;
	}

	/**
	 * Intenta iniciar sesión como administrador con el código proporcionado.
	 *
	 * @param code      El código de administrador
	 * @param txtError  La etiqueta para mostrar mensajes de error
	 * @param idUser    El identificador del usuario
	 * @return true si se inicia sesión como administrador exitosamente, false en caso contrario
	 */
	public boolean iniciarSesionAdmin(TextField code, Label txtError, int idUser) {

		boolean encontrado = false;

		ResultSet result;
		String sql;

		String codeAux = code.getText();

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT isAdmin FROM Usuarios where idUsuarios = '" + idUser + "' and isAdmin = '" + codeAux + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {

				// En caso de coincidir datos devuelve el encontrado
				encontrado = true;
				System.out.println("Admin control? : " + encontrado);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR at login");
			txtError.setText("User or password incorrect");
		}

		return encontrado;
	}

	/**
	 * Obtiene el identificador de un usuario a partir del correo electrónico y la contraseña proporcionados.
	 *
	 * @param email     El correo electrónico del usuario
	 * @param password  La contraseña del usuario
	 * @return El identificador del usuario, o 0 si no se encuentra el usuario con las credenciales dadas.
	 */
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
			System.out.println("ERROR al obtener el id del usuario");
		}

		return id;
	}

	/**
	 * Guarda un nuevo usuario en la base de datos con la información proporcionada.
	 *
	 * @param nombre     El nombre del usuario.
	 * @param apellidos  Los apellidos del usuario.
	 * @param date       La fecha de nacimiento del usuario en formato string.
	 * @param genero     El género del usuario.
	 * @param email      El correo electrónico del usuario.
	 * @param password   La contraseña del usuario.
	 */
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

	/**
	 * Guarda un nuevo contenido en la base de datos con la información proporcionada.
	 *
	 * @param txtNameC         El nombre del contenido.
	 * @param txtSinopsisC     La sinopsis del contenido.
	 * @param txtTypeC         El tipo del contenido.
	 * @param txtGenderC       El género del contenido.
	 * @param txtDurationC     La duración del contenido.
	 * @param txtValorationC   La valoración del contenido.
	 * @param txtYearC         El año de lanzamiento del contenido.
	 * @param txtIdActor1      El ID del primer actor asociado al contenido.
	 * @param txtIdActor2      El ID del segundo actor asociado al contenido.
	 * @param txtDirectorC     El director del contenido.
	 * @param txtUrlImagenC    La URL de la imagen del contenido.
	 * @param txtUrlVideoC     La URL del video del contenido.
	 * @param txtUrlImagenPeC  La URL de la imagen pequeña del contenido.
	 * @param txtError         La etiqueta donde se mostrará un mensaje de error o confirmación.
	 */
	public void guardarContent(TextField txtNameC, TextField txtSinopsisC, TextField txtTypeC, TextField txtGenderC, TextField txtDurationC, TextField txtValorationC, TextField txtYearC, TextField txtIdActor1, TextField txtIdActor2, TextField txtDirectorC, TextField txtUrlImagenC, TextField txtUrlVideoC, TextField txtUrlImagenPeC, Label txtError) {

		int result;
		String sql;

		String nombreAux = txtNameC.getText();
		String sinopsisAux = txtSinopsisC.getText();
		String typeAux = txtTypeC.getText();
		String genderAux = txtGenderC.getText();
		String durationAux = txtDurationC.getText();
		String valorationAux = txtValorationC.getText();
		String yearAux = txtYearC.getText();
		String idA1Aux = txtIdActor1.getText();
		String idA2Aux = txtIdActor2.getText();
		String directorAux = txtDirectorC.getText();
		String imageAux = txtUrlImagenC.getText();
		String videoAux = txtUrlVideoC.getText();
		String imagePeAux = txtUrlImagenPeC.getText();

		try {

			// ANTES TENEMOS QUE VERIFICAR SI EL USUARIO EXISTE

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "INSERT INTO Contenido (nameCont, sinopsisCont, tipoCont, generoCont, duracionCont, valoracionCont, yearCont, idActor1, idActor2, directorCont, urlImagen, urlVideo, urlImagenPe) VALUES ('" + nombreAux + "', '" + sinopsisAux + "', '" + typeAux + "', '" + genderAux + "', '" + durationAux + "', '" + valorationAux + "', '" + yearAux + "', '" + idA1Aux + "', '" + idA2Aux + "', '" + directorAux + "', '" + imageAux + "', '" + videoAux + "', '" + imagePeAux + "')";
			result = sentenciaSQL.executeUpdate(sql);

			// Se incrementa el valor de los personajes almacenados en el arrayList
			System.out.println("Contenido añadido");
			txtError.setText("Content add");

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar un contenido");
			txtError.setText("ERROR at add a content");
		}
	}

	/**
	 * Muestra la información del usuario correspondiente al ID proporcionado en los campos de texto especificados.
	 *
	 * @param idUser         El ID del usuario cuya información se desea mostrar.
	 * @param userName       El campo de texto para mostrar el nombre del usuario.
	 * @param userSurname    El campo de texto para mostrar los apellidos del usuario.
	 * @param userBirth      El campo de texto para mostrar la fecha de nacimiento del usuario.
	 * @param userGender     El campo de texto para mostrar el género del usuario.
	 * @param userMail       El campo de texto para mostrar el correo electrónico del usuario.
	 * @param userPassword   El campo de texto para mostrar la contraseña del usuario.
	 */
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

	/**
	 * Muestra la información del contenido correspondiente al ID proporcionado en los campos de texto especificados.
	 *
	 * @param idCont           El ID del contenido cuya información se desea mostrar.
	 * @param txtNameC         El campo de texto para mostrar el nombre del contenido.
	 * @param txtSinopsisC     El campo de texto para mostrar la sinopsis del contenido.
	 * @param txtTypeC         El campo de texto para mostrar el tipo del contenido.
	 * @param txtGenderC       El campo de texto para mostrar el género del contenido.
	 * @param txtDurationC     El campo de texto para mostrar la duración del contenido.
	 * @param txtValorationC   El campo de texto para mostrar la valoración del contenido.
	 * @param txtYearC         El campo de texto para mostrar el año del contenido.
	 * @param txtIdActor1      El campo de texto para mostrar el ID del primer actor del contenido.
	 * @param txtIdActor2      El campo de texto para mostrar el ID del segundo actor del contenido.
	 * @param txtDirectorC     El campo de texto para mostrar el director del contenido.
	 * @param txtUrlImagenC    El campo de texto para mostrar la URL de la imagen del contenido.
	 * @param txtUrlVideoC     El campo de texto para mostrar la URL del video del contenido.
	 * @param txtUrlImagenPeC  El campo de texto para mostrar la URL de la imagen pequeña del contenido.
	 */
	public void mostrarContent(int idCont, TextField txtNameC, TextField txtSinopsisC, TextField txtTypeC, TextField txtGenderC, TextField txtDurationC, TextField txtValorationC, TextField txtYearC, TextField txtIdActor1, TextField txtIdActor2, TextField txtDirectorC, TextField txtUrlImagenC, TextField txtUrlVideoC, TextField txtUrlImagenPeC) {

		ResultSet result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "SELECT * FROM Contenido where idContenido = '" + idCont + "'";
			result = sentenciaSQL.executeQuery(sql);

			// Siempre se ejecuta cada vez que encuentre un dato buscado en la secuencia
			if(result.next()) {

				// Insertamos en cada TextField los datos que sacamos de la BD
				txtNameC.setText(result.getString("nameCont"));
				txtSinopsisC.setText(result.getString("sinopsisCont"));
				txtTypeC.setText(result.getString("tipoCont"));
				txtGenderC.setText(result.getString("generoCont"));
				txtDurationC.setText(result.getString("duracionCont"));
				txtValorationC.setText(result.getString("valoracionCont"));
				txtYearC.setText(result.getString("yearCont"));
				txtIdActor1.setText(result.getString("idActor1"));
				txtIdActor2.setText(result.getString("idActor2"));
				txtDirectorC.setText(result.getString("directorCont"));
				txtUrlImagenC.setText(result.getString("urlImagen"));
				txtUrlVideoC.setText(result.getString("urlVideo"));
				txtUrlImagenPeC.setText(result.getString("urlImagenPe"));
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar contenido");
		}
	}

	/**
	 * Elimina un usuario de la base de datos con el ID proporcionado.
	 *
	 * @param idUser El ID del usuario que se desea eliminar.
	 */
	public void eliminarUsuario(int idUser) {

		int result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			System.out.println("el user a eliminar es : " + idUser);
			// Sentencia para añadir usuarios a la tabla
			sql = "DELETE FROM Usuarios where idUsuarios = '" + idUser + "'";
			result = sentenciaSQL.executeUpdate(sql);

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al eliminar user");
		}
	}

	/**
	 * Elimina un contenido de la base de datos con el ID proporcionado.
	 *
	 * @param idCont El ID del contenido que se desea eliminar.
	 */
	public void eliminarContenido(int idCont) {

		int result;
		String sql;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			System.out.println("el contenido a eliminar es : " + idCont);
			// Sentencia para añadir usuarios a la tabla
			sql = "DELETE FROM Contenido where idContenido = '" + idCont + "'";
			result = sentenciaSQL.executeUpdate(sql);

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al eliminar contenido");
		}
	}

	/**
	 * Modifica un usuario en la base de datos con la información proporcionada.
	 *
	 * @param idUser   El ID del usuario que se desea modificar.
	 * @param nombre   El nuevo nombre del usuario.
	 * @param apellidos Los nuevos apellidos del usuario.
	 * @param date     La nueva fecha de nacimiento del usuario.
	 * @param genero   El nuevo género del usuario.
	 * @param email    El nuevo correo electrónico del usuario.
	 * @param password La nueva contraseña del usuario.
	 * @param txtError Etiqueta donde se mostrará un mensaje en caso de error.
	 */
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

	/**
	 * Modifica la información de un contenido en la base de datos con la información proporcionada.
	 *
	 * @param idCont       El ID del contenido que se desea modificar.
	 * @param txtNameC     El nuevo nombre del contenido.
	 * @param txtSinopsisC La nueva sinopsis del contenido.
	 * @param txtTypeC     El nuevo tipo del contenido.
	 * @param txtGenderC   El nuevo género del contenido.
	 * @param txtDurationC La nueva duración del contenido.
	 * @param txtValorationC La nueva valoración del contenido.
	 * @param txtYearC     El nuevo año del contenido.
	 * @param txtIdActor1  El nuevo ID del primer actor asociado al contenido.
	 * @param txtIdActor2  El nuevo ID del segundo actor asociado al contenido.
	 * @param txtDirectorC El nuevo director del contenido.
	 * @param txtUrlImagenC La nueva URL de la imagen principal del contenido.
	 * @param txtUrlVideoC La nueva URL del video asociado al contenido.
	 * @param txtUrlImagenPeC La nueva URL de la imagen pequeña del contenido.
	 * @param txtError     Etiqueta donde se mostrará un mensaje en caso de error.
	 */
	public void modificarContent(int idCont, TextField txtNameC, TextField txtSinopsisC, TextField txtTypeC, TextField txtGenderC, TextField txtDurationC, TextField txtValorationC, TextField txtYearC, TextField txtIdActor1, TextField txtIdActor2, TextField txtDirectorC, TextField txtUrlImagenC, TextField txtUrlVideoC, TextField txtUrlImagenPeC, Label txtError) {

		int result;
		String sql;

		String nombreAux = txtNameC.getText();
		String sinopsisAux = txtSinopsisC.getText();
		String typeAux = txtTypeC.getText();
		String genderAux = txtGenderC.getText();
		String durationAux = txtDurationC.getText();
		String valorationAux = txtValorationC.getText();
		String yearAux = txtYearC.getText();
		String idA1Aux = txtIdActor1.getText();
		String idA2Aux = txtIdActor2.getText();
		String directorAux = txtDirectorC.getText();
		String imageAux = txtUrlImagenC.getText();
		String videoAux = txtUrlVideoC.getText();
		String imagePeAux = txtUrlImagenPeC.getText();

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sentencia para añadir usuarios a la tabla
			sql = "UPDATE Contenido SET nameCont = '" + nombreAux + "', sinopsisCont = '" + sinopsisAux + "', tipoCont = '" + typeAux + "', generoCont = '" + genderAux + "', duracionCont = '" + durationAux + "', valoracionCont = '" + valorationAux + "', yearCont = '" + yearAux + "', idActor1 = '" + idA1Aux + "', idActor2 = '" + idA2Aux + "', directorCont = '" + directorAux + "', urlImagen = '" + imageAux + "', urlVideo = '" + videoAux + "', urlImagenPe = '" + imagePeAux + "' WHERE idContenido = '" + idCont + "'";
			result = sentenciaSQL.executeUpdate(sql);

			// Se incrementa el valor de los personajes almacenados en el arrayList
			System.out.println("Contenido modificado");
			txtError.setText("Updated content");

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al modificar el contenido");
		}
	}

	//----------------------------------------------------------------------------------------------------------
	// MÉTODOS USADOS PARA EL HOME DE LA APLICACIÓN

	/**
	 * Obtiene la imagen asociada a un contenido específico y la muestra en un ImageView.
	 *
	 * @param idContenido El ID del contenido del cual se desea obtener la imagen.
	 * @param img         El ImageView donde se mostrará la imagen.
	 * @param urlImagen   El tipo de imagen a obtener ("urlImagen" o "urlImagenPe").
	 * @return El ID del contenido del cual se obtuvo la imagen.
	 */
	public int getOnlyImage(int idContenido, ImageView img, String urlImagen) {

		File file;
		ResultSet result;
		String sql;

		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT idContenido, " + urlImagen + " FROM Contenido WHERE idContenido = '" + idContenido + "'";
			result = sentenciaSQL.executeQuery(sql);

			if(result.next()) {

				if (urlImagen == "urlImagen") {

					// Cuando recibimos el dato de la base de datos lo añadimos a la imagen
					file = new File(result.getString("urlImagen"));
					Image image = new Image(file.toURI().toString());
					img.setImage(image);
					System.out.println(file);

					idCont = result.getInt("idContenido");

				} else if (urlImagen == "urlImagenPe") {

					// Cuando recibimos el dato de la base de datos lo añadimos a la imagen
					file = new File(result.getString("urlImagenPe"));
					Image image = new Image(file.toURI().toString());
					img.setImage(image);
					System.out.println(file);

					idCont = result.getInt("idContenido");
				}
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar la imagen del home");
		}

		return idCont;
	}

	/**
	 * Establece la imagen asociada a un contenido específico en un ImageView.
	 *
	 * @param idContenido El ID del contenido del cual se desea establecer la imagen.
	 * @param img         El ImageView donde se mostrará la imagen.
	 */
	public void setOnlyImage(int idContenido, ImageView img) {

		File file;
		ResultSet result;
		String sql;

		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT idContenido, urlImagenPe FROM Contenido WHERE idContenido = '" + idContenido + "'";
			result = sentenciaSQL.executeQuery(sql);

			if(result.next()) {

				// Cuando recibimos el dato de la base de datos lo añadimos a la imagen
				file = new File(result.getString("urlImagenPe"));
				Image image = new Image(file.toURI().toString());
				img.setImage(image);
				System.out.println(file);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar la imagen por id array");
		}
	}

	//----------------------------------------------------------------------------------------------------------
	// FILTRADO

	/**
	 * Obtiene una lista de IDs de contenidos.
	 *
	 * @return Una lista de IDs de contenidos.
	 */
	public ArrayList<Integer> getOnlyIds() {

		ResultSet result;
		String sql;

		ArrayList<Integer> listIds = new ArrayList<>();
		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT idContenido FROM Contenido LIMIT 25";
			result = sentenciaSQL.executeQuery(sql);

			while(result.next()) {

				idCont = result.getInt("idContenido");
				//System.out.println("Dato ID obtenido : " + idCont);
				listIds.add(idCont);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar los ids de los contenidos");
		}

		return listIds;
	}

	/**
	 * Obtiene el ID de un contenido mediante su nombre.
	 *
	 * @param nombreCont El nombre del contenido para el cual se busca el ID.
	 * @return El ID del contenido encontrado o 0 si no se encuentra.
	 */
	public int getOnlyIdBuscador(TextField nombreCont) {

		ResultSet result;
		String sql;

		String nombreAux = nombreCont.getText();

		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Sólo sirve en la vista buscador para obtener el id de la película y mostrarlo
			sql = "SELECT idContenido FROM Contenido WHERE nameCont = '" + nombreAux + "'";
			result = sentenciaSQL.executeQuery(sql);

			while(result.next()) {

				idCont = result.getInt("idContenido");
				//System.out.println("Dato ID obtenido : " + idCont);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar el id buscado");
		}

		return idCont;
	}

	/**
	 * Obtiene los IDs de las películas limitado a un máximo de 25.
	 *
	 * @return Un ArrayList que contiene los IDs de las películas obtenidos.
	 */
	public ArrayList<Integer> getOnlyIdsPeliculas() {

		ResultSet result;
		String sql;

		ArrayList<Integer> listIds = new ArrayList<>();
		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Solo seleccionamos los id de las imágenes
			sql = "SELECT idContenido FROM Contenido WHERE tipoCont = 'Pelicula' LIMIT 25";
			result = sentenciaSQL.executeQuery(sql);

			// Las insertamos dentro de un ArrayList para luego con otro método mostrar las imágenes
			while(result.next()) {

				idCont = result.getInt("idContenido");
				//System.out.println("Dato ID obtenido : " + idCont);
				listIds.add(idCont);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar los ids pelicula de los contenidos");
		}

		return listIds;
	}

	/**
	 * Obtiene los IDs de las series limitado a un máximo de 25.
	 *
	 * @return Un ArrayList que contiene los IDs de las series obtenidos.
	 */
	public ArrayList<Integer> getOnlyIdsSeries() {

		ResultSet result;
		String sql;

		ArrayList<Integer> listIds = new ArrayList<>();
		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT idContenido FROM Contenido WHERE tipoCont = 'Serie' LIMIT 25";
			result = sentenciaSQL.executeQuery(sql);

			while(result.next()) {

				idCont = result.getInt("idContenido");
				//System.out.println("Dato ID obtenido : " + idCont);
				listIds.add(idCont);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar los ids pelicula de los contenidos");
		}

		return listIds;
	}

	/**
	 * Obtiene los IDs de contenidos filtrados por tipo y género, limitado a un máximo de 25.
	 *
	 * @param tipo   El tipo de contenido a filtrar ("Pelicula" o "Serie").
	 * @param genero El género del contenido a filtrar.
	 * @return Un ArrayList que contiene los IDs de los contenidos obtenidos según el tipo y género especificados.
	 */
	public ArrayList<Integer> getOnlyIdsGenero(String tipo, String genero) {

		ResultSet result;
		String sql;

		ArrayList<Integer> listIds = new ArrayList<>();
		int idCont = 0;

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			// Filtramos por contenido y género
			sql = "SELECT idContenido FROM Contenido WHERE tipoCont = '" + tipo + "' AND generoCont = '" + genero + "' LIMIT 25";
			result = sentenciaSQL.executeQuery(sql);

			while(result.next()) {

				idCont = result.getInt("idContenido");
				//System.out.println("Dato ID obtenido : " + idCont);
				listIds.add(idCont);
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al guardar los ids pelicula de los contenidos");
		}

		return listIds;
	}

	/**
	 * Obtiene y muestra los datos de un contenido, como el título, año y duración.
	 *
	 * @param idContenido El ID del contenido del cual se obtendrán los datos.
	 * @param txtTitulo   El objeto Label donde se mostrará el título del contenido.
	 * @param txtDuracion El objeto Label donde se mostrará el año y la duración del contenido.
	 */
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

	/**
	 * Obtiene y muestra los datos completos de un contenido, como el título, valoración, año, duración y sinopsis.
	 *
	 * @param idContenido El ID del contenido del cual se obtendrán los datos.
	 * @param txtTitulo   El objeto Label donde se mostrará el título del contenido.
	 * @param txtValoracion El objeto Label donde se mostrará la valoración del contenido.
	 * @param txtYear     El objeto Label donde se mostrará el año del contenido.
	 * @param txtDuracion El objeto Label donde se mostrará la duración del contenido.
	 * @param txtSinopsis El objeto Label donde se mostrará la sinopsis del contenido.
	 * @return La URL del video asociado al contenido.
	 */
	public String getDatosImageCompl(int idContenido, Label txtTitulo, Label txtValoracion, Label txtYear, Label txtDuracion, Label txtSinopsis) {

		ResultSet result;
		String sql;
		String urlVideo = "";

		try {

			conectar();
			sentenciaSQL = conexion.createStatement();

			sql = "SELECT * FROM Contenido WHERE idContenido = '" + idContenido + "'";
			result = sentenciaSQL.executeQuery(sql);

			System.out.println("El id del contenido en la bd es : " + idContenido);

			if(result.next()) {

				// Insertamos los datos obtenidos en los textos de la vista
				txtTitulo.setText(result.getString("generoCont") + " : " + result.getString("nameCont")  + " / " + result.getString("yearCont"));
				txtValoracion.setText("Rate : " + result.getString("valoracionCont"));
				//txtYear.setText(result.getString("yearCont"));
				txtDuracion.setText(result.getString("duracionCont"));
				txtSinopsis.setText(result.getString("sinopsisCont"));

				System.out.println("Dato titulo prueba : " + result.getString("generoCont"));

				// Obtenemos el link del video
				urlVideo = result.getString("urlVideo");
				System.out.println("url video bd : " + result.getString("urlVideo"));
			}

			desconectar();

		} catch (SQLException ex) {
			System.out.println("ERROR al mostrar datos de la imagen del home");
		}

		return urlVideo;
	}
}
