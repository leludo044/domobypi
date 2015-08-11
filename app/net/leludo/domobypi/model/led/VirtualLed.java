package net.leludo.domobypi.model.led;

import play.Logger;

/**
 * Implementation of a virtual led. No dependence with any hardware. For test purpose. 
 *
 */
public class VirtualLed extends AbstractLed {

	/** Led state */
	private String state ;

	/**
	 * Constructor. By default the led is off.
	 * @param id Led ID
	 */
	protected VirtualLed(String id) {
		this.setId(id);
		this.state = "off";
	}

	@Override
	public void on() {
		Logger.debug("Led " + this.id + " on.");
		state = "on" ;
	}

	@Override
	public void off() {
		Logger.debug("Led " + this.id + " off.");
		state = "off" ;
	}
	
	@Override
	public boolean isOn() {
		return state.equals("on") ;
	}
	
	
	@Override
	public String getState() {
		return state ;
	}
	
	@Override
	public String getType() {
		return "virtual" ;
	}
}
