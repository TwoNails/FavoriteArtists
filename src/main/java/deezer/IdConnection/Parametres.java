package deezer.IdConnection;
// class pour ne pas avoir besoin d'ecrire tous les parametres quand on se connecte a la bdd.
public class Parametres {

	private static String password = "root"; // 
    private static String user = "root";
    private static String url = "jdbc:mysql://localhost:3308/deezerdb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";  

	public static String getPassword() {
		return password;
	}

	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}
}
