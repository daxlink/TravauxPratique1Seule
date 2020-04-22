package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import outilsjava.OutilsFichier;

public class Main {
	static ArrayList<Client> listeClients = new ArrayList<Client>();

	static ArrayList<Plat> listePlats = new ArrayList<Plat>();
	
	static ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
	
	public static void main(String[] args) throws IOException {
		boolean formatValide = lirelesLignes();
		
		if (formatValide) {
			System.out.println("Bienvenue chez Barette!");
			
			calculerFacture();
			
			afficherFacture();
		} else {
			System.out.println("Le fichier ne respecte pas le format demand\u00E9 !");

		}
		
	}
	
	public static boolean lirelesLignes() throws IOException {
		BufferedReader fic = OutilsFichier.ouvrirFicTexteLecture("commandes.txt");
		boolean formatValide = true;
		String ligne = "";
		
		if((ligne = fic.readLine()) != null && ligne.contains("Clients")) {
			while((ligne = fic.readLine()) != null && !ligne.contains("Plats") && formatValide) {
				formatValide = ajouterClient(ligne);
				
			}
			while((ligne = fic.readLine()) != null && !ligne.contains("Commandes") && formatValide) {
				formatValide = ajouterPlat(ligne);
				
			}
			while((ligne = fic.readLine()) != null && !ligne.contains("Fin") && formatValide) {
				formatValide = ajouterCommande(ligne);
				
			}
			
		} else {
			formatValide = false;
			
		}
		
		return formatValide;
	}
	
	public static boolean ajouterClient(String ligne) throws IOException {
		String infoClient ="[a-zA-ZÀ-ÿ]*\\ ?";
		Client client;
		boolean nomValide = true;
		
		if(ligne.matches(infoClient)) {
			client = new Client(ligne, 0.00);
			
			listeClients.add(client);
			
		} else {
			nomValide = false;
			
		}
			
		return nomValide;
	}
	
	public static boolean ajouterPlat(String ligne) throws IOException {
		String infoPlats = "[a-zA-ZÀ-ÿ\\_]*\\ [0-9]+\\.?[0-9]{0,2}";
		String[] tabPlat = new String[2];
		boolean platValide = true;
		Plat plat;
		
		if (ligne.matches(infoPlats)) {
			tabPlat = ligne.split(" ");
			
			plat = new Plat(tabPlat[0],   Double.parseDouble(tabPlat[1]));
			
			listePlats.add(plat);
			
		} else {
			platValide = false;
			
		}
			
		return platValide;
		
	}
	
	public static boolean ajouterCommande(String ligne) throws IOException {
		String infoCommandes = "[a-zA-ZÀ-ÿ]*\\ [a-zA-ZÀ-ÿ\\_]*\\ [0-9]*";
		boolean commandeValide = true;
		Commande commande;
		String cloneLigne = ligne;
		String[] tabCommande;
		
		for (int i = 1; i < ligne.length(); i++) {
			if(ligne.charAt(i-1) != ' ' && ligne.charAt(i-1) != '_' && Character.isUpperCase(ligne.charAt(i))) {
				ligne = cloneLigne.substring(0, i-1) + " " + cloneLigne.substring(i-1);
				
			}
			
		}
		
		if (ligne.matches(infoCommandes)) {
			tabCommande = ligne.split(" ");
			
			commande = new Commande(tabCommande[0], tabCommande[1], Integer.parseInt(tabCommande[2]));
			
			listeCommandes.add(commande);
			
		} else {
			commandeValide = false;

		}
			
		return commandeValide;
		
	}
	
	public static void calculerFacture() {
		int indPlat, indClient;
		double prixTotalPlat;
		
		Client clientCourant;
		Plat platCourant;
		
		for (int i = 0; i < listeCommandes.size(); i++) {
			platCourant = new Plat(listeCommandes.get(i).getPlat());
			
			indPlat = listePlats.indexOf(platCourant);
			
			if(indPlat != -1) {
				platCourant = listePlats.get(indPlat);
				
				prixTotalPlat = platCourant.getPrix() * listeCommandes.get(i).getNbPlat();
			
				clientCourant = new Client(listeCommandes.get(i).getNom());
				
				indClient = listeClients.indexOf(clientCourant);
				
				if(indClient != -1) {
					clientCourant = listeClients.get(indClient);
					
					clientCourant.setTotal(prixTotalPlat);
					
				} else {
					System.out.println("Le client " + clientCourant.getNom() + " dans la commande n'existe pas dans la liste des clients.");
					
				}
				
			} else {
				System.out.println("Le plat " + platCourant.getNom() + " de la commande n'est pas dans la liste des plats.");
				
			}
			
		}
	}

	public static void afficherFacture() {
		for (int i = 0; i < listeClients.size(); i++) {
			listeClients.get(i).afficherFactureClient();
			
		}
		
	}
}
