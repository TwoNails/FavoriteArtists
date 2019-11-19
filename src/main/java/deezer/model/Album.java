package deezer.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class Album {
	
	//fields
	private int id;
	private String title;
	private int id_artist;
	
	//Constructor
	public Album(int id, String title, int id_artist) {
		this.id = id;
		this.title = title;
		this.id_artist = id_artist;
	}
	
	
	public Album(JSONObject albumjson) {
		this.id = albumjson.getInt("id");
		this.title = albumjson.getString("title");
		this.id_artist = albumjson.getJSONObject("artist").getInt("id");
	}
	
	
	
	public Album(JSONArray albumjson) {
		this(albumjson.getJSONObject(0));
	}
	
	
	
	
	//getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId_artist() {
		return id_artist;
	}

	public void setId_artist(int id_artist) {
		this.id_artist = id_artist;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", id_artist=" + id_artist + "]";
	}
	
	
	
	
	
}
