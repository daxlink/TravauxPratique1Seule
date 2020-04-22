package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandeTest {

	@Test
	public void testCommande() {
		Commande commandeTest = new Commande();
		
		assertNotNull(commandeTest);
	}

	@Test
	public void testCommandeStringStringInt() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		assertNotNull(commandeTest);
	}

	@Test
	public void testGetNom() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		assertEquals("Alain", commandeTest.getNom());
	}

	@Test
	public void testSetNom() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		commandeTest.setNom("Albert");
		
		assertEquals("Albert", commandeTest.getNom());
	}

	@Test
	public void testGetPlat() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		assertEquals("Spaghetti", commandeTest.getPlat());
	}

	@Test
	public void testSetPlat() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		commandeTest.setPlat("Lasagne");
		
		assertEquals("Lasagne", commandeTest.getPlat());
	}

	@Test
	public void testGetNbPlat() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		assertEquals(2, commandeTest.getNbPlat());
	}

	@Test
	public void testSetNbPlat() {
		Commande commandeTest = new Commande("Alain","Spaghetti", 2);
		
		commandeTest.setNbPlat(3);
		
		assertEquals(3, commandeTest.getNbPlat());
	}

}
