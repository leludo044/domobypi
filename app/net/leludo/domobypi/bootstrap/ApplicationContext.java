package net.leludo.domobypi.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.leludo.domobypi.model.Module;
import play.Configuration;
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
			Module module;
			try {
				module = mapper.readValue(configFile.openStream(), Module.class);
				Logger.info("Module found : " + module);
				if (!module.hasSensors()) {
					throw new ApplicationContextException("No sensor for this module !");
				}
			} catch (IOException e) {
				throw new ApplicationContextException("Error while reading config.json", e);
			}
		} else {
			throw new ApplicationContextException("Config file not found !");
		}
	}
}
