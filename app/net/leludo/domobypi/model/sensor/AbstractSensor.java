package net.leludo.domobypi.model.sensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import net.leludo.domobypi.model.Module;

/**
 * Implementation of common Sensor methods
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = RandomSensor.class, name = "random"),
		@Type(value = TemperatureSensor.class, name = "temperature") })
public abstract class AbstractSensor implements Sensor {

	/** Sensor ID */
	private String id;

	/** Sensor type */
	private String type;

	/** Sensor read frequency in ms */
	private long frequency;

	/** Sensor model */
	private String model;

	/** Sensor min range value of comfort */
	private int min;

	/** Sensor max range value of comfort */
	private int max;

	/** Led ID which will be used to notify out of range */
	private String led;

	@JsonIgnore
	private Module module;

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
		if (min <= max) {
			this.min = min;
		}
	}

	@Override
	public int getMax() {
		return max;
	}

	@Override
	public void setMax(int max) {
		if (max >= min) {
			this.max = max;
		}
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
	public abstract String read() throws SensorException;

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
		StringBuffer sb = new StringBuffer();
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

		return sb.toString();
	}
}
