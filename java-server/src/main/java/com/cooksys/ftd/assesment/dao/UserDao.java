package com.cooksys.ftd.assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.cooksys.ftd.assessment.model.db.User;

public class UserDao extends AbstractDao {
	
	AbstractDao abs = new AbstractDao();
	Connection conn = abs.getConn();
	
	public User createUser(User user) throws ClassNotFoundException {

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
	
	public Optional<User> getUserByUsername(String username) {
		return null; // TODO
	}
	
}
