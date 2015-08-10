package net.leludo.domobypi.model;

import play.Logger;

public class VirtualLed extends AbstractLed {

	private String state = "off" ;

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
