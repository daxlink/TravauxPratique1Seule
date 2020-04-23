package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import outilsjava.OutilsFichier;

public class Main {
	static ArrayList<Client> listeClients = new ArrayList<>();

	static ArrayList<Plat> listePlats = new ArrayList<>();

	static ArrayList<Commande> listeCommandes = new ArrayList<>();

	static ArrayList<String> listeErreur = new ArrayList<>();
	
	static ArrayList<String> listeFacture = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		boolean formatValide = lirelesLignes();

		if (formatValide) {

			for (int i = 0; i < listeErreur.size(); i++) {
				System.out.println(listeErreur.get(i));

			}

			System.out.println("Bienvenue chez Barette!");

			for (int i = 0; i < listeClients.size(); i++) {
				facture(listeClients.get(i));

			}
			
			ecrireFacture();

		} else {
			System.out.println("Le fichier ne respecte pas le format demand\u00E9 !");

		}

	}

	public static boolean lirelesLignes() throws IOException {
		BufferedReader fic = OutilsFichier.ouvrirFicTexteLecture("commandes.txt");
		boolean formatValide = true;
		String ligne = "";

		if ((ligne = fic.readLine()) != null && ligne.contains("Clients")) {
			while ((ligne = fic.readLine()) != null && !ligne.contains("Plats") && formatValide) {
				formatValide = ajouterClient(ligne);

			}

			while ((ligne = fic.readLine()) != null && !ligne.contains("Commandes") && formatValide) {
				formatValide = ajouterPlat(ligne);

			}

			while ((ligne = fic.readLine()) != null && !ligne.contains("Fin") && formatValide) {
				formatValide = ajouterCommande(ligne);

			}

		} else {
			formatValide = false;

		}

		return formatValide;
	}

	public static boolean ajouterClient(String ligne) throws IOException {
		String infoClient = "[a-zA-ZÀ-ÿ]*\\ ?";
		Client client;
		boolean nomValide = true;

		if (ligne.matches(infoClient)) {
			client = new Client(ligne);

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

			plat = new Plat(tabPlat[0], Double.parseDouble(tabPlat[1]));

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
			if (ligne.charAt(i - 1) != ' ' && ligne.charAt(i - 1) != '_' && Character.isUpperCase(ligne.charAt(i))) {
				ligne = cloneLigne.substring(0, i - 1) + " " + cloneLigne.substring(i - 1);

			}

		}

		if (ligne.matches(infoCommandes)) {
			tabCommande = ligne.split(" ");

			if (verifierInfoCommande(tabCommande)) {
				commande = new Commande(tabCommande[0], tabCommande[1], Integer.parseInt(tabCommande[2]));

				listeCommandes.add(commande);

			}

		} else {
			commandeValide = false;

		}

		return commandeValide;

	}

	public static boolean verifierInfoCommande(String[] tabCommande) {
		boolean bonneCommande = true;
		String ligneErreur = "";

		if (!clientPresent(tabCommande[0]) || !platPresent(tabCommande[1]) || !nbPlatCorrect(Integer.parseInt(tabCommande[2]))) {
			ligneErreur += "Dans la commande :";

			bonneCommande = false;

			for (int i = 0; i < tabCommande.length; i++) {
				ligneErreur += " " + tabCommande[i];

			}

			if (!clientPresent(tabCommande[0])) {
				ligneErreur += "\nLe client " + tabCommande[0] + " n'est pas pr\u00E9sent dans la liste des clients.";

			}

			if (!platPresent(tabCommande[1])) {
				ligneErreur += "\nLe plat " + tabCommande[1] + " n'est pas pr\u00E9sent dans la liste des plats.";

			}

			if (!nbPlatCorrect(Integer.parseInt(tabCommande[2]))) {
				ligneErreur += "\nLe nombre de plats command\u00E9s " + tabCommande[3]
						+ " est négatif ou supérieur à 10.";

			}

			ligneErreur += "\n";

			listeErreur.add(ligneErreur);
		}

		return bonneCommande;
	}

	public static boolean clientPresent(String nom) {
		Client clientTemp = new Client(nom);
		boolean estPresent = false;

		if (listeClients.indexOf(clientTemp) != -1) {
			estPresent = true;

		}

		return estPresent;
	}

	public static boolean platPresent(String nom) {
		Plat platTemp = new Plat(nom);
		boolean estPresent = false;

		if (listePlats.indexOf(platTemp) != -1) {
			estPresent = true;

		}

		return estPresent;
	}

	public static boolean nbPlatCorrect(int nbPlat) {
		boolean estCorrect = false;

		if (nbPlat > 0 && nbPlat <= 10) {
			estCorrect = true;
		}

		return estCorrect;

	}

	public static void facture(Client client) {
		final double TPS = 0.05, TVQ = 0.09975;

		String facture = "\nFacture " + client.getNom() + "\n";
		double prixPlat, totalTPS, totalTVQ;
		double total = 0;
		int indPlat;
		Plat plat;
		Commande commande;
		DecimalFormat df = new DecimalFormat("0.00");

		for (int i = 0; i < listeCommandes.size(); i++) {
			commande = listeCommandes.get(i);

			if (client.nom.equals(commande.getNom())) {
				plat = new Plat(commande.getPlat());

				indPlat = listePlats.indexOf(plat);

				plat = listePlats.get(indPlat);

				prixPlat = Double.parseDouble(df.format(commande.getNbPlat() * plat.getPrix()).replace(',', '.'));

				total += prixPlat;

				facture += "\n" + commande.getNbPlat() + " " + plat.getNom() + "\t" + df.format(prixPlat);

			}
		}

		totalTPS = Double.parseDouble(df.format(TPS * total).replace(',', '.'));
		totalTVQ = Double.parseDouble(df.format(TVQ * total).replace(',', '.'));

		facture += "\n\nSous-total\t" + df.format(total) + "\nTPS\t\t" + df.format(totalTPS) + "\nTVQ\t\t"
				+ df.format(totalTVQ);

		total += totalTPS + totalTVQ;

		facture += "\nTotal\t\t" + df.format(total) + "";

		if (total != 0) {
			System.out.println(facture);
			
			listeFacture.add(facture);

		}

	}
	
	public static void ecrireFacture() throws IOException {
		String texteFacture = "";
		
		for (int i = 0; i < listeErreur.size(); i++) {
			texteFacture += listeErreur.get(i);
			
		}
		
		for (int i = 0; i < listeFacture.size(); i++) {
			texteFacture += listeFacture.get(i);
		}
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd-hh'h'mm");
		File file = new File("facture/Facture-du-" + dateFormat.format(date) + ".txt");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texteFacture);
		bw.close();

	}

}
