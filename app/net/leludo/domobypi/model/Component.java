package net.leludo.domobypi.model;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

import net.leludo.pi.component.PiPins;

public abstract class Component extends AbstractLed {

	private String name ;
	private ComponentFactory manager ;
	protected GpioPinDigitalOutput pin ;
	
	protected Component(ComponentFactory manager, String name) {
		this.name = name ;
		this.manager = manager ;
	}

	protected void connect(final PiPins pinNumber) {
		pin = manager.getGpioController().provisionDigitalOutputPin(pinNumber.getPin(), this.name, PinState.LOW);	
        pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
