package net.leludo.domobypi.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;
import com.jcabi.jdbc.SingleOutcome;

import play.db.DB;

public class MesureDao {
	DataSource ds;

	public void create(String type, long date, String temp) throws SQLException {
		ds = DB.getDataSource();
		new JdbcSession(ds).clear().sql("insert into mesure (type, timestamp, value) values (?,?,?)").set(type).set(date).set(temp).insert(Outcome.VOID);

	}
}
