package main;

public class Plat {
	String nom;
	double prix;
	
	public Plat() {
		
	}
	
	public Plat(String nom) {
		this.nom = nom;
		
	}
	
	public Plat(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
		
	}
	
	public String getNom() {
		return this.nom;
		
	}
	
	public void setNom(String nom) {
		this.nom = nom;
		
	}
	
	public double getPrix() {
		return this.prix;
		
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
		
	}
	
	@Override	
	public boolean equals( Object autreObjet ) {
		boolean egalite = false;

		// Si les deux objets pointent sur la même zone mémoire.

		if ( this == autreObjet ) {
			egalite = true;
		} else if ( autreObjet != null ) {

			if ( autreObjet instanceof Plat ) {

				// Convertir le type de l'autre objet en type Participant.
				
				Plat autre = (Plat) autreObjet;

				// Les objets sont identiques si les numéros de participant sont identiques.

				if ( this.getNom().equalsIgnoreCase( autre.getNom() ) ) {
					egalite = true;
				}
			}
		}

		return egalite;
	}

}
