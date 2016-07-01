package com.cooksys.ftd.assessment.model.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assesment.dao.AbstractDao;

public class DownloadFile {
	
	Logger log = LoggerFactory.getLogger(UploadFile.class);

	AbstractDao abs = new AbstractDao();
	Connection conn = abs.getConn();
	
	public String downloadFiles (String filePath) {
			log.info("in the downloadFile thingy");
		try {
			PreparedStatement stmt;
			
			String sql = "select * "
					+ "from files ";
			
			//WHY THE FLIP WONT THIS DARN STATEMENT PREPARE ITSELF?!?!?!?!?!?!?!?!?!?! IS IT BECAUSE THERE'S NOTHING IN IT?
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String path = rs.getString("file_path");
				log.info("{} {}", path);
			}
			
		} catch (SQLException e) {
			log.error("Something went wrong :/", e);
		}
		return "Suck it Hollywood";
	}
}
