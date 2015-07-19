package net.leludo.pi.component.mock;

import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

import net.leludo.domobypi.dao.MesureDao;
import play.Logger;
import play.mvc.WebSocket.Out;

public class SensorTimerMock extends TimerTask {

	String name ;
	String temp;
	long date ;
	MesureDao dao ;
	
	Out<String> out ;
	
	public SensorTimerMock(play.mvc.WebSocket.Out<String> arg1) {
		out = arg1 ;
		temp = "unknown" ;
		name = "Ludo";
		dao = new MesureDao() ;
	}

	@Override
	public void run() {
		date = new Date().getTime() ;
		StringBuffer sb = new StringBuffer() ;
		temp = new Long(Math.round(20000+(Math.random()*5000))).toString();
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
		
		//System.out.println(sb.toString());
		Logger.info(sb.toString());
		out.write(sb.toString());
		try {
			dao.create(date, temp);
		} catch (SQLException e) {
			Logger.error("Persitence problem !", e);
		}
		
	}
}
