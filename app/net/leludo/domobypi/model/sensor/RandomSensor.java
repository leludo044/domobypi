package net.leludo.domobypi.model.sensor;

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
