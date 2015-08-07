package net.leludo.domobypi.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;

import play.Logger;
import play.db.DB;

public class Dao {
	DataSource ds;

	public void create(String type, long date, String temp) throws SQLException {
		ds = DB.getDataSource();
		new JdbcSession(ds).clear().sql("insert into mesure (type, timestamp, value) values (?,?,?)").set(type)
				.set(date).set(temp).insert(Outcome.VOID);

	}

	public void initDatabase() throws SQLException {
		ds = DB.getDataSource();
		JdbcSession session = new JdbcSession(ds).clear();

		Logger.warn("Initializing database... All data will be lost !") ;
		session.sql("drop table if exists mesure;").execute();
		session.sql("create table mesure (timestamp bigint,value varchar(10),type varchar(20));").execute();
		Logger.warn("Database initialized !") ;
	}
}
