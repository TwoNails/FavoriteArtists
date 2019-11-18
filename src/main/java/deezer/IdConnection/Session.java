package deezer.IdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import deezer.IdConnection.Parametres;

public class Session 
{
	private Connection connection;

	/**
	 * Initialise la connection à la création.
	 */
	public Session() {
		try {
			initConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne la connection déjà initialisée.
	 * @return la connection déjà initialisée.
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Initialise la connection.
	 * 
	 * @throws SQLException
	 */
	private void initConnection() throws SQLException {
		connection = DriverManager.getConnection(Parametres.getUrl(), Parametres.getUser(), Parametres.getPassword());
	}

}

