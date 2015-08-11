package net.leludo.domobypi.model.led;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public interface GpioConnected {

	void connect(final GpioPinDigitalOutput pin) ;
}
