package main;

public class Client {
	String nom;
	double total;
	
	public Client() {
		
	}
	
	public Client(String nom) {
		this.nom = nom;
		
	}
	
	public String getNom() {
		return this.nom;
		
	}
	
	public void setNom(String nom) {
		this.nom = nom;
		
	}
	
	@Override	
	public boolean equals( Object autreObjet ) {
		boolean egalite = false;

		if ( this == autreObjet ) {
			egalite = true;
		} else if ( autreObjet != null ) {

			if ( autreObjet instanceof Client ) {

				Client autre = (Client) autreObjet;

				if ( this.getNom().equalsIgnoreCase( autre.getNom() ) ) {
					egalite = true;
				}
			}
		}

		return egalite;
	}

}
