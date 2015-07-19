package net.leludo.pi.component;

public class MockSensor implements Sensor {

	@Override
	public String read() throws SensorException {
		return new Long(Math.round(20000+(Math.random()*5000))).toString();
	}

}
