package deezer.model;

import org.json.JSONArray;
import org.json.JSONObject;


public class Artist {

		//fields
		private int id;
		private String nom;
		private long nbFan;
		
		//constructor
		public Artist(int id, String nom, long nbFan) {
			this.id = id;
			this.nom = nom;
			this.nbFan = nbFan;
		}
		
		public Artist(JSONArray artistjson) {
			
			//System.out.println("name : " + artistjson.getJSONObject(0).getString("name"));
			//System.out.println("id : " + artistjson.getJSONObject(0).getInt("id"));
			//System.out.println("nbfan : "+ artistjson.getJSONObject(0).getLong("nb_fan"));
			
			this.id = artistjson.getJSONObject(0).getInt("id");
			this.nom = artistjson.getJSONObject(0).getString("name");
			this.nbFan = artistjson.getJSONObject(0).getLong("nb_fan");
		}
		
		public Artist(JSONObject artistjson) {
			
			//System.out.println("name : " + artistjson.getJSONObject(0).getString("name"));
			//System.out.println("id : " + artistjson.getJSONObject(0).getInt("id"));
			//System.out.println("nbfan : "+ artistjson.getJSONObject(0).getLong("nb_fan"));
			
			this.id = artistjson.getInt("id");
			this.nom = artistjson.getString("name");
			this.nbFan = artistjson.getLong("nb_fan");
		}


		//getter/setters/toString
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public long getNbFan() {
			return nbFan;
		}

		public void setNbFan(int nbFan) {
			this.nbFan = nbFan;
		}

		@Override
		public String toString() {
			return "Artists [id=" + id + ", nom=" + nom + ", nbFan=" + nbFan + "]";
		}
		
		//Methods
		
		
		
		
		
		

	}


