package com.cooksys.ftd.assessment.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assesment.dao.FilesDao;
import com.cooksys.ftd.assesment.dao.UserDao;
import com.cooksys.ftd.assessment.model.api.CreateUser;
import com.cooksys.ftd.assessment.model.api.DownloadFile;
import com.cooksys.ftd.assessment.model.api.UploadFile;
import com.cooksys.ftd.assessment.model.db.Files;

public class ClientHandler implements Runnable {
	
	private Logger log = LoggerFactory.getLogger(ClientHandler.class);
	BufferedReader reader;
	PrintWriter writer;
	
	FilesDao filesDao;
	UserDao userDao;
	UploadFile upload;
	DownloadFile download;

	@Override
	public void run() {
		try {
			while (true) {
				String message = reader.readLine();
				if (message.equals("files")) {
					log.info("Wrote Files");
					writer.print(filesDao.FileList());
					writer.flush();
				} else if (message.equals("upload")) {
					writer.print("Please specify a file path: ");
					writer.flush();
					message = reader.readLine();
					writer.print("Uploading file...");
					Files fly = new Files();
					fly.setFilePath(message);
					fly.setObjs();
					//jaxB sets the file to the object!
					//I should probably do that in a different class...Maybe in the Files Class
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
				} else if (message.equals("download")) {
					writer.print("Please specify a file path to download: ");
					writer.flush();
					message = reader.readLine();
					writer.print("Downloading file...");
					writer.flush();
					PrintWriter fileWriter = new PrintWriter(message, "UTF-8");
					download = new DownloadFile();
					String hallo = download.downloadFiles(message);
					fileWriter.print(hallo);
					log.info("Hallo put in a file");
					writer.print(hallo);
					writer.flush();
				}
				writer.flush();
				log.info("Wrote message ({}) in ClientHandler", message);
			}
		} catch (IOException | ClassNotFoundException f) {
			log.error("ERROR! Something went wrong trying to run the ClientHandler. FIX ME! Stack trace below. " + f);
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
