package net.leludo.domobypi.model;

import play.Logger;

public class VirtualLed extends AbstractLed {


	public void on() {
		Logger.debug("Led " + this.id + " on.");
	}

	public void off() {
		Logger.debug("Led " + this.id + " off.");
	}
}
