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

	// Method

	// field
	public final static String ARTISTS = "deezerdb.artist";
	public final static String ALBUM = "deezerdb.album";
	public final static String TRACK = "deezerdb.track";

	// OUTILS D'AFFICHAGE

	// affiche une table complète.

	public static void afficheTable(Connection connexion, String tableName) throws SQLException {
		Statement stmt = connexion.createStatement();
		String demandeSql = "select * from " + tableName;
		ResultSet demandeBdd = stmt.executeQuery(demandeSql);
		ResultSetMetaData recupenameColonne = demandeBdd.getMetaData();
		int nbColonne = recupenameColonne.getColumnCount();

		String properTableName = tableName.replace("deezerdb.", "").toUpperCase();
		System.out.println("\n____________________________________________\nTABLE : " + properTableName
				+ "\n____________________________________________\n");
		for (int i = 1; i < nbColonne; i++) {
			String nameDeColonne = recupenameColonne.getColumnName(i);
			System.out.print(String.format("%-10s|", nameDeColonne));
		}
		System.out.println();

		while (demandeBdd.next()) {

			for (int i = 1; i < nbColonne; i++) {
				String valeurColonne = demandeBdd.getString(i);
				System.out.print(String.format("%-10.10s|", valeurColonne));
			}
			System.out.println();
		}
	}

	// affiche juste les names ou le titre.

	public static void afficheShortTable(Connection connexion, String tableName) throws SQLException {
		Statement stmt = connexion.createStatement();
		String demandeSql = "select * from " + tableName;
		ResultSet demandeBdd = stmt.executeQuery(demandeSql);

		String properTableName = tableName.replace("deezerdb.", "").toUpperCase();
		System.out.println(
				"\n___________________________\nTABLE : " + properTableName + "\n___________________________\n");

		while (demandeBdd.next()) {

		}
	}

	// CREATE METHODS

	// Create methods : Artist

	/**
	 * 
	 * @param conn       the current connection
	 * @param artistName the name of the artist we're trying to add
	 * @throws SQLException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void createArtist(Connection conn, String artistName)
			throws SQLException, MalformedURLException, IOException {
		Artist nvArtiste = RequetesAPI.artisteDeezer(conn, artistName);

		PreparedStatement stmt = conn.prepareStatement("insert into " + ARTISTS + " values (? , ? , ?) ");
		stmt.setInt(1, nvArtiste.getId());
		stmt.setString(2, nvArtiste.getName());
		stmt.setLong(3, nvArtiste.getNbFan());
		try {
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("existe déjà dans la base");
		}
	}

	// Same but with the ID as argument.
	// Copying the code rather than using one method to call the other is on
	// purpose, to avoid needing to reach the API twice.

	public static void createArtist(Connection conn, int ArtistID)
			throws SQLException, MalformedURLException, IOException {
		Artist nvArtiste = RequetesAPI.artisteDeezer(conn, ArtistID);

		PreparedStatement stmt = conn.prepareStatement("insert into " + ARTISTS + " values (? , ? , ?) ");
		stmt.setInt(1, nvArtiste.getId());
		stmt.setString(2, nvArtiste.getName());
		stmt.setLong(3, nvArtiste.getNbFan());
		try {
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("existe déjà dans la base");
		}
	}

	// Create methods : Album

	public static void createAlbum(Connection connection, int albumID)
			throws SQLException, MalformedURLException, IOException {

		Album newAlbum = RequetesAPI.albumDeezer(connection, albumID);

		PreparedStatement stmt = connection.prepareStatement("insert into " + ALBUM + " values (? , ? , ?)");
		stmt.setInt(1, newAlbum.getId());
		stmt.setString(2, newAlbum.getTitle());
		stmt.setInt(3, newAlbum.getId_artist());
		System.out.println(stmt);

		try {
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("existe déjà dans la base");
		}
	}

	public static void createAlbum(Connection connection, String albumName)
			throws SQLException, MalformedURLException, IOException {

		Album newAlbum = RequetesAPI.albumDeezer(connection, albumName);

		PreparedStatement stmt = connection.prepareStatement("insert into " + ALBUM + " values (? , ? , ?)");
		stmt.setInt(1, newAlbum.getId());
		stmt.setString(2, newAlbum.getTitle());
		stmt.setInt(3, newAlbum.getId_artist());
		System.out.println(stmt);

		try {
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("existe déjà dans la base");
		}
	}

	// Create methods : track

	public static void createTitleWithID(Connection connection, int titreID)
			throws SQLException, MalformedURLException, IOException {

		Track newTitre = RequetesAPI.titreDeezer(connection, titreID);

		PreparedStatement stmt = connection.prepareStatement("insert into " + TRACK + " values (? , ?, ?, ?, ? )");
		stmt.setInt(1, newTitre.getId());
		stmt.setString(2, newTitre.getTitle());
		stmt.setInt(3, newTitre.getDuration());
		stmt.setInt(4, newTitre.getFavorite());
		stmt.setInt(5, newTitre.getId_album());

		System.out.println(stmt);

		try {
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("existe déjà dans la base");
		}
	}

	public static void createTitleWithName(Connection connection, String titre)
			throws SQLException, MalformedURLException, IOException {

		Track newTitre = RequetesAPI.titreDeezer(connection, titre);

		PreparedStatement stmt = connection.prepareStatement("insert into " + TRACK + " values (? , ?, ?, ?, ? )");
		stmt.setInt(1, newTitre.getId());
		stmt.setString(2, newTitre.getTitle());
		stmt.setInt(3, newTitre.getDuration());
		stmt.setInt(4, newTitre.getFavorite());
		stmt.setInt(5, newTitre.getId_album());

		System.out.println(stmt);

		try {
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("existe déjà dans la base");
		}
	}

	// READ METHODS

	// Read methods : artist

	public static Artist getArtist(Connection conn, String artistName) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS + " where name = ?");
		stmt.setString(1, artistName);

		System.out.println("Our query for getArtist (with arg : artistName) is : " + stmt);

		ResultSet rs = stmt.executeQuery();

		// if (rs.next()) {

		try {
			rs.next();
			int id = rs.getInt("id");

			System.out.println(id);
			return getArtist(conn, id);
		} catch (SQLException e) {
			// System.out.println("L'artiste cherché n'est pas dans la base");
			return new Artist(0, "NOT IN BASE", 0);
		}

		// }

		/*
		 * else { throw new Error("l'artiste cherché n'est pas dans la base"); }
		 */
	}

	public static Artist getArtist(Connection conn, int artistID) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS + " where id = ?");
		stmt.setInt(1, artistID);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int nbFan = rs.getInt("nb_fan");
			return new Artist(id, name, nbFan);
		} else {
			throw new Error("l'artiste cherché n'est pas dans la base");
		}
	}

	// Read methods : album

	public static Album getAlbum(Connection conn, String albumName) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + ALBUM + " where name = ?");

		stmt.setString(1, albumName);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			return getAlbum(conn, id);
		} else {
			throw new Error("l'album cherché n'est pas dans la base");
		}
	}

	public static Album getAlbum(Connection conn, int albumID) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + ALBUM + " where id = ?");

		stmt.setInt(1, albumID);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			int id_artist = rs.getInt("id_artist");
			return new Album(id, title, id_artist);
		} else {
			throw new Error("l'album cherché n'est pas dans la base");
		}
	}

	// Read methods : track

	public static Track getTrack(Connection conn, String trackName) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + TRACK + " where title = ?");

		stmt.setString(1, trackName);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			return getTrack(conn, id);
		} else {
			throw new Error("la piste cherchée n'est pas dans la base");
		}
	}

	public static Track getTrack(Connection conn, int trackID) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + TRACK + " where id = ?");

		stmt.setInt(1, trackID);
		ResultSet rs = stmt.executeQuery();

		rs.next();
		System.out.println(rs);

		int id = rs.getInt("id");
		String title = rs.getString("title");
		int duration = rs.getInt("duration");
		int favorite = rs.getInt("favorite");
		int id_album = rs.getInt("id_album");
		return new Track(id, title, duration, favorite, id_album);

		/*
		 * if (rs.next()) { int id = rs.getInt("id"); String title =
		 * rs.getString("title"); int duration = rs.getInt("duration"); int favorite =
		 * rs.getInt("favorite"); int id_artist = rs.getInt("id_artist"); return new
		 * Track(id, title, duration, favorite, id_artist); } else { throw new
		 * Error("la piste cherchée n'est pas dans la base"); }
		 */
	}

	// DELETE METHODS

	// Delete methods : artist

	/**
	 * Calls the method deleteArtist (Connection conn, int ID) with the ID
	 * associated with that artistName
	 * 
	 * @param conn       the current connection
	 * @param artistName the name of the artist we're trying to delete
	 * @throws SQLException when the artist can't be found in the database
	 */
	public static void deleteArtist(Connection conn, String artistName) throws SQLException {
		Artist artistToDelete = getArtist(conn, artistName);
		System.out.println("artistToDelete's ID = " + artistToDelete.getId());
		deleteArtist(conn, artistToDelete.getId());
	}

	/**
	 * Delete all albums of the artist with id = artistID from the database, then
	 * delete this artist from the database.
	 * 
	 * @param conn     the current connection
	 * @param artistID the ID of the artist we're trying to delete
	 * @throws SQLException when the artist can't be found in the database
	 */
	public static void deleteArtist(Connection conn, int artistID) throws SQLException {

		// Delete all albums from the artist
		List<Integer> albumsToDeleteID = listAlbumsFromArtist(conn, artistID);

		try {
			for (Integer ID : albumsToDeleteID) {
				deleteAlbum(conn, ID);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Il n'y a aucun album de l'artiste demandé dans la database");
		}

		// Then delete the artist
		PreparedStatement stmt = conn.prepareStatement("delete from " + ARTISTS + " where id = ? ");
		stmt.setInt(1, artistID);
		stmt.execute();
	}

	// Delete methods : album

	/**
	 * Delete all tracks of the album with name = albumName, then delete the album
	 * from the database.
	 * 
	 * @param conn       the current connection
	 * @param albumtName the name of the album we're trying to delete
	 * @throws SQLException when the album can't be found in the database
	 */
	public static void deleteAlbum(Connection conn, String albumName) throws SQLException {
		Album albumToDelete = getAlbum(conn, albumName);
		deleteAlbum(conn, albumToDelete.getId());
	}

	public static void deleteAlbum(Connection conn, int albumID) throws SQLException {
		// Delete all tracks from the album
		PreparedStatement stmt = conn.prepareStatement("delete from " + TRACK + " where id_album = ? ");
		stmt.setInt(1, albumID);
		stmt.execute();

		// Then delete the album
		stmt = conn.prepareStatement("delete from " + ALBUM + " where id = ? ");
		stmt.setInt(1, albumID);
		stmt.execute();
	}

	// Delete methods : track

	/**
	 * Delete the track from the database.
	 * 
	 * @param conn       the current connection
	 * @param albumtName the name of the album we're trying to delete
	 * @throws SQLException when the album can't be found in the database
	 */

	public static void deleteTrack(Connection conn, String trackName) throws SQLException {
		Track trackToDelete = getTrack(conn, trackName);
		deleteTrack(conn, trackToDelete.getId());
	}

	public static void deleteTrack(Connection conn, int trackID) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("delete from " + TRACK + " where id = ? ");
		stmt.setInt(1, trackID);
		stmt.execute();
	}

	public static List<Artist> getAllArtists(Connection conn) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from " + ARTISTS);

		ResultSet rs = stmt.executeQuery();

		List<Artist> artisteListe = new ArrayList<Artist>();

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int nbFan = rs.getInt("nb_fan");
			artisteListe.add(new Artist(id, name, nbFan));

		}
		return artisteListe;
	}

	// LISTES

	// liste d'ID de tous les albums dont l'auteur (idArtiste) est nameAuteur

	public static List<Integer> listAlbumsFromArtist(Connection conn, int idArtist) throws SQLException {
		List<Integer> listIDAlbums = new ArrayList<Integer>();

		Statement stmt = conn.createStatement();
		String demandSQL = "select id from " + ALBUM + " WHERE id_artist = " + idArtist;
		System.out.println(demandSQL);

		ResultSet demandeBdd = stmt.executeQuery(demandSQL);

		while (demandeBdd.next()) {
			listIDAlbums.add(demandeBdd.getInt(1));
		}

		return listIDAlbums;
	}

	public static void updateTrack(Connection conn, String updateTrack) throws SQLException {

		boolean isFavorite;
		PreparedStatement stmt;

		if (getTrack(conn, updateTrack).getFavorite() == 0) {
			isFavorite = false;
		} else {
			isFavorite = true;
		}

		if (isFavorite) {
			stmt = conn.prepareStatement("update " + TRACK + " set favorite = 0 where title = ? ");

		} else {
			stmt = conn.prepareStatement("update " + TRACK + " set favorite = 1 where title = ? ");

		}
		stmt.setString(1, updateTrack);
		stmt.execute();

	}

}
