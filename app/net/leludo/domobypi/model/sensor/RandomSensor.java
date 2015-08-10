package net.leludo.domobypi.model.sensor;

/**
 * Implementation of a random sensor. Value between 20000 and 25000. 
 *
 */
public class RandomSensor extends AbstractSensor {

	@Override
	public String getType() {
		return "random" ;
	}
	
	@Override
	public String read() throws SensorException {
		return new Long(Math.round(20000 + (Math.random() * 5000))).toString();
	}

}
