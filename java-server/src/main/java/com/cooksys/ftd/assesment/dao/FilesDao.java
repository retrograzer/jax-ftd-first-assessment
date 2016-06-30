package com.cooksys.ftd.assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FilesDao extends AbstractDao {
	
	AbstractDao abs = new AbstractDao();
	Connection conn = abs.getConn();
	
	try {
		
		String sql = "select * "
				+ "from user";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();

		log.info("Retrieving actors...");
		while (rs.next()) {
			String firstName = rs.getString("username");
			String lastName = rs.getString("password");
			log.info("{} {}", firstName, lastName);
		}
		
	} catch (SQLException e) {
		log.error("Something went wrong :/", e);
	}
		return user;
	}
	
	public <E> List<E> FileList () {
		return null; //TODO
	}

}
