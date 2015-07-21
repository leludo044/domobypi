package net.leludo.domobypi.model;

import java.util.ArrayList;
import java.util.List;

public class Module {

	private String id ;
	private List<Sensor> sensors ;

	public Module() {
		this.id = "undefined" ;
		this.sensors = new ArrayList<Sensor>() ;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Module [id=");
		builder.append(id);
		builder.append(", sensors=");
		builder.append(sensors);
		builder.append("]");
		return builder.toString();
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public boolean hasSensors() {
		// TODO Auto-generated method stub
		return sensors.size()>0 ;
	}	
}
