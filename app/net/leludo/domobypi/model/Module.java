package net.leludo.domobypi.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.leludo.domobypi.model.led.AbstractLed;
import net.leludo.domobypi.model.sensor.AbstractSensor;
import play.Logger;

public class Module {

	private String id;
	private boolean persistence;
	private boolean initDatabase;

	private List<AbstractSensor> sensors;
	
	private Map<String, AbstractLed> leds ;

	public Module() {
		this.id = "undefined";
		this.sensors = new ArrayList<AbstractSensor>();
		this.persistence = false;
		this.initDatabase = false;
		this.leds = new Hashtable<String, AbstractLed>() ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Module [id=");
		builder.append(id);
		builder.append(", sensors=");
		builder.append(sensors);
		builder.append(", leds=");
		builder.append(leds);
		builder.append("]");
		return builder.toString();
	}

	public List<AbstractSensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<AbstractSensor> sensors) {
		this.sensors = sensors;
		for (AbstractSensor sensor : sensors) {
			sensor.setModule(this);
		}
	}

	public boolean hasSensors() {
		return sensors.size() > 0;
	}

	public Map<String, AbstractLed> getLeds() {
		return leds;
	}

	public void setLeds(List<AbstractLed> leds) {
		for (AbstractLed led : leds) {
			this.leds.put(led.getId(), led);
		}
	}

	public boolean hasLeds() {
		return leds.size() > 0;
	}

	public boolean canPersists() {
		return persistence;
	}

	public void setPersistence(boolean persistence) {
		this.persistence = persistence;
		if (this.persistence) {
			Logger.info("Persistence enabled.");
		} else {
			Logger.info("Persistence disabled.");
		}
	}

	public boolean canInitDatabase() {
		return initDatabase;
	}

	public void setInitDatabase(boolean initDatabase) {
		this.initDatabase = initDatabase;
		if (this.initDatabase) {
			Logger.info("Data initialization enabled.");
		} else {
			Logger.info("Data initialization disabled.");
		}
	}

}
