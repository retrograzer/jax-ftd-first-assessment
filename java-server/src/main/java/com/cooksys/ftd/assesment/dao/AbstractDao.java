package com.cooksys.ftd.assesment.dao;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractDao {

	Logger log = LoggerFactory.getLogger(AbstractDao.class);
	
	private Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
		log.info("Set a connection! {}", this.conn);
	}

}
