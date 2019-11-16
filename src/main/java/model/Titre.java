package model;

public class Titre{
	
	//fields
	private int pk;
	private String nom;
	private int duree;
	private int nbEcoute;
	private int note;
	private int id;

	//Constructor
	public Titre(int pk, String nom, int duree, int nbEcoute, int note, int id) {
		super();
		this.pk = pk;
		this.nom = nom;
		this.duree = duree;
		this.nbEcoute = nbEcoute;
		this.note = note;
		this.id = id;
	}

	//getter/setter/toString
	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getNbEcoute() {
		return nbEcoute;
	}

	public void setNbEcoute(int nbEcoute) {
		this.nbEcoute = nbEcoute;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	//Methods	

	@Override
	public String toString() {
		return "Titre [pk=" + pk + ", nom=" + nom + ", duree=" + duree + ", nbEcoute=" + nbEcoute + ", note=" + note
				+ ", id=" + id + "]";
	}
	
}