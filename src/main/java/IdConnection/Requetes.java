package IdConnection;

import java.sql.*;


public class Requetes {
	//field
	public final static String artists = "FavoritesArtists.ARTISTE";
	public final static String album = "FavoritesArtists.ALBUM";
	public final static String title = "FavoritesArtists.TITRE";
	
	//Method
	public static void afficheTable(Connection connexion, String tableName, String tableName2) throws SQLException{
		Statement stmt = connexion.createStatement();
		String demandeSql = "select * from "+ tableName+", "+tableName2;
		ResultSet demandeBdd = stmt.executeQuery(demandeSql);
		ResultSetMetaData recupeNomColonne = demandeBdd.getMetaData();
		int nbColonne = recupeNomColonne.getColumnCount();
		
		while (demandeBdd.next()) {
			for (int i = 0; i < nbColonne; i++) {
				String valeurColonne = demandeBdd.getString(i);
				String nomDeColonne = recupeNomColonne.getColumnName(i);
				System.out.println(MessageFormat.format("<{0}:{1}>\t\t",nomDeColonne,valeurColonne));
			}
			System.out.println();
		}
		
	}
	
	

}
