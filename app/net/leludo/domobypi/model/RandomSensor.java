package net.leludo.domobypi.model;

import net.leludo.pi.component.SensorException;

public class RandomSensor extends AbstractSensor {

	@Override
	public String read() throws SensorException {
		return new Long(Math.round(20000 + (Math.random() * 5000))).toString();
	}

}
