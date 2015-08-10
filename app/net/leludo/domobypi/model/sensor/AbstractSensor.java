package net.leludo.domobypi.model.sensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import net.leludo.domobypi.model.Module;

@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
	@JsonSubTypes({  
	    @Type(value = RandomSensor.class, name = "random"),
	    @Type(value = TemperatureSensor.class, name = "temperature")})
public abstract class AbstractSensor implements Sensor {
	private String id;
	private String type;
	private long frequency;
	private String model;
	private int min;
	private int max;
	private String led ;
	
	@JsonIgnore
	private Module module ;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public long getFrequency() {
		return frequency;
	}

	@Override
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public int getMin() {
		return min;
	}

	@Override
	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public int getMax() {
		return max;
	}

	@Override
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getLed() {
		return led;
	}

	@Override
	public void setLed(String led) {
		this.led = led;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public abstract String read() throws SensorException ;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sensor [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", frequency=");
		builder.append(frequency);
		builder.append(", model=");
		builder.append(model);
		builder.append(", min=");
		builder.append(min);
		builder.append(", max=");
		builder.append(max);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public String toJson(long timestamp, String value) {
		StringBuffer sb = new StringBuffer() ;
		sb.append("{");
		sb.append("\"").append("id").append("\"");
		sb.append(":").append("\"").append(id).append("\"");
		sb.append(",");
		sb.append("\"").append("temp").append("\"");
		sb.append(":").append(value);
		sb.append(",");
		sb.append("\"").append("date").append("\"");
		sb.append(":").append(timestamp);
		sb.append("}");
		
		return sb.toString() ;
	}
}
