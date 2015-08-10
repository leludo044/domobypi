package net.leludo.domobypi.model.led;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;

import net.leludo.pi.hardware.PiPins;

public abstract class AbstractLed implements Led {

	String id;
	int pinNumber;
	String type;

	public abstract void on() ;

	public abstract void off() ;

	@JsonCreator
	public static AbstractLed createInstance(@JsonProperty("id") String id, @JsonProperty("pinNumber") int pinNumber,
			@JsonProperty("type") String type) {
		AbstractLed led = null;
		if (type.equals("virtual")) {
			led = new VirtualLed(id);
			led.setPinNumber(pinNumber);
		} else if (type.equals("real")) {
			ComponentFactory manager = ComponentFactory.getInstance() ;
		    led = (AbstractLed)manager.createComponent(type, id, PiPins.TWELVE) ;
		}
		return led ;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	@Override
	public int getPinNumber() {
		return this.pinNumber;
	}

	@Override
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimpleLed [id=");
		builder.append(id);
		builder.append(", pinNumber=");
		builder.append(pinNumber);
		builder.append("]");
		return builder.toString();
	}

}
