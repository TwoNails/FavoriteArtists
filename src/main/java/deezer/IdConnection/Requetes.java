//package deezer.IdConnection;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.sql.*;
//import java.text.MessageFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.io.IOUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import deezer.model.Album;
//import deezer.model.Artist;
//import deezer.model.Track;
//
//
//public class Requetes {
//	
//	public final static String ARTISTS = "deezerdb.artist";
//	public final static String ALBUM = "deezerdb.album";
//	public final static String TRACK = "deezerdb.track";
//	
//	// REQUETES API
//	
//	public static void writeJson(String jsonText) 
//	{
//		BufferedWriter bw;
//
//		try {
//			bw = new BufferedWriter(new FileWriter("sample.json"));
//			bw.write(jsonText);
//			bw.close();
//		}
//
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public static Artist artisteDeezer (String artiste) throws MalformedURLException, IOException
//	{
//		String url = "https://api.deezer.com/search/artist/?q="+artiste+"&index=0&nb_items=1&output=java";
//		
//		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
//		writeJson(jsonText);
//		JSONObject jsonComplet = new JSONObject(jsonText);
//		JSONArray dataArtist = jsonComplet.getJSONArray("data");
//		
//		return new Artist(dataArtist);
//	}
//	
//	public static Artist artisteDeezer (int id) throws MalformedURLException, IOException
//	{
//		String url = "https://api.deezer.com/artist/"+id;
//		
//		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
//		writeJson(jsonText);
//		JSONObject jsonComplet = new JSONObject(jsonText);
//		
//		
//		return new Artist(jsonComplet);
//	}
//	
//	public static Album albumDeezer (Connection conn, String album) throws MalformedURLException, IOException, SQLException
//	{
//		String url = "https://api.deezer.com/search/album/?q="+album+"&index=0&nb_items=1&output=java";
//		
//		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
//		writeJson(jsonText);
//		JSONObject jsonComplet = new JSONObject(jsonText);
//		JSONArray dataAlbum = jsonComplet.getJSONArray("data");
//		JSONObject dataArtist = dataAlbum.getJSONObject(0).getJSONObject("artist");
//		
//		int idArtist= dataArtist.getInt("id");
//		boolean artistAlreadyInDB = false;
//		
//		for (Integer n : listIDArtists(conn)) {
//			if(n==idArtist) {
//				artistAlreadyInDB = true;
//			}
//		}
//		
//		if(artistAlreadyInDB){
//			return new Album(dataAlbum);
//		} else {
//			createArtists(conn, artisteDeezer(idArtist));
//			return new Album(dataAlbum);
//		}
//	}
//	
//	
//	public static Album albumDeezer (Connection conn, int idAlbum) throws MalformedURLException, IOException, SQLException
//	{
//		String url = "https://api.deezer.com/album/"+idAlbum;
//		
//		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
//		writeJson(jsonText);
//		JSONObject jsonComplet = new JSONObject(jsonText);
//		
//		JSONObject dataArtist = jsonComplet.getJSONObject("artist");
//		
//		int idArtist= dataArtist.getInt("id");
//		boolean artistAlreadyInDB = false;
//		
//		for (Integer n : listIDArtists(conn)) {
//			if(n==idArtist) {
//				artistAlreadyInDB = true;
//			}
//		}
//		
//		if(artistAlreadyInDB){
//			return new Album(jsonComplet);
//		} else {
//			createArtists(conn, artisteDeezer(idArtist));
//			return new Album(jsonComplet);
//		}
//	}
//	
//	
//	
//	
//	public static Track titreDeezer (Connection conn, String titre) throws MalformedURLException, IOException, SQLException
//	{
//		String url = "https://api.deezer.com/search/track/?q="+titre+"&index=0&limit=2&output=json";
//		
//		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
//		writeJson(jsonText);
//		JSONObject jsonComplet = new JSONObject(jsonText);
//		JSONArray dataTitre = jsonComplet.getJSONArray("data");
//		JSONObject dataAlbum = dataTitre.getJSONObject(0).getJSONObject("album");
//		
//		int idAlbum= dataAlbum.getInt("id");
//		boolean titreAlreadyInDB = false;
//		
//		for (Integer n : listIDAlbums(conn)) {
//			if(n==idAlbum) {
//				titreAlreadyInDB = true;
//			}
//		}
//		
//		if(titreAlreadyInDB){
//			return new Track(dataTitre);
//		} else {
//			createAlbum(conn, albumDeezer(conn, idAlbum));
//			return new Track(dataTitre);
//		}
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	// REQUETES BDD
//	
//	//Method
//	
//	
//	
//	// Obtenir listes 
//	
//	public static List<Integer> listIDArtists (Connection conn) throws SQLException {
//		 List<Integer> listIDArtists = new ArrayList<Integer>();
//		
//		Statement stmt = conn.createStatement();
//		String demandSQL = "select id from " + ARTISTS;
//		ResultSet demandeBdd = stmt.executeQuery(demandSQL);
//		
//		while (demandeBdd.next()) {	
//			listIDArtists.add(demandeBdd.getInt(1));
//		}
//		
//		return listIDArtists;
//	}
//	
//	public static List<Integer> listIDAlbums (Connection conn) throws SQLException {
//		 List<Integer> listIDAlbums = new ArrayList<Integer>();
//		
//		Statement stmt = conn.createStatement();
//		String demandSQL = "select id from " + ALBUM;
//		ResultSet demandeBdd = stmt.executeQuery(demandSQL);
//		
//		while (demandeBdd.next()) {	
//			listIDAlbums.add(demandeBdd.getInt(1));
//		}
//		
//		return listIDAlbums;
//	}
//	
//	
//	
//	// Affichage
//	
//	public static void afficheTable(Connection connexion, String tableName/*, String tableName2*/) throws SQLException
//	{
//		Statement stmt = connexion.createStatement();
//		String demandeSql = "select * from "+ tableName/*+", "+tableName2*/;
//		ResultSet demandeBdd = stmt.executeQuery(demandeSql);
//		ResultSetMetaData recupeNomColonne = demandeBdd.getMetaData();
//		int nbColonne = recupeNomColonne.getColumnCount();
//		
//		while (demandeBdd.next()) {
//			for (int i = 1; i < nbColonne; i++) 
//			{
//				String valeurColonne = demandeBdd.getString(i);
//				String nomDeColonne = recupeNomColonne.getColumnName(i);
//				System.out.println(MessageFormat.format("<{0}:{1}>\t\t",nomDeColonne,valeurColonne));
//			}
//			System.out.println();
//		}
//		
//	}
//	
//	
//	
//	// Methodes CRUD artiste
//	
//	public static Artist getArtistsByName(Connection conn, String nomCherche) throws SQLException 
//	{
//		PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS + " where nom = ?");
//
//		stmt.setString(1, nomCherche);
//		ResultSet rs = stmt.executeQuery();
//
//		if (rs.next()) 
//		{
//			int id = rs.getInt("id");
//			String nom = rs.getString("nom");
//			int nbFan = rs.getInt("nb_fan");
//			return new Artist(id, nom, nbFan);
//			
//		} 
//		else 
//		{
//			throw new Error("l'artiste cherché n'est pas dans la base");
//		}
//	}
//	
//	public static void updateArtists(Connection conn, Artist artisteAModifier) throws SQLException 
//	{
//		PreparedStatement stmt = conn.prepareStatement(" update " + ARTISTS + " set nom = ? , nb_fan = ?  where id = ? ");
//		stmt.setString(1, artisteAModifier.getNom());
//		stmt.setLong(2, artisteAModifier.getNbFan());
//		stmt.setInt(3, artisteAModifier.getId());
//		stmt.executeUpdate();
//	}
//	
//	public static void createArtists(Connection conn, Artist nvArtiste) throws SQLException 
//	{
//		PreparedStatement stmt = conn.prepareStatement(" insert into " + ARTISTS + " values (? , ? , ?) ");
//		stmt.setInt(1, nvArtiste.getId());
//		stmt.setString(2, nvArtiste.getNom());
//		stmt.setLong(3, nvArtiste.getNbFan());
//		stmt.executeUpdate();
//	}
//	
//	public static void deleteArtists(Connection conn, Artist artisteASupprimer) throws SQLException 
//	{
//		PreparedStatement stmt = conn.prepareStatement(" delete from " + ARTISTS + " where id = ? ");
//		stmt.setInt(1, artisteASupprimer.getId());
//		stmt.executeUpdate();
//	}
//	
//	public static List<Artist> getAllArtists(Connection conn)throws SQLException 
//	{
//		PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS);
//
//		ResultSet rs = stmt.executeQuery();
//		
//		List<Artist> artisteListe = new ArrayList<Artist>();
//
//		while (rs.next()) 
//		{
//			int id = rs.getInt("id");
//			String nom = rs.getString("nom");
//			int nbFan = rs.getInt("nb_fan");
//			artisteListe.add(new Artist(id, nom, nbFan));
//			
//		} 
//		return artisteListe;
//	}
//	
//	
//	// Methodes CRUD album
//	
//	public static void createAlbum(Connection connection, Album newAlbum) throws SQLException{
//        PreparedStatement stmt = connection.prepareStatement(
//                "insert into "+ALBUM+" values (? , ? , ?)");
//        stmt.setInt(1, newAlbum.getId());
//        stmt.setString(2, newAlbum.getTitle());
//        stmt.setInt(3, newAlbum.getId_artist());
//        System.out.println(stmt);
//        
//        stmt.executeUpdate();
//    }
//	
//	
//	
//	// Methodes CRUD title
//	
//	public static void createTitle(Connection connection, Track newTitre) throws SQLException{
//        PreparedStatement stmt = connection.prepareStatement(
//                "insert into "+TRACK+" values (? , ?, ?, ?, ? )");
//        stmt.setInt(1, newTitre.getId());
//        stmt.setString(2, newTitre.getTitle());
//        stmt.setInt(3, newTitre.getDuration());
//        stmt.setInt(4, newTitre.getFavorite());
//        stmt.setInt(5, newTitre.getId_album());
//
//        System.out.println(stmt);
//        
//        stmt.executeUpdate();
//    }
//	
//}
