package com.cooksys.ftd.assessment.model.api;

import com.cooksys.ftd.assesment.dao.UserDao;
import com.cooksys.ftd.assessment.model.db.User;

public class CreateUser {

	private String username;
	private String password;

	public CreateUser(String username, String password) throws ClassNotFoundException {
		super();
		this.username = username;
		this.password = password;
		User ppl = new User();
		ppl.setPassword(password);
		ppl.setUsername(username);
		UserDao dao = new UserDao();
		dao.createUser(ppl);
	}

}
