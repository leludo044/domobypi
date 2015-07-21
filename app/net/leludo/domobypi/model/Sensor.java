package net.leludo.domobypi.model;

import net.leludo.pi.component.SensorException;

public interface Sensor {
	String getId();

	void setId(String id);

	long getFrequency();

	void setFrequency(long frequency);

	String getModel();

	void setModel(String model);

	int getMin();

	void setMin(int min);

	int getMax();

	void setMax(int max);

	String getType();

	void setType(String type);

	String read() throws SensorException;
	
	String toJson(long timestamp, String value);
}
