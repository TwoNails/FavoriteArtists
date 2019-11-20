package deezer.model;

import org.json.JSONArray;
import org.json.JSONObject;


public class Artist {

		//fields
		private int id;
		private String name;
		private long nb_fan;
		
		//constructor
		public Artist(int id, String name, long nb_fan) {
			this.id = id;
			this.name = name;
			this.nb_fan = nb_fan;
		}
		
		public Artist(JSONArray artistjson) {
			
			//System.out.println("name : " + artistjson.getJSONObject(0).getString("name"));
			//System.out.println("id : " + artistjson.getJSONObject(0).getInt("id"));
			//System.out.println("nbfan : "+ artistjson.getJSONObject(0).getLong("nb_fan"));
			
			this.id = artistjson.getJSONObject(0).getInt("id");
			this.name = artistjson.getJSONObject(0).getString("name");
			this.nb_fan = artistjson.getJSONObject(0).getLong("nb_fan");
		}
		
		public Artist(JSONObject artistjson) {
			
			//System.out.println("name : " + artistjson.getJSONObject(0).getString("name"));
			//System.out.println("id : " + artistjson.getJSONObject(0).getInt("id"));
			//System.out.println("nb_fan : "+ artistjson.getJSONObject(0).getLong("nb_fan"));
			
			this.id = artistjson.getInt("id");
			this.name = artistjson.getString("name");
			this.nb_fan = artistjson.getLong("nb_fan");
		}


		//getter/setters/toString
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getNbFan() {
			return nb_fan;
		}

		public void setNb_fan(int nb_fan) {
			this.nb_fan = nb_fan;
		}

		@Override
		public String toString() {
			return "Artists [id=" + id + ", name=" + name + ", nbFan=" + nb_fan + "]";
		}
		
		//Methods
		
		
		
		
		
		

	}


