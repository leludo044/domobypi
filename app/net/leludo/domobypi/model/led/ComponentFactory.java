package net.leludo.domobypi.model.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import net.leludo.pi.hardware.PiPins;
import play.Logger;

public class ComponentFactory {

	static ComponentFactory instance;
	final GpioController gpio;

	private ComponentFactory() {
		gpio = GpioFactory.getInstance();
	}

	public static final ComponentFactory getInstance() {
		if (instance == null) {
			instance = new ComponentFactory();
		}
		return instance;
	}

	public Component createComponent(final String type, final String name,
			final PiPins pin) {
		Component component = null;
		if ("real".equals(type)) {
			component = new RealLed(this, name);
			component.connect(pin);
		}
		Logger.info("Component "+name+ " of type "+type+" created.");
		return component;
	}

	public GpioController getGpioController() {
		return gpio;
	}

	public void close() {
		//gpio.shutdown();
	}
}
