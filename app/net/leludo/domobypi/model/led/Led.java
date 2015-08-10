package net.leludo.domobypi.model.led;

public interface Led {
	String getId();

	void setId(String id);

	int getPinNumber();

	void setPinNumber(int pinNumber);

	String getType();

	void setType(String type);
	
	void on() ;

	void off() ;
	
	public String getState() ;
}
