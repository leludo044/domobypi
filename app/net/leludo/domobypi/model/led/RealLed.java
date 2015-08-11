package net.leludo.domobypi.model.led;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Implementation of a real connected to Raspberry PI GPIO. 
 *
 */
public class RealLed extends AbstractLed implements GpioConnected {

	/** Pin the led is connected on */
	private int pinNumber ;
	
	/** Gpio from pinNumber */
	private GpioPinDigitalOutput pin ;
	
	/**
	 * Constructor
	 * @param manager
	 * @param id The led ID 
	 */
	protected RealLed(String id) {
		this.setId(id);
		this.setPinNumber(0);
	}

	@Override
	public void on() {
		pin.high();
	}

	@Override
	public void off() {
		pin.low();
	}
	
	@Override
	public boolean isOn() {
		return pin.isHigh() ;
	}

	@Override
	public String getState() {
		return isOn() ? "on" : "off";
	}
	
	public void blink(final int nb, final int seconds) throws InterruptedException {
		for (int i = 0; i<nb; i++) {
			this.on() ;
			Thread.sleep(seconds*1000);
			this.off() ;
			Thread.sleep(seconds*1000);
		}
	}
	
	/**
	 * 
	 * @return The pin number where the led is connected on
	 */
	public int getPinNumber() {
		return this.pinNumber;
	}

	/**
	 * Fix the pin number where the led must be connected on
	 * @return The new pin number
	 */
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	
	@Override
	public String getType() {
		return "real" ;
	}

	@Override
	public void connect(GpioPinDigitalOutput pin) {
		this.pin = pin ;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RealLed [id=");
		builder.append(id);
		builder.append(", pinNumber=");
		builder.append(pinNumber);
		builder.append(", pin=");
		builder.append(pin);
		builder.append("]");
		return builder.toString();
	}

}
