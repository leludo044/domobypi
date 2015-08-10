package net.leludo.domobypi.bootstrap;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.leludo.domobypi.dao.Dao;
import net.leludo.domobypi.model.Module;
import net.leludo.domobypi.model.led.AbstractLed;
import net.leludo.domobypi.model.sensor.AbstractSensor;
import net.leludo.domobypi.model.sensor.Sensor;
import net.leludo.pi.component.task.SensorTask;
import play.Logger;
import play.mvc.WebSocket;

/**
 * Root class for model management (configuration, sensors and leds description)
 *
 */
public final class ApplicationContext {

	/** Unique instance of the application */
	static ApplicationContext instance;

	/** Open websockets lists */
	List<WebSocket.Out<String>> sockets;

	/** Module */
	private Module module;

	/**
	 * Constructor. By default the module is empty and the sockets list is
	 * created with zero element
	 */
	private ApplicationContext() {
		module = new Module();
		sockets = new ArrayList<WebSocket.Out<String>>();
	}

	/**
	 * Load context form a json config file
	 * 
	 * @param configFile
	 *            Config file url
	 * @throws ApplicationContextException
	 *             Exception for config file reading problem
	 */
	public void load(URL configFile) throws ApplicationContextException {

		if (configFile != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				module = mapper.readValue(configFile.openStream(), Module.class);
				Logger.info("Module found : " + module);
				if (module.canInitDatabase()) {
					new Dao().initDatabase();
				}
				if (!module.hasLeds()) {
					Logger.warn("No led for this module !");
				}
				if (!module.hasSensors()) {
					throw new ApplicationContextException("No sensor for this module !");
				} else {
					start();
				}
			} catch (IOException e) {
				throw new ApplicationContextException("Error while reading config.json", e);
			} catch (SQLException e) {
				throw new ApplicationContextException("Error while initializing database", e);
			}
		} else {
			throw new ApplicationContextException("Config file not found !");
		}
	}

	private void start() {

		for (Sensor sensor : this.module.getSensors()) {
			final Timer t = new Timer("sensor-" + sensor.getId());
			t.schedule(new SensorTask(sensor, this.sockets, module.canPersist()), 0, sensor.getFrequency());
		}
	}

	/**
	 * Return the unique instance. Create one if not exists.
	 * 
	 * @return The unique instance
	 */
	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}

	/**
	 * Register a websocket opened by an HTML client
	 * 
	 * @param ws
	 *            The websocket to register
	 */
	public void register(WebSocket.Out<String> ws) {
		sockets.add(ws);
		Logger.info("Registering " + ws.toString());
	}

	/**
	 * Unregister a websocket closed by an HTML client
	 * 
	 * @param ws
	 *            The websocket to unregister
	 */
	public void unregister(WebSocket.Out<String> ws) {
		this.sockets.remove(ws);
		Logger.info("Unregistering " + ws.toString());
	}

	/**
	 * @return The sensors list associated with this application context
	 */
	public List<AbstractSensor> getSensors() {
		return module.getSensors();

	}

	/**
	 * @return The leds list associated with this application context
	 */
	public Map<String, AbstractLed> getLeds() {
		return module.getLeds();
	}
}
