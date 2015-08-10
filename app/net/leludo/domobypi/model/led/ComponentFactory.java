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
			Logger.debug("Component factory created...");
		}
		return instance;
	}

	public Component createComponent(final String type, final String name,
			final PiPins pin) {
		Logger.debug("Name="+name+ ", type="+type);
		Component component = null;
		if ("real".equals(type)) {
			component = new RealLed(this, name);
			component.connect(pin);
		}
		return component;
	}

//	public Led createLed(final String type, final String name,
//			final PiPins pin) {
//		Logger.debug("Name="+name+ ", type="+type);
//		Led led = null;
//		if ("real".equals(type)) {
//			led = new RealLed(this, name);
//			((Component)led).connect(pin);
//		} else if ("virtual".equals(type)) {
//			led = new VirtualLed(name) ;
//		}
//		return led;
//	}

	public GpioController getGpioController() {
		return gpio;
	}

	public void close() {
		//gpio.shutdown();
	}
}
