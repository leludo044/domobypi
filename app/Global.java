import java.net.URL;

import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.bootstrap.ApplicationContextException;
import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

	/** Config file name */
	private static final String CONFIG_DEV_FILENAME = "config_dev.json";
	private static final String CONFIG_PROD_FILENAME = "config_prod.json";

	/**
	 * On application startup
	 * 
	 * @param app
	 *            Current application
	 */
	@Override
	public void onStart(Application app) {

		Logger.info("Starting application (" + (app.isDev() ? "mode DEV" : "Mode PROD") + ")...");
		String configFilename = app.isDev() ? CONFIG_DEV_FILENAME : CONFIG_PROD_FILENAME;
		Logger.info("Config file " + configFilename + " expected.");
		ApplicationContext ac = ApplicationContext.getInstance();
		try {
			ac.load(app.resource(configFilename));
		} catch (ApplicationContextException e) {
			Logger.error("Config file " + configFilename + " reading problem : " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onStop(Application arg0) {
		Logger.info("Application has stopped.");
	}

}
