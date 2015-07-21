package net.leludo.domobypi.model;

public class Sensor {
	private String id;
	private String type;
	private long frequency;
	private String model;
	private int min;
	private int max;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getFrequency() {
		return frequency;
	}
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
}
