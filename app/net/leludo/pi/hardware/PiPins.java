package net.leludo.pi.hardware;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public enum PiPins {
	TWELVE(RaspiPin.GPIO_01, 12);
	
	private Pin pin;
	private int pinNumber ;

	PiPins(Pin pin, int pinNumber) {
		this.pin = pin;
		this.pinNumber = pinNumber;
	}
	
	public Pin getPin()  {
		return this.pin;
	}
	
	public int getPinNumber() {
		return this.pinNumber;
	}
	
	public static PiPins fromPinNumber(int pinNumber) {
		PiPins found = null ;
		for (PiPins pin : PiPins.values()) {
			if (pin.getPinNumber() == pinNumber) {
				found = pin ;
				break ;
			}
		}
		return found ;
	}
}
