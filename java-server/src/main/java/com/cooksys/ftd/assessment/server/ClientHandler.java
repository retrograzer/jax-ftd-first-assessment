package com.cooksys.ftd.assessment.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assesment.dao.FilesDao;
import com.cooksys.ftd.assesment.dao.UserDao;
import com.cooksys.ftd.assessment.model.api.CreateUser;

public class ClientHandler implements Runnable {
	
	private Logger log = LoggerFactory.getLogger(ClientHandler.class);
	BufferedReader reader;
	PrintWriter writer;
	
	FilesDao filesDao;
	UserDao userDao;

	@Override
	public void run() {
		try {
			while (true) {
				String message = reader.readLine();
				if (message.equals("files")) {
					writer.print(message);
					
				} else if (message.equals("upload")) {
//					writer.print("Please specify a file path: ");
//					writer.flush();
//					message = reader.readLine();
//					writer.print("Uploading file...");
				} else if (message.equals("update")) {
					writer.print("Please specify your username.");
					writer.flush();
					message = reader.readLine();
					writer.print("Now please press Enter to continue...");
					writer.flush();
					writer.print("password");
					writer.flush();
					String message2 = reader.readLine();
					CreateUser usa = new CreateUser(message, message2);
					log.info("Did a thing in Client {} ", usa);
				}
				writer.flush();
				log.info("Wrote message ({}) in ClientHandler", message);
			}
		} catch (IOException | ClassNotFoundException e) {
			log.error("ERROR! Something went wrong trying to run the ClientHandler. FIX ME! Stack trace below. " + e);
		}
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public FilesDao getFilesDao() {
		return filesDao;
	}

	public void setFilesDao(FilesDao filesDao) {
		this.filesDao = filesDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
