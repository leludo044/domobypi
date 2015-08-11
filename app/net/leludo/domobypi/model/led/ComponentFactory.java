package net.leludo.domobypi.model.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

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

	public Led createComponent(final String type, final String id,
			final PiPins pin) {
		Led component = null;
		if ("real".equals(type)) {
			component = new RealLed(id);
			this.connect((RealLed)component, component.getId(), pin);
		}
		Logger.info("Component "+id+ " of type "+type+" created.");
		return component;
	}

	private void connect(GpioConnected component, String name, PiPins pinNumber) {
		GpioPinDigitalOutput pin = this.gpio.provisionDigitalOutputPin(pinNumber.getPin(), name, PinState.LOW);
		pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		component.connect(pin);
	}
	
	
	public GpioController getGpioController() {
		return gpio;
	}

	public void close() {
		//gpio.shutdown();
	}
}
