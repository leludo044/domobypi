import net.leludo.domobypi.bootstrap.ApplicationContext;
import net.leludo.domobypi.bootstrap.ApplicationContextException;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

public class Global extends GlobalSettings {

	/** Config file name */
	private static final String CONFIG_FILENAME = "config.json";


	/**
	 * On application startup
	 * 
	 * @param app
	 *            Current application
	 */
	@Override
	public void onStart(Application app) {

		Logger.info("Starting application (" + (app.isDev() ? "mode DEV" : "Mode PROD") + ")...");
		
		ApplicationContext ac = ApplicationContext.getInstance() ;
		try {
			ac.load(app.resource(CONFIG_FILENAME));
		} catch (ApplicationContextException e) {
			Logger.error("Config file "+CONFIG_FILENAME+" reading problem : "+e.getMessage());
			throw new RuntimeException(e);
		}		
	}

	@Override
	public void onStop(Application arg0) {
		Logger.info("Application has stopped.");
	}

}
