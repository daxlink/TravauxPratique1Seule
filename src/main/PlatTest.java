package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlatTest {

	@Test
	public void testPlat() {
		Plat platTest = new Plat();
		
		assertNotNull(platTest);
	}

	@Test
	public void testPlatString() {
		Plat platTest = new Plat("Spaghetti");
		
		assertNotNull(platTest);
	}

	@Test
	public void testPlatStringDouble() {
		Plat platTest = new Plat("Spaghetti", 7.50);
		
		assertNotNull(platTest);
	}

	@Test
	public void testGetNom() {
		Plat platTest = new Plat("Spaghetti");
		
		assertEquals("Spaghetti", platTest.getNom());
	}

	@Test
	public void testSetNom() {
		Plat platTest = new Plat("Spaghetti");
		platTest.setNom("Lasagne");
		
		assertEquals("Lasagne", platTest.getNom());
	}

	@Test
	public void testGetPrix() {
		Plat platTest = new Plat("Spaghetti", 7.5);
		
		assertEquals(7.5, platTest.getPrix(), 0.01);
	}

	@Test
	public void testSetPrix() {
		Plat platTest = new Plat("Spaghetti", 7.5);
		platTest.setPrix(8.00);
		
		assertEquals(8, platTest.getPrix(), 0.01);
	}

	@Test
	public void testEqualsObject() {
		Plat platTest = new Plat("Spaghetti");
		Plat platTestTrue = new Plat("Spaghetti", 8);
		Plat platTestFalse = new Plat("lasagne", 7);
		Plat platNull = null;
		assertTrue(platTest.equals(platTestTrue));
		assertTrue(platTest.equals(platTest));
		
		assertFalse(platTest.equals(platTestFalse));
		assertFalse(platTest.equals(platNull));
		
	}

}
