package net.leludo.domobypi.model.led;

public class RealLed extends Component {

	protected RealLed(ComponentFactory manager, String name) {
		super(manager, name);
		this.setId(name);
	}

	public void on() {
		pin.high();
	}

	public void off() {
		pin.low();
	}
	
	public boolean isOn() {
		return pin.isHigh() ;
	}

	public String getState() {
		return isOn() ? "on" : "off";
	}
	
	public void blink(final int nb, final int seconds) throws InterruptedException {
		for (int i = 0; i<nb; i++) {
			this.on() ;
			Thread.sleep(seconds*1000);
			this.off() ;
			Thread.sleep(seconds*1000);
		}
	}
}
