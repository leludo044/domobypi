package net.leludo.domobypi.model.led;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for VirtualLed class
 *
 */
public class VirtualLedTest {

	/** Actor of the test */
	VirtualLed acteur;

	@Before
	public void setUp() throws Exception {
		acteur = new VirtualLed("test");
	}

	@Test
	public void testVirtualLed() {
		assertEquals("test", acteur.getId());
		assertFalse(acteur.isOn());
	}

	@Test
	public void testOn() {
		acteur.on();
		assertEquals("on", acteur.getState());
		assertTrue(acteur.isOn());
	}

	@Test
	public void testOff() {
		acteur.off();
		assertEquals("off", acteur.getState());
		assertFalse(acteur.isOn());
	}

	@Test
	public void testGetType() {
		assertEquals("virtual", acteur.getType());
	}
}
