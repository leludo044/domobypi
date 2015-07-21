package net.leludo.pi.component.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

import net.leludo.domobypi.dao.MesureDao;
import net.leludo.domobypi.model.Sensor;
import net.leludo.pi.component.SensorException;
import play.Logger;
import play.mvc.WebSocket.Out;

public class SensorTask extends TimerTask {

	String name;
	String temp;
	long date;
	MesureDao dao;
	Sensor sensor;

	Out<String> out;

	public SensorTask(Sensor sensor, play.mvc.WebSocket.Out<String> arg1) {
		out = arg1;
		temp = "unknown";
		name = "Ludo";
		dao = new MesureDao();
		this.sensor = sensor;
	}

	public SensorTask(Sensor sensor) {
		out = null;
		temp = "unknown";
		name = "Ludo";
		dao = new MesureDao();
		this.sensor = sensor;
	}

	@Override
	public void run() {
		date = new Date().getTime();
		StringBuffer sb = new StringBuffer();
		try {
			temp = sensor.read();

			// System.out.println(sb.toString());
			if (Logger.isDebugEnabled()) {
				Logger.debug(sensor.toJson(date, temp));
			}
			if (out != null) {
				out.write(sb.toString());
			}
			dao.create(sensor.getType(), date, temp);
		} catch (SQLException e) {
			Logger.error("Persitence problem !", e);
		} catch (SensorException e) {
			Logger.error("Sensor problem !", e);
		}

	}
}
