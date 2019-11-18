package deezer.api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import deezer.jdbc.RequetesJDBC;
import deezer.model.Album;
import deezer.model.Artist;
import deezer.model.Track;


public class RequetesAPI {

	public static void writeJson(String jsonText) 
	{
		BufferedWriter bw;

		try {
			bw = new BufferedWriter(new FileWriter("sample.json"));
			bw.write(jsonText);
			bw.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Artist artisteDeezer (String artiste) throws MalformedURLException, IOException
	{
		String url = "https://api.deezer.com/search/artist/?q="+artiste+"&index=0&nb_items=1&output=java";
		
		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);
		JSONObject jsonComplet = new JSONObject(jsonText);
		JSONArray dataArtist = jsonComplet.getJSONArray("data");
		
		return new Artist(dataArtist);
	}
	
	public static Artist artisteDeezer (int id) throws MalformedURLException, IOException
	{
		String url = "https://api.deezer.com/artist/"+id;
		
		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);
		JSONObject jsonComplet = new JSONObject(jsonText);
		
		
		return new Artist(jsonComplet);
	}
	
	public static Album albumDeezer (Connection conn, String album) throws MalformedURLException, IOException, SQLException
	{
		String url = "https://api.deezer.com/search/album/?q="+album+"&index=0&nb_items=1&output=java";
		
		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);
		JSONObject jsonComplet = new JSONObject(jsonText);
		JSONArray dataAlbum = jsonComplet.getJSONArray("data");
		JSONObject dataArtist = dataAlbum.getJSONObject(0).getJSONObject("artist");
		
		int idArtist= dataArtist.getInt("id");
		boolean artistAlreadyInDB = false;
		
		for (Integer n : RequetesJDBC.listIDArtists(conn)) {
			if(n==idArtist) {
				artistAlreadyInDB = true;
			}
		}
		
		if(artistAlreadyInDB){
			return new Album(dataAlbum);
		} else {
			RequetesJDBC.createArtists(conn, artisteDeezer(idArtist));
			return new Album(dataAlbum);
		}
	}
	
	
	public static Album albumDeezer (Connection conn, int idAlbum) throws MalformedURLException, IOException, SQLException
	{
		String url = "https://api.deezer.com/album/"+idAlbum;
		
		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);
		JSONObject jsonComplet = new JSONObject(jsonText);
		
		JSONObject dataArtist = jsonComplet.getJSONObject("artist");
		
		int idArtist= dataArtist.getInt("id");
		boolean artistAlreadyInDB = false;
		
		for (Integer n : RequetesJDBC.listIDArtists(conn)) {
			if(n==idArtist) {
				artistAlreadyInDB = true;
			}
		}
		
		if(artistAlreadyInDB){
			return new Album(jsonComplet);
		} else {
			RequetesJDBC.createArtists(conn, artisteDeezer(idArtist));
			return new Album(jsonComplet);
		}
	}
	
	
	
	
	public static Track titreDeezer (Connection conn, String titre) throws MalformedURLException, IOException, SQLException
	{
		String url = "https://api.deezer.com/search/track/?q="+titre+"&index=0&limit=2&output=json";
		
		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);
		JSONObject jsonComplet = new JSONObject(jsonText);
		JSONArray dataTitre = jsonComplet.getJSONArray("data");
		JSONObject dataAlbum = dataTitre.getJSONObject(0).getJSONObject("album");
		
		int idAlbum= dataAlbum.getInt("id");
		boolean titreAlreadyInDB = false;
		
		for (Integer n : RequetesJDBC.listIDAlbums(conn)) {
			if(n==idAlbum) {
				titreAlreadyInDB = true;
			}
		}
		
		if(titreAlreadyInDB){
			return new Track(dataTitre);
		} else {
			RequetesJDBC.createAlbum(conn, albumDeezer(conn, idAlbum));
			return new Track(dataTitre);
		}
	}
	
}
