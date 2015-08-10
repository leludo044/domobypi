package net.leludo.pi.timer;

import java.util.Date;
import java.util.TimerTask;

import net.leludo.domobypi.model.sensor.SensorException;
import net.leludo.domobypi.model.sensor.TemperatureSensor;
import play.mvc.WebSocket.Out;

public class SensorTimer extends TimerTask {

	String name ;
	String temp;
	long date ;

	Out<String> out ;

	public SensorTimer(play.mvc.WebSocket.Out<String> arg1) {
		out = arg1 ;
		temp = "unknown" ;
		name = "Ludo";
	}

	@Override
	public void run() {
		try {
			date = new Date().getTime() ;
			temp = new TemperatureSensor().read();
			StringBuffer sb = new StringBuffer() ;
			sb.append("{") ;
			sb.append("\"").append("id").append("\"") ;
			sb.append(":").append("\"").append(name).append("\"");
			sb.append(",");
			sb.append("\"").append("temp").append("\"") ;
			sb.append(":").append(temp);
			sb.append(",");
			sb.append("\"").append("date").append("\"") ;
			sb.append(":").append(date);
			sb.append("}");
			System.out.println(sb.toString());
			out.write(sb.toString());
		} catch (SensorException e) {
			e.printStackTrace();
		}
	}
}
