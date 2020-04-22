package main;

public class Commande {
	String nom, plat;
	int nbPlat;
	
	public Commande(){
		
	}
	
	public Commande(String nom, String plat, int nbPlat) {
		this.nom = nom;
		this.plat = plat;
		this.nbPlat = nbPlat;
		
	}
	
	public String getNom() {
		return this.nom;
		
	}
	
	public void setNom(String nom) {
		this.nom = nom;
		
	}
	
	public String getPlat() {
		return this.plat;
		
	}
	
	public void setPlat(String plat) {
		this.plat = plat;
		
	}
	
	public int getNbPlat() {
		return this.nbPlat;
		
	}
	
	public void setNbPlat(int nbPlat) {
		this.nbPlat = nbPlat;
		
	}

}
