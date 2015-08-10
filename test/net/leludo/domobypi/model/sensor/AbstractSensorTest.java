package net.leludo.domobypi.model.sensor;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AbstractSensor class
 *
 */
public class AbstractSensorTest {

	/** Actor of the test */
	TestSensor acteur;

	@Before
	public void setUp() throws Exception {
		acteur = new TestSensor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetId() {
		assertEquals("test", acteur.getId());
	}

	@Test
	public void testGetModel() {
		assertNull(acteur.getModel());
	}

	@Test
	public void testGetMin() {
		assertEquals(0, acteur.getMin());
	}

	@Test
	public void testGetMax() {
		assertEquals(0, acteur.getMax());
	}

	@Test
	public void testGetLed() {
		assertNull(acteur.getLed());
	}

	@Test
	public void testGetModule() {
		assertNull(acteur.getModule());
	}

	@Test
	public void testSetMin_InferiorMax() {
		acteur.setMax(25);
		acteur.setMin(20);
		assertEquals(20, acteur.getMin());
	}

	@Test
	public void testSetMin_EqualsMax() {
		acteur.setMax(25);
		acteur.setMin(25);
		assertEquals(25, acteur.getMin());
	}

	@Test
	public void testSetMin_SuperiorMax() {
		acteur.setMax(25);
		acteur.setMin(30);
		assertEquals(0, acteur.getMin());
	}

	@Test
	public void testSetMax_SuperioMin() {
		acteur.setMin(20);
		acteur.setMax(25);
		assertEquals(25, acteur.getMax());
	}

	@Test
	public void testSetMax_EqualsMin() {
		acteur.setMin(20);
		acteur.setMax(20);
		assertEquals(20, acteur.getMax());
	}

	@Test
	public void testSetMax_InferiorMin() {
		acteur.setMax(25);
		acteur.setMin(20);
		acteur.setMax(15);
		assertEquals(25, acteur.getMax());
	}

	@Test
	public void testToJson() {
		Calendar cal = Calendar.getInstance();
		long timestamp = cal.getTimeInMillis();

		String expected = acteur.toJson(timestamp, "22000");
		assertEquals("{\"id\":\"test\",\"temp\":22000,\"date\":" + new Long(timestamp).toString() + "}", expected);
	}

}
