package com.cooksys.ftd.assessment.model.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assesment.dao.AbstractDao;
import com.cooksys.ftd.assessment.model.db.Files;

public class UploadFile {
	
	Logger log = LoggerFactory.getLogger(UploadFile.class);

	AbstractDao abs = new AbstractDao();
	Connection conn = abs.getConn();
	
	public Files createFiles (String filePath, Object fileobjs) {
		
		try {
			
			ResultSet rs = null;
			
			java.sql.Statement stmt = conn.createStatement();
			
			log.info("got into the fileDAo..." + filePath);
			
			stmt.executeUpdate("insert into user "
					+ "values (" + filePath + ", " + fileobjs +")");


			rs = stmt.getGeneratedKeys();
			log.info("got into the userdao..." + filePath);
			
			log.info("Retrieving actors...");
			while (rs.next()) {
				String firstName = rs.getString("username");
				String filmTitle = rs.getString("password");
				log.info("{} {}", filmTitle, firstName);
			}
			
		} catch (SQLException e) {
			log.error("Something went wrong :/", e);
		}
		return null;
	}
	
}
