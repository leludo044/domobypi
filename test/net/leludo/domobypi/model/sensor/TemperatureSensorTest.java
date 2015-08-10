package net.leludo.domobypi.model.sensor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class for TemperatureSensor class
 *
 */
public class TemperatureSensorTest {

	/** Actor of the test */
	TemperatureSensor acteur;

	@Before
	public void setUp() throws Exception {
		acteur = new TemperatureSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testRead() throws SensorException {
		String value = acteur.read();
		int intValue = Integer.valueOf(value).intValue();
		assertTrue(intValue >= 20000);
		assertTrue(intValue <= 25000);
	}

	@Test
	public void testGetType() {
		assertEquals("temperature", acteur.getType());
	}

}
