package deezer.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class Track{
	
	//fields
	private int id;
	private String title;
	private int duration;
	private int favorite;
	private int id_album;
	
	//Constructors
	public Track(int id, String title) {
		this.id = id;
		this.title = title;  
	}
	

	public Track (JSONObject artistjson) {
		this.id = artistjson.getInt("id");
	    this.title = artistjson.getString("title");
	    this.duration = artistjson.getInt("duration");
	    this.favorite = 0;
	    this.id_album = artistjson.getJSONObject("album").getInt("id");
	}
	
	public Track (JSONArray artistjson) {
		this(artistjson.getJSONObject(0));
	}

	// get & set
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public int getId_album() {
		return id_album;
	}

	public void setId_album(int id_album) {
		this.id_album = id_album;
	}
}
	