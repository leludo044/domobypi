package net.leludo.pi.component.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import net.leludo.domobypi.dao.Dao;
import net.leludo.domobypi.model.Sensor;
import net.leludo.pi.component.SensorException;
import play.Logger;
import play.mvc.WebSocket;
import play.mvc.WebSocket.Out;

public class SensorTask extends TimerTask {

	String name;
	String value;
	long date;
	Dao dao;
	Sensor sensor;
	boolean canPersists;

	List<WebSocket.Out<String>> sockets;

	public SensorTask(Sensor sensor, List<WebSocket.Out<String>> sockets, boolean canPersists) {
		this.sockets = sockets;
		value = "unknown";
		name = "Ludo";
		dao = new Dao();
		this.sensor = sensor;
		this.canPersists = canPersists;
	}

	@Override
	public void run() {
		date = new Date().getTime();
		String message;
		try {
			value = sensor.read();
			message = sensor.toJson(date, value);
			if (Logger.isDebugEnabled()) {
				Logger.debug(message);
			}
			if (!this.sockets.isEmpty()) {
				for (Out<String> out : this.sockets) {
					out.write(message);
				}
			}
			if (this.canPersists) {
				dao.create(sensor.getType(), date, value);
			}
			double humanValue = Math.round(Integer.valueOf(value) / 10) / 100.0;
			if (humanValue > sensor.getMax()) {
				Logger.debug("Too hot !!!!!");
			} else if (humanValue < sensor.getMin()) {
				Logger.debug("Too cold !!!!!");
			} else {
				Logger.debug("Normal !!!!!");
			}

		} catch (SQLException e) {
			Logger.error("Persitence problem !", e);
		} catch (SensorException e) {
			Logger.error("Sensor problem !", e);
		}

	}
}
