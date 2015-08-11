package net.leludo.domobypi.model.led;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Interface for all component that need to be connected to the GPIO. This is
 * used by ComponentFactory to connect a newly created real led
 * 
 * @see ComponentFactory
 *
 */
public interface GpioConnected {

	void connect(final GpioPinDigitalOutput pin);
}
