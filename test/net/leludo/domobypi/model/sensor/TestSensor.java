package net.leludo.domobypi.model.sensor;

/**
 * Implementation of a test sensor for test purpose only
 *
 */
public class TestSensor extends AbstractSensor {

	/**
	 * Constructor
	 */
	public TestSensor() {
		this.setId("test");
	}
	
	
	@Override
	public String getType() {
		return "test";
	}

	@Override
	public String read() throws SensorException {
		return "0";
	}

}
