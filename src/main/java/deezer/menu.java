package deezer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Scanner;

import deezer.IdConnection.Session;
import deezer.jdbc.RequetesJDBC;
import deezer.model.Artist;

public class menu {

	public enum ChoixTable {
		ARTIST, ALBUM, TRACK;
	}

	private static ChoixTable table;
	public static Session session = new Session();
	public static Scanner lectureScanner = new Scanner(System.in);
	public static String tableSelectionne;
	public static int reponseNum;

	public static String selectionTable() {

		int choixNum = -1;

		do {
			String reponse = lectureScanner.nextLine();

			try {
				choixNum = Integer.parseInt(reponse);
				if (choixNum > 0 && choixNum < 4) {
					continue;
				} else {
					System.out.println("Veuillez choisir un numero valide : ");					
				}
			} catch (Exception e) {
				System.out.println("Erreur pas un numero");
				System.out.println("Veuillez choisir un numero : ");
			}
		} while (choixNum < 0 || choixNum > 4);

		switch (choixNum) {
		case 1:
			tableSelectionne = ChoixTable.ARTIST.name();
			return tableSelectionne;
		case 2:
			tableSelectionne = ChoixTable.ALBUM.name();
			return tableSelectionne;
		case 3:
			tableSelectionne = ChoixTable.TRACK.name();
			return tableSelectionne;
		default:
			System.out.println("erreur");
			tableSelectionne = ChoixTable.ARTIST.name();
		}
		return tableSelectionne;
	}

	public static void affichageMenuTable() throws SQLException {

		System.out.println("Choix de la table à afficher");
		System.out.print("\n" + String.format("%-10s|", "1 ARTIST")
				+ (String.format("%-10s|", "2 ALBUM") + String.format("%-10s|", "3 TRACK")));
		RequetesJDBC.afficheTable(session.getConnection(), selectionTable());
	}

	public static void affichageOption() throws SQLException {

		affichageMenuTable();
		System.out.print("\n" + String.format("%-10s|", "1 CREATE") + (String.format("%-10s|", "2 READ")
				+ String.format("%-10s|", "3 DELETE") + (String.format("%-10s|", "4 UPDATE"))));

	}

	public static void selectionOption() {
		System.out.println("\nVeuillez choisir un numero : ");
		Scanner lectureScanner = new Scanner(System.in);
		reponseNum = -1;

		do {
			String reponse = lectureScanner.nextLine();
			try {
				reponseNum = Integer.parseInt(reponse);
				if (reponseNum > 0 && reponseNum < 5) {
					continue;
				} else {
					System.out.println("Veuillez choisir un numero valide : ");					
				}
			} catch (Exception e) {
				System.out.println("Erreur pas un numero");
				System.out.println("Veuillez choisir un numero : ");
			}
		} while (reponseNum < 0 || reponseNum > 4);
	}

	public static void CRUD() throws SQLException, MalformedURLException, IOException {

		affichageOption();
		
		if (tableSelectionne == ChoixTable.ARTIST.name()) {
			selectionOption();
			
			switch (reponseNum) {
			case 1: // CREATION ARTISTE
				System.out.println("Quel artiste veux-tu ajouter ?");
				String artistName = lectureScanner.nextLine();
				RequetesJDBC.createArtist(session.getConnection(), artistName);
				break;
				
			case 2: // LECTURE TABLE ARTISTE
				RequetesJDBC.afficheTable(session.getConnection(), RequetesJDBC.ARTISTS);
				break;
				
			case 3: // SUPPRESSION ARTISTE
				System.out.println("Quel artiste veux-tu supprimer?");
				String deleteName = lectureScanner.nextLine();
				RequetesJDBC.deleteArtist(session.getConnection(), deleteName);
				break;
				
			}
		} else if (tableSelectionne == ChoixTable.ALBUM.name()) {
			selectionOption();
			
			switch (reponseNum) {
			case 1: // CREATION ALBUM
				System.out.println("Quel album veux-tu ajouter ?");
				String albumName = lectureScanner.nextLine();
				RequetesJDBC.createAlbum(session.getConnection(), albumName);
				break;
				
			case 2: // LECTURE TABLE ALBUM
				RequetesJDBC.afficheTable(session.getConnection(), RequetesJDBC.ALBUM);
				break;
				
			case 3: // SUPPRESSION ALBUM
				System.out.println("Quel album veux-tu supprimer?");
				String deleteName = lectureScanner.nextLine();
				// RequetesJDBC.deleteAlbum(session.getConnection(), deleteName);
				break;
			}

		} else if (tableSelectionne == ChoixTable.TRACK.name()) {
			selectionOption();

			switch (reponseNum) {
			case 1: // CREATION TRACK
				System.out.println("Quel titre veux-tu ajouter ?");
				String trackName = lectureScanner.nextLine();
				RequetesJDBC.createTitleWithName(session.getConnection(), trackName);
				break;
				
			case 2: // LECTURE TABLE TRACK
				RequetesJDBC.afficheTable(session.getConnection(), RequetesJDBC.TRACK);
				break;
				
			case 3: // SUPPRESSION TRACK
				System.out.println("Quel titre veux-tu supprimer?");
				String deleteTrack = lectureScanner.nextLine();
				// RequetesJDBC.delete(session.getConnection(), deleteName);
				break;

			case 4: // FAVORIS TRACK
				System.out.println("Quel titre veux-tu mettre en favoris?");
				String updateTrack = lectureScanner.nextLine();
				RequetesJDBC.updateTrack(session.getConnection(), updateTrack);
				break;
			}
		}
	}
}
