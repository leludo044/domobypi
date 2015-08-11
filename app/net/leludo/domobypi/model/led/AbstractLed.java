package net.leludo.domobypi.model.led;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.leludo.pi.hardware.PiPins;

/**
 * Implementation of common Led methods
 *
 */
public abstract class AbstractLed implements Led {

	/** Led ID */
	String id;

	/**
	 * Create an instance of led
	 * 
	 * @param id
	 *            The led ID
	 * @param pinNumber
	 *            The pin number needed for real led
	 * @param type
	 *            The type of the led to create
	 * @return A led depending of the type
	 */
	@JsonCreator
	public static AbstractLed createInstance(@JsonProperty("id") String id, @JsonProperty("pinNumber") int pinNumber,
			@JsonProperty("type") String type) {
		AbstractLed led = null;
		if (type.equals("virtual")) {
			led = new VirtualLed(id);
		} else if (type.equals("real")) {
			ComponentFactory manager = ComponentFactory.getInstance();
			led = (AbstractLed) manager.createComponent(type, id, PiPins.TWELVE);
		}
		return led;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}
}
