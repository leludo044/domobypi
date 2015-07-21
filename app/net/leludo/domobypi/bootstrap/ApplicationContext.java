package net.leludo.domobypi.bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.leludo.domobypi.model.Module;
import net.leludo.domobypi.model.Sensor;
import net.leludo.pi.component.task.SensorTask;
import play.Logger;

/**
 * Application context management
 *
 */
public class ApplicationContext {

	/** Module */
	private Module module;

	/**
	 * Constructor
	 */
	public ApplicationContext() {
		module = new Module();
	}

	/**
	 * Load context
	 * 
	 * @param configFile
	 *            Config file url
	 * @throws ApplicationContextException
	 *             Exception for config file readinf problem
	 */
	public void load(URL configFile) throws ApplicationContextException {

		if (configFile != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				module = mapper.readValue(configFile.openStream(), Module.class);
				Logger.info("Module found : " + module);
				if (!module.hasSensors()) {
					throw new ApplicationContextException("No sensor for this module !");
				} else {
					start() ;
				}
			} catch (IOException e) {
				throw new ApplicationContextException("Error while reading config.json", e);
			}
		} else {
			throw new ApplicationContextException("Config file not found !");
		}
	}
	
	private void start() {
		
		for (Sensor sensor : this.module.getSensors()) {
			final Timer t = new Timer("sensor-"+sensor.getId());
			t.schedule(new SensorTask(sensor), 0, sensor.getFrequency());
		}
	}
}
