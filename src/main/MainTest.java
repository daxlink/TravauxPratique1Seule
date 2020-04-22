package main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MainTest {

	@Test
	public void ajouterClient() throws IOException {
		assertTrue(Main.ajouterClient("Archi"));
		assertTrue(Main.ajouterClient("Émilie"));
		
		assertFalse(Main.ajouterClient("Archi22"));
		assertFalse(Main.ajouterClient("123456"));
	}
	
	@Test
	public void ajouterPlat() throws IOException {
		assertTrue(Main.ajouterPlat("Repas_Familliale 15.50"));
		assertTrue(Main.ajouterPlat("Aile_Poulet 8"));
		
		assertFalse(Main.ajouterPlat("2Repas_Familliale"));
		assertFalse(Main.ajouterPlat("123456"));
		assertFalse(Main.ajouterPlat("123456 deux"));
		
	}
	

	@Test
	public void ajouterCommande() throws IOException {
		assertTrue(Main.ajouterCommande("JonathanRepas_Familliale 3"));
		assertTrue(Main.ajouterCommande("Alain Aile_Poulet 4"));
		
		assertFalse(Main.ajouterCommande("Albert Repas_Familliale 4.6"));
		assertFalse(Main.ajouterCommande("Jean 2 Aile"));
		
	}
	
	

}
