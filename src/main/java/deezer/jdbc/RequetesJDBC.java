package deezer.jdbc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import deezer.api.RequetesAPI;
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
		
		public static void afficheTable(Connection connexion, String tableName) throws SQLException
		{
			Statement stmt = connexion.createStatement();
			String demandeSql = "select * from "+ tableName;
			ResultSet demandeBdd = stmt.executeQuery(demandeSql);
			ResultSetMetaData recupeNomColonne = demandeBdd.getMetaData();
			int nbColonne = recupeNomColonne.getColumnCount();
			
			
			String properTableName = tableName.replace("deezerdb.", "").toUpperCase();
			System.out.println("\n____________________________________________\nTABLE : " + properTableName +"\n____________________________________________\n");
			for (int i = 1; i < nbColonne; i++) 
			{
				String nomDeColonne = recupeNomColonne.getColumnName(i);
				System.out.print(String.format("%-10s|", nomDeColonne));
			}
			System.out.println();
			
			while (demandeBdd.next()) {
				
				for (int i = 1; i < nbColonne; i++) 
				{
					String valeurColonne = demandeBdd.getString(i);
					System.out.print(String.format("%-10.10s|", valeurColonne));
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
		
		
		public static void createArtistsWithID(Connection conn, int ArtistID) throws SQLException, MalformedURLException, IOException 
		{
			Artist nvArtiste = RequetesAPI.artisteDeezer(conn, ArtistID);
			
			PreparedStatement stmt = conn.prepareStatement("insert into " + ARTISTS + " values (? , ? , ?) ");
			stmt.setInt(1, nvArtiste.getId());
			stmt.setString(2, nvArtiste.getNom());
			stmt.setLong(3, nvArtiste.getNbFan());
			try {
		        stmt.executeUpdate();
		        }
		        catch (SQLIntegrityConstraintViolationException e) {
		        	System.out.println("existe déjà dans la base");			
	        	}
		}
		
		public static void createArtistsWithName(Connection conn, String artistName) throws SQLException, MalformedURLException, IOException 
		{
			Artist nvArtiste = RequetesAPI.artisteDeezer(conn, artistName);
			
			PreparedStatement stmt = conn.prepareStatement("insert into " + ARTISTS + " values (? , ? , ?) ");
			stmt.setInt(1, nvArtiste.getId());
			stmt.setString(2, nvArtiste.getNom());
			stmt.setLong(3, nvArtiste.getNbFan());
			try {
		        stmt.executeUpdate();
		        }
		        catch (SQLIntegrityConstraintViolationException e) {
		        	System.out.println("existe déjà dans la base");			
	        	}
		}		
		
		public static void deleteArtists(Connection conn, String artisteASupprimer) throws SQLException 
		{
			PreparedStatement stmt = conn.prepareStatement("delete from " + ARTISTS + " where name = ? ");
			stmt.setString(1, artisteASupprimer);
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
	
		public static void createAlbumWithID(Connection connection, int albumID) throws SQLException, MalformedURLException, IOException{
			
			Album newAlbum = RequetesAPI.albumDeezer(connection, albumID);
			
	        PreparedStatement stmt = connection.prepareStatement(
	                "insert into "+ALBUM+" values (? , ? , ?)");
	        stmt.setInt(1, newAlbum.getId());
	        stmt.setString(2, newAlbum.getTitle());
	        stmt.setInt(3, newAlbum.getId_artist());
	        System.out.println(stmt);
	        
	        try {
	        	stmt.executeUpdate();
	        }
	        catch (SQLIntegrityConstraintViolationException e) {
	        	System.out.println("existe déjà dans la base");			
        	}
	    }
		
		public static void createAlbumWithName(Connection connection, String albumName) throws SQLException, MalformedURLException, IOException{
			
			Album newAlbum = RequetesAPI.albumDeezer(connection, albumName);
			
	        PreparedStatement stmt = connection.prepareStatement(
	                "insert into "+ALBUM+" values (? , ? , ?)");
	        stmt.setInt(1, newAlbum.getId());
	        stmt.setString(2, newAlbum.getTitle());
	        stmt.setInt(3, newAlbum.getId_artist());
	        System.out.println(stmt);
	        
	        try {
	        	stmt.executeUpdate();
	        }
	        catch (SQLIntegrityConstraintViolationException e) {
	        	System.out.println("existe déjà dans la base");			
        	}
	    }
		
		
		
		// Methodes CRUD title
		
		public static void createTitleWithID(Connection connection, int titreID) throws SQLException, MalformedURLException, IOException{
			
			Track newTitre = RequetesAPI.titreDeezer(connection, titreID);
			
	        PreparedStatement stmt = connection.prepareStatement(
	                "insert into "+TRACK+" values (? , ?, ?, ?, ? )");
	        stmt.setInt(1, newTitre.getId());
	        stmt.setString(2, newTitre.getTitle());
	        stmt.setInt(3, newTitre.getDuration());
	        stmt.setInt(4, newTitre.getFavorite());
	        stmt.setInt(5, newTitre.getId_album());

	        System.out.println(stmt);
	        
	        try {
		        stmt.executeUpdate();
		    }
		    catch (SQLIntegrityConstraintViolationException e) {
		        System.out.println("existe déjà dans la base");			
	        }
	    }
		
		public static void createTitleWithName(Connection connection, String titre) throws SQLException, MalformedURLException, IOException{
			
			Track newTitre = RequetesAPI.titreDeezer(connection, titre);
			
	        PreparedStatement stmt = connection.prepareStatement(
	                "insert into "+TRACK+" values (? , ?, ?, ?, ? )");
	        stmt.setInt(1, newTitre.getId());
	        stmt.setString(2, newTitre.getTitle());
	        stmt.setInt(3, newTitre.getDuration());
	        stmt.setInt(4, newTitre.getFavorite());
	        stmt.setInt(5, newTitre.getId_album());

	        System.out.println(stmt);
	        
	        try {
		        stmt.executeUpdate();
		    }
		    catch (SQLIntegrityConstraintViolationException e) {
		        System.out.println("existe déjà dans la base");			
	        }
	    }
		
		public static void updateTitle (Connection connection, String name) throws SQLException {
			
			PreparedStatement stmt = connection.prepareStatement(
					"if favorite =0 select (update "+TRACK+" set favorite = 1 where title = ?) else (update "+TRACK+" set favorite = 0 where title = ?) ");
			
			stmt.setString(1, name);
			stmt.setString(2, name);
			stmt.executeUpdate();
			
		}

}
