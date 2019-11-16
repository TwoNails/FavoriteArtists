package model;



	public class Artists {

		//fields
		private int id;
		private String nom;
		private int nbFan;
		
		//constructor
		public Artists(int id, String nom, int nbFan) {
			super();
			this.id = id;
			this.nom = nom;
			this.nbFan = nbFan;
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

		public int getNbFan() {
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


