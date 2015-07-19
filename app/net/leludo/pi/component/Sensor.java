package net.leludo.pi.component;

public interface Sensor {

	String read() throws SensorException;
}
