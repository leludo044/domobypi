package net.leludo.domobypi.bootstrap;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.leludo.domobypi.dao.Dao;
import net.leludo.domobypi.model.AbstractSensor;
import net.leludo.domobypi.model.Module;
import net.leludo.domobypi.model.Sensor;
import net.leludo.pi.component.task.SensorTask;
import play.Logger;
import play.mvc.WebSocket;

/**
 * Application context management
 *
 */
public final class ApplicationContext {

	static ApplicationContext instance ;
	
	List<WebSocket.Out<String>> sockets ;
	
	/** Module */
	private Module module;

	/**
	 * Constructor
	 */
	private ApplicationContext() {
		module = new Module();
		sockets = new ArrayList<WebSocket.Out<String>>() ;
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
				if (module.canInitDatabase()) {
					new Dao().initDatabase();
				}
				if (!module.hasLeds()) {
					Logger.warn("No led for this module !");					
				}
				if (!module.hasSensors()) {
					throw new ApplicationContextException("No sensor for this module !");
				} else {
					start() ;
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
			final Timer t = new Timer("sensor-"+sensor.getId());
			t.schedule(new SensorTask(sensor, this.sockets, module.canPersists()), 0, sensor.getFrequency());
		}
	}
	
	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext() ;
		}
		return instance ;
	}
	
	public void register(WebSocket.Out<String> ws) {
		sockets.add(ws) ;
		Logger.info("Registering "+ws.toString());
	}

	public void unregister(WebSocket.Out<String> ws) {
		this.sockets.remove(ws) ;
		Logger.info("Unregistering "+ws.toString());
	}

	public List<AbstractSensor> getSensors() {
		return module.getSensors();
		
	}
}
