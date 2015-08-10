package net.leludo.domobypi.model.sensor;

import net.leludo.domobypi.model.Module;

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

	String getLed();

	void setLed(String led);

	Module getModule();

	void setModule(Module module);

	String read() throws SensorException;

	String toJson(long timestamp, String value);
}
