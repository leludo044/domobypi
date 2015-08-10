package net.leludo.domobypi.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.leludo.domobypi.model.led.AbstractLed;
import net.leludo.domobypi.model.sensor.AbstractSensor;
import play.Logger;

/**
 * domobyPi box Configuration : a module
 *
 */
public class Module {

	/** The module ID */
	private String id;

	/** Mark if the module provide persistence stuff */
	private boolean persistence;

	/**
	 * Mark if the module can initialize the database itself (all data will be
	 * lost)
	 */
	private boolean initDatabase;

	/** Sensors list */
	private List<AbstractSensor> sensors;

	/** Leds list */
	private Map<String, AbstractLed> leds;

	/**
	 * Constructor. By default : no persistence, can not initialize database
	 * itself, sensors list is created with zero sensor, leds list is created
	 * with zero led and the id is "undefined"
	 */
	public Module() {
		this.id = "undefined";
		this.sensors = new ArrayList<AbstractSensor>();
		this.persistence = false;
		this.initDatabase = false;
		this.leds = new Hashtable<String, AbstractLed>();
	}

	/**
	 * 
	 * @return The module ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Fix the module ID
	 * 
	 * @param id
	 *            The new module ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The sensors list (can not be null)
	 */
	public List<AbstractSensor> getSensors() {
		return sensors;
	}

	/**
	 * Fix the sensors list
	 * 
	 * @param sensors
	 *            The new sensors list. If null, do nothing
	 */
	public void setSensors(List<AbstractSensor> sensors) {
		if (sensors != null) {
			this.sensors = sensors;
			for (AbstractSensor sensor : sensors) {
				sensor.setModule(this);
			}
		}
	}

	/**
	 * Say if the module contains sensors
	 * 
	 * @return true or false
	 */
	public boolean hasSensors() {
		return sensors.size() > 0;
	}

	/**
	 * 
	 * @return The leds list (can not be null)
	 */
	public Map<String, AbstractLed> getLeds() {
		return leds;
	}

	/**
	 * Fix the leds list
	 * 
	 * @param leds
	 *            The new leds list. If null, do nothing
	 */
	public void setLeds(List<AbstractLed> leds) {
		if (leds != null) {
			for (AbstractLed led : leds) {
				this.leds.put(led.getId(), led);
			}
		}
	}

	/**
	 * Say if the module contains leds
	 * 
	 * @return true or false
	 */
	public boolean hasLeds() {
		return leds.size() > 0;
	}

	/**
	 * Say if the module can provide persistence stuff
	 * 
	 * @return true ou false
	 */
	public boolean canPersist() {
		return persistence;
	}

	/**
	 * Fix if the module can provide persistence stuff
	 * 
	 * @param persistence
	 *            true or false
	 */
	public void setPersistence(boolean persistence) {
		this.persistence = persistence;
		if (this.persistence) {
			Logger.info("Persistence enabled.");
		} else {
			Logger.info("Persistence disabled.");
		}
	}

	/**
	 * Say if the module can initialize the database itself.
	 * 
	 * @return true ou false
	 */
	public boolean canInitDatabase() {
		return initDatabase;
	}

	/**
	 * Fix if the module can initialize the database itself. If true all the
	 * data will be lost at startup
	 * 
	 * @param initDatabase
	 *            true or false
	 */
	public void setInitDatabase(boolean initDatabase) {
		this.initDatabase = initDatabase;
		if (this.initDatabase) {
			Logger.info("Data initialization enabled.");
		} else {
			Logger.info("Data initialization disabled.");
		}
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
		builder.append(", canPersist=");
		builder.append(canPersist());
		builder.append(", canInitDatabase=");
		builder.append(canInitDatabase());
		builder.append("]");
		return builder.toString();
	}
}
