package net.leludo.domobypi.model.sensor;

import net.leludo.domobypi.model.Module;

/**
 * Sensors stuff
 */
public interface Sensor {
	/**
	 * 
	 * @return The sensor ID
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
	 * @return The sensor read frequency in ms
	 */
	long getFrequency();

	/**
	 * Fixe the sensor read frequency
	 * 
	 * @param frequency
	 *            The new read frequency (in ms)
	 */
	void setFrequency(long frequency);

	/**
	 * 
	 * @return The sensor model
	 */
	String getModel();

	/**
	 * Fix the sensor model
	 * 
	 * @param model
	 *            The new sensor model
	 */
	void setModel(String model);

	/**
	 * Return the sensor min range value of comfort. Below this value, the
	 * sensor is out of range an can notify.
	 * 
	 * @return The sensor min range value of comfort.
	 */
	int getMin();

	/**
	 * Fix the sensor min range value of comfort. Ignored if superior to max.
	 * 
	 * @param min
	 *            The new sensor min range value of comfort.
	 */
	void setMin(int min);

	/**
	 * Return the sensor max range value of comfort. Above this value, the
	 * sensor is out of range an can notify.
	 * 
	 * @return The sensor max range value of comfort.
	 */
	int getMax();

	/**
	 * Fix the sensor max range value of comfort. Igored if inferior to min.
	 * 
	 * @param min
	 *            The new sensor max range value of comfort.
	 */
	void setMax(int max);

	/**
	 * 
	 * @return The sensor type. At the moment : random or temperature
	 */
	String getType();

	/**
	 * 
	 * @return The led ID which will be used to notify out of range. Can be
	 *         null.
	 */
	String getLed();

	/**
	 * Fix The led ID which will be used to notify out of range
	 * 
	 * @param led
	 *            The new led ID
	 */
	void setLed(String led);

	/**
	 * 
	 * @return The parent module.
	 */
	Module getModule();

	/**
	 * Fix the parent module. Can not be null.
	 */
	void setModule(Module module);

	/**
	 * Read the sensor value
	 * 
	 * @return The sensor value
	 * @throws SensorException
	 *             Exception related to sensor reading problem
	 */
	String read() throws SensorException;

	/**
	 * Convert to Json format
	 * 
	 * @param timestamp
	 *            Date of the measure
	 * @param value
	 *            Sensor value
	 * @return A Json formated string
	 */
	String toJson(long timestamp, String value);
}
