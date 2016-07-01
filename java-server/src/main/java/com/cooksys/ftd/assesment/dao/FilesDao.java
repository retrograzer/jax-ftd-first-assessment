package com.cooksys.ftd.assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooksys.ftd.assessment.model.db.Files;

public class FilesDao extends AbstractDao {
	
	AbstractDao abs = new AbstractDao();
	Connection conn = abs.getConn();
	
	public Files createFiles () {
		
		try {
			String sql = "select * "
					+ "from files";
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
		return null;
	}
	
	public String FileList () {
		log.info("In FileList");
		try {
			log.info("In the FileDao");
			String sql = "select * from files";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			String complPath = "";
			String complObj = "";
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String filePath = rs.getString("file_path");
				String fileObj = rs.getString("file_data");
				log.info("{} {}", filePath, fileObj);
				complPath = filePath;
				complObj = fileObj;
			}

			return complPath + " " + complObj;
		} catch (SQLException e) {
			log.error("Something went wrong retrieving file list");
		}
		return "Nothingness";
	}

}
