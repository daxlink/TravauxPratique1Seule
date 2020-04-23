package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientTest {

	@Test
	public void testClient() {
		Client clientTest = new Client();
		
		assertNotNull(clientTest);
		
	}

	@Test
	public void testClientString() {
		Client clientTest = new Client("Alain");
		
		assertNotNull(clientTest);
	}

	@Test
	public void testGetNom() {
		Client clientTest = new Client("Alain");
		
		assertEquals("Alain", clientTest.getNom());
	}

	@Test
	public void testSetNom() {
		Client clientTest = new Client("Alain");
		clientTest.setNom("George");
		
		assertEquals("George", clientTest.getNom());
	}

	@Test
	public void testEqualsObject() {
		Client clientTest = new Client("Alain");
		Client clientTestTrue = new Client("Alain");
		Client clientTestFalse = new Client("George"); 
		Plat platTest = new Plat("Spaghetti");
		Client clientNull = null;
		
		assertTrue(clientTest.equals(clientTestTrue));
		assertTrue(clientTest.equals(clientTest));
		
		assertFalse(clientTest.equals(clientTestFalse));
		assertFalse(clientTest.equals(platTest));
		assertFalse(clientTest.equals(clientNull));
	}

}
