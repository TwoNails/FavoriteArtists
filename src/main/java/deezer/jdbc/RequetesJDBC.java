package deezer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import deezer.model.Album;
import deezer.model.Artist;
import deezer.model.Track;

public class RequetesJDBC {
	
	// REQUETES BDD
	
		//Method
		
		//field
		public final static String ARTISTS = "deezerdb.artist";
		public final static String ALBUM = "deezerdb.album";
		public final static String TRACK = "deezerdb.track";
		
		// Obtenir listes 
		
		public static List<Integer> listIDArtists(Connection conn) throws SQLException {
			 List<Integer> listIDArtists = new ArrayList<Integer>();
			
			Statement stmt = conn.createStatement();
			String demandSQL = "select id from " + ARTISTS;
			ResultSet demandeBdd = stmt.executeQuery(demandSQL);
			
			while (demandeBdd.next()) {	
				listIDArtists.add(demandeBdd.getInt(1));
			}
			
			return listIDArtists;
		}
		
		public static List<Integer> listIDAlbums (Connection conn) throws SQLException {
			 List<Integer> listIDAlbums = new ArrayList<Integer>();
			
			Statement stmt = conn.createStatement();
			String demandSQL = "select id from " + ALBUM;
			ResultSet demandeBdd = stmt.executeQuery(demandSQL);
			
			while (demandeBdd.next()) {	
				listIDAlbums.add(demandeBdd.getInt(1));
			}
			
			return listIDAlbums;
		}
		
		
		
		// Affichage
		
		public static void afficheTable(Connection connexion, String tableName/*, String tableName2*/) throws SQLException
		{
			Statement stmt = connexion.createStatement();
			String demandeSql = "select * from "+ tableName/*+", "+tableName2*/;
			ResultSet demandeBdd = stmt.executeQuery(demandeSql);
			ResultSetMetaData recupeNomColonne = demandeBdd.getMetaData();
			int nbColonne = recupeNomColonne.getColumnCount();
			
			while (demandeBdd.next()) {
				for (int i = 1; i < nbColonne; i++) 
				{
					String valeurColonne = demandeBdd.getString(i);
					String nomDeColonne = recupeNomColonne.getColumnName(i);
					System.out.println(MessageFormat.format("<{0}:{1}>\t\t",nomDeColonne,valeurColonne));
				}
				System.out.println();
			}
			
		}
		
		
		
		// Methodes CRUD artiste
		
		public static Artist getArtistsByName(Connection conn, String nomCherche) throws SQLException 
		{
			PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS + " where nom = ?");

			stmt.setString(1, nomCherche);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) 
			{
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				int nbFan = rs.getInt("nb_fan");
				return new Artist(id, nom, nbFan);
				
			} 
			else 
			{
				throw new Error("l'artiste cherché n'est pas dans la base");
			}
		}
		
		public static void updateArtists(Connection conn, Artist artisteAModifier) throws SQLException 
		{
			PreparedStatement stmt = conn.prepareStatement("update " + ARTISTS + " set nom = ? , nb_fan = ?  where id = ? ");
			stmt.setString(1, artisteAModifier.getNom());
			stmt.setLong(2, artisteAModifier.getNbFan());
			stmt.setInt(3, artisteAModifier.getId());
			stmt.executeUpdate();
		}
		
		public static void createArtists(Connection conn, Artist nvArtiste) throws SQLException 
		{
			PreparedStatement stmt = conn.prepareStatement("insert into " + ARTISTS + " values (? , ? , ?) ");
			stmt.setInt(1, nvArtiste.getId());
			stmt.setString(2, nvArtiste.getNom());
			stmt.setLong(3, nvArtiste.getNbFan());
			stmt.executeUpdate();
			System.out.println("ok");
		}
		
		public static void deleteArtists(Connection conn, Artist artisteASupprimer) throws SQLException 
		{
			PreparedStatement stmt = conn.prepareStatement("delete from " + ARTISTS + " where id = ? ");
			stmt.setInt(1, artisteASupprimer.getId());
			stmt.executeUpdate();
		}
		
		public static List<Artist> getAllArtists(Connection conn)throws SQLException 
		{
			PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS);

			ResultSet rs = stmt.executeQuery();
			
			List<Artist> artisteListe = new ArrayList<Artist>();

			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				int nbFan = rs.getInt("nb_fan");
				artisteListe.add(new Artist(id, nom, nbFan));
				
			} 
			return artisteListe;
		}
		
		
		// Methodes CRUD album
		
		public static void createAlbum(Connection connection, Album newAlbum) throws SQLException{
	        PreparedStatement stmt = connection.prepareStatement(
	                "insert into "+ALBUM+" values (? , ? , ?)");
	        stmt.setInt(1, newAlbum.getId());
	        stmt.setString(2, newAlbum.getTitle());
	        stmt.setInt(3, newAlbum.getId_artist());
	        System.out.println(stmt);
	        
	        stmt.executeUpdate();
	    }
		
		
		
		// Methodes CRUD title
		
		public static void createTitle(Connection connection, Track newTitre) throws SQLException{
	        PreparedStatement stmt = connection.prepareStatement(
	                "insert into "+TRACK+" values (? , ?, ?, ?, ? )");
	        stmt.setInt(1, newTitre.getId());
	        stmt.setString(2, newTitre.getTitle());
	        stmt.setInt(3, newTitre.getDuration());
	        stmt.setInt(4, newTitre.getFavorite());
	        stmt.setInt(5, newTitre.getId_album());

	        System.out.println(stmt);
	        
	        stmt.executeUpdate();
	    }

}
