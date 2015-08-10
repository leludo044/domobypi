package net.leludo.domobypi.model.sensor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RandomSensor class
 *
 */
public class RandomSensorTest {

	/** Actor of the test */
	RandomSensor acteur;

	@Before
	public void setUp() throws Exception {
		acteur = new RandomSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() throws SensorException {
		String value = acteur.read();
		int intValue = Integer.valueOf(value).intValue();
		assertTrue(intValue >= 20000);
		assertTrue(intValue <= 25000);
	}

	@Test
	public void testGetType() {
		assertEquals("random", acteur.getType());
	}

}
