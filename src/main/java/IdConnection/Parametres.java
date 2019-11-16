package IdConnection;
// class pour ne pas avoir besoin d'ecrire tous les parametres quand on se connecte a la bdd.
public class Parametres {

	private static String user = "root";
	private static String password = "admin";
	private static String url = "jdbc:mysql://localhost:3306/FavoritesArtists?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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
