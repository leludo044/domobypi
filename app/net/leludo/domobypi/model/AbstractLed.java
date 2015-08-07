package net.leludo.domobypi.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import net.leludo.pi.component.TemperatureSensor;

@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
	@JsonSubTypes({  
	    @Type(value = VirtualLed.class, name = "virtual")})
public abstract class AbstractLed implements Led {

	String id;
	int pinNumber;
	String type;

	public abstract void on() ;

	public abstract void off() ;

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
