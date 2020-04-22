package main;

import java.text.DecimalFormat;

public class Client {
	String nom;
	double total;
	
	public Client() {
		
	}
	
	public Client(String nom) {
		this.nom = nom;
		
	}
	
	public Client(String nom, double total) {
		this.nom = nom;
		this.total = total;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void afficherFactureClient() {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(this.nom + " " + df.format(this.total) + "$");
	}
	
	@Override	
	public boolean equals( Object autreObjet ) {
		boolean egalite = false;

		// Si les deux objets pointent sur la même zone mémoire.

		if ( this == autreObjet ) {
			egalite = true;
		} else if ( autreObjet != null ) {

			if ( autreObjet instanceof Client ) {

				// Convertir le type de l'autre objet en type Participant.
				
				Client autre = (Client) autreObjet;

				// Les objets sont identiques si les numéros de participant sont identiques.

				if ( this.getNom().equalsIgnoreCase( autre.getNom() ) ) {
					egalite = true;
				}
			}
		}

		return egalite;
	}

}
