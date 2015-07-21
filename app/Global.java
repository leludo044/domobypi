import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.leludo.domobypi.model.Module;
import play.Application;
import play.Configuration;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

	/** Nom du fichier de propriétés */
	private static final String PROPERTY_FILENAME = "config.json";

	/**
	 * Sur événement de démarrage de l'application
	 * 
	 * @param app
	 *            l'application en cours de démarrage
	 */
	@Override
	public void onStart(Application app) {

		Logger.info("Starting application (" + (app.isDev() ? "mode DEV" : "Mode PROD") + ")...");

		ObjectMapper mapper = new ObjectMapper();
		Module module;
		try {
			module = mapper.readValue(app.resource(PROPERTY_FILENAME), Module.class);
			Logger.debug("Module found : " + module);
			Configuration conf = app.configuration();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStop(Application arg0) {
		Logger.info("Application has stopped.");
	}

}
