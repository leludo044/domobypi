package net.leludo.pi.component.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import net.leludo.domobypi.dao.MesureDao;
import net.leludo.domobypi.model.Sensor;
import net.leludo.pi.component.SensorException;
import play.Logger;
import play.mvc.WebSocket;
import play.mvc.WebSocket.Out;

public class SensorTask extends TimerTask {

	String name;
	String temp;
	long date;
	MesureDao dao;
	Sensor sensor;

	List<WebSocket.Out<String>> sockets ;

	public SensorTask(Sensor sensor, List<WebSocket.Out<String>> sockets) {
		this.sockets = sockets;
		temp = "unknown";
		name = "Ludo";
		dao = new MesureDao();
		this.sensor = sensor;
	}

	@Override
	public void run() {
		date = new Date().getTime();
		String message ;
		try {
			temp = sensor.read();
			message = sensor.toJson(date, temp);
			// System.out.println(sb.toString());
			if (Logger.isDebugEnabled()) {
				Logger.debug(message);
			}
			if (!this.sockets.isEmpty()) {
				this.sockets.get(0).write(message);
			}
			dao.create(sensor.getType(), date, temp);
		} catch (SQLException e) {
			Logger.error("Persitence problem !", e);
		} catch (SensorException e) {
			Logger.error("Sensor problem !", e);
		}

	}
}
