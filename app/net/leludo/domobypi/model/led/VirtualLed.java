package net.leludo.domobypi.model.led;

import play.Logger;

public class VirtualLed extends AbstractLed {

	private String state = "off" ;

	protected VirtualLed(String id) {
		this.setId(id);
		this.setType("virtual");
		this.setPinNumber(0);
	}

	public void on() {
		Logger.debug("Led " + this.id + " on.");
		state = "on" ;
	}

	public void off() {
		Logger.debug("Led " + this.id + " off.");
		state = "off" ;
	}
	
	public String getState() {
		return state ;
	}
}
