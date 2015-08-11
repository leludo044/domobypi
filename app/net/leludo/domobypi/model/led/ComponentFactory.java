package net.leludo.domobypi.model.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

import net.leludo.pi.hardware.PiPins;
import play.Logger;

/**
 * Factory for real led connected to hardware
 */
public class ComponentFactory {

	/** Factory instance */
	static ComponentFactory instance;
	
	/** GPIO Controller to use */
	final GpioController gpio;

	/**
	 * Constructor. The factory can not be instanciated. It's a singleton
	 */
	private ComponentFactory() {
		gpio = GpioFactory.getInstance();
	}

	/**
	 * 
	 * @return The unique factory instance
	 */
	public static final ComponentFactory getInstance() {
		if (instance == null) {
			instance = new ComponentFactory();
		}
		return instance;
	}

	/**
	 * Create a real led
	 * @param type Led type to create
	 * @param id Led ID 
	 * @param pin Pin number the led is connected on. Pin number is the sequential from 1 to 24
	 * @return The real led
	 */
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

	/**
	 * Connect a led to a GPIO related to the pin number
	 * @param component The led 
	 * @param name The name associated to the GPIO
	 * @param pinNumber The pin number
	 */
	private void connect(GpioConnected component, String name, PiPins pinNumber) {
		GpioPinDigitalOutput pin = this.gpio.provisionDigitalOutputPin(pinNumber.getPin(), name, PinState.LOW);
		pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		component.connect(pin);
	}	
}
