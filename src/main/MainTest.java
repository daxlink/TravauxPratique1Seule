package main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MainTest {

	@Test
	public void testAjouterClient() throws IOException {
		assertTrue(Main.ajouterClient("Alain"));
		
		assertFalse(Main.ajouterClient("Alain22"));
		assertFalse(Main.ajouterClient("54222"));
		assertFalse(Main.ajouterClient("22.04"));
		
	}

	@Test
	public void testAjouterPlat() throws IOException {
		assertTrue(Main.ajouterPlat("Pizza 10.50"));
		assertTrue(Main.ajouterPlat("Pizza 10"));
		
		assertFalse(Main.ajouterPlat("Alain22"));
		assertFalse(Main.ajouterPlat("10 Pizza"));
		assertFalse(Main.ajouterPlat("22.04"));
	}

	@Test
	public void testAjouterCommande() throws IOException {
		assertTrue(Main.ajouterCommande("Alain Pizza 2"));
		assertTrue(Main.ajouterCommande("AlainPizza 2"));
		assertTrue(Main.ajouterCommande("Alain Aile_Poulet 2"));
		
	}

	@Test
	public void testNbPlatCorrect() {
		assertTrue(Main.nbPlatCorrect(3));
		
		assertFalse(Main.nbPlatCorrect(-4));
		assertFalse(Main.nbPlatCorrect(22));
	}

}
