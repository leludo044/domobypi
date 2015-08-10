package net.leludo.domobypi.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Module class
 *
 */
public class ModuleTest {

	/** Actor of the test */
	private Module acteur ;
	
	@Before
	public void setUp() throws Exception {
		acteur = new Module() ;
	}

	@Test
	public void testModule() {
		assertEquals("undefined", acteur.getId());
		assertNotNull(acteur.getSensors());
		assertFalse(acteur.hasSensors()) ;
		assertNotNull(acteur.getLeds());
		assertFalse(acteur.hasLeds()) ;
		assertFalse(acteur.canPersist()) ;
		assertFalse(acteur.canInitDatabase());
	}

	@Test
	public void testSetSensors() {
		acteur.setSensors(null);
		assertNotNull(acteur.getSensors());
		assertFalse(acteur.hasSensors()) ;
	}

	@Test
	public void testSetLeds() {
		acteur.setLeds(null);
		assertNotNull(acteur.getLeds());
		assertFalse(acteur.hasLeds()) ;
	}

}
