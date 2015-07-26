package net.leludo.pi.component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import net.leludo.domobypi.model.AbstractSensor;

public class TemperatureSensor extends AbstractSensor {

	@Override
	public String getType() {
		return "temperature";
	}

	BufferedReader br;

	@Override
	public String read() throws SensorException {
		String value;
		try {
			br = new BufferedReader(new FileReader("/sys/bus/w1/devices/28-0000063735b4/w1_slave"));
			br.readLine();
			String toto = br.readLine();
			value = toto.split(" ")[9].substring(2);
		} catch (IOException e) {
			value = "";
			throw new SensorException(e);
		}
		return value;
	}
}
