package net.leludo.domobypi.model;

import java.util.ArrayList;
import java.util.List;

public class Module {

	private String id ;

	private List<AbstractSensor> sensors ;

	public Module() {
		this.id = "undefined" ;
		this.sensors = new ArrayList<AbstractSensor>() ;
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

	public List<AbstractSensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<AbstractSensor> sensors) {
		this.sensors = sensors;
	}

	public boolean hasSensors() {
		// TODO Auto-generated method stub
		return sensors.size()>0 ;
	}	
}
