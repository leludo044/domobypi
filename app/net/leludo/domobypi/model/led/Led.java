package net.leludo.domobypi.model.led;

/**
 * Leds stuff
 */
public interface Led {
	/**
	 * 
	 * @return The led ID
	 */
	String getId();

	/**
	 * Fix the sensor ID
	 * 
	 * @param id
	 *            The new sensor ID
	 */
	void setId(String id);

	/**
	 * 
	 * @return The sensor type. At the moment : virtual or real
	 */
	String getType();

	/**
	 * Light on the led
	 */
	void on();

	/**
	 * Light off the led
	 */
	void off();

	/**
	 * 
	 * @return If the led is on (true) or not (false)
	 */
	boolean isOn();

	/**
	 * 
	 * @return The led state : "on" or "off"
	 */
	public String getState();
}
