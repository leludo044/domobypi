import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.bootstrap.ApplicationContextException;
import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

	/** Config file name */
	private static final String CONFIG_PREFIX_FILENAME = "config_";
	private static final String CONFIG_EXTENSION_FILENAME = ".json";

	String runningMode ; 
	
	/**
	 * On application startup
	 * 
	 * @param app
	 *            Current application
	 */
	@Override
	public void onStart(Application app) {

		this.detectRunningMode(app);
		Logger.info("Starting application (mode "+ this.runningMode + ")...");
		String configFilename = CONFIG_PREFIX_FILENAME+this.runningMode+CONFIG_EXTENSION_FILENAME;
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
	
	private void detectRunningMode(Application app) {
		if (app.isDev()) {
			this.runningMode = "dev" ;
		} else if (app.isProd()) {
			this.runningMode = "prod" ;			
		} else if (app.isTest()) {
			this.runningMode = "test" ;			
		} else {
			this.runningMode = "unknows" ;			
		}
	}

}
