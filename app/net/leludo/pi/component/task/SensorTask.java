package net.leludo.pi.component.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

import net.leludo.domobypi.dao.MesureDao;
import net.leludo.pi.component.MockSensor;
import net.leludo.pi.component.SensorException;
import play.Logger;
import play.mvc.WebSocket.Out;

public class SensorTask<T extends MockSensor> extends TimerTask {

	String name;
	String temp;
	long date;
	MesureDao dao;
	T sensor;

	Out<String> out;

	public SensorTask(T sensor, play.mvc.WebSocket.Out<String> arg1) {
		out = arg1;
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
			sb.append("{");
			sb.append("\"").append("id").append("\"");
			sb.append(":").append("\"").append(name).append("\"");
			sb.append(",");
			sb.append("\"").append("temp").append("\"");
			sb.append(":").append(temp);
			sb.append(",");
			sb.append("\"").append("date").append("\"");
			sb.append(":").append(date);
			sb.append("}");

			// System.out.println(sb.toString());
			Logger.info(sb.toString());
			out.write(sb.toString());
			dao.create(date, temp);
		} catch (SQLException e) {
			Logger.error("Persitence problem !", e);
		} catch (SensorException e) {
			Logger.error("Sensor problem !", e);
		}

	}
}
