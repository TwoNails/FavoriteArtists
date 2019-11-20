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
		
		public Artist(JSONObject artistjson) {
			this.id = artistjson.getInt("id");
			this.name = artistjson.getString("name");
			this.nb_fan = artistjson.getLong("nb_fan");
		}
		
		public Artist(JSONArray artistjson) {		
			this(artistjson.getJSONObject(0));
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


