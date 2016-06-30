package com.cooksys.ftd.assessment.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assesment.dao.FilesDao;
import com.cooksys.ftd.assesment.dao.UserDao;


public class Server implements Runnable {
	
	private Logger log = LoggerFactory.getLogger(Server.class);
	
	ExecutorService executor = Executors.newCachedThreadPool();
	private ServerSocket serverSocket;
	
	private UserDao userDao;
	private FilesDao filesDao;
	
	@Override
	public void run() {
		try {
			while (true) {
				Socket socket = this.serverSocket.accept();
				ClientHandler handler = this.createClientHandler(socket);
				this.executor.execute(handler);
			}
		} catch (IOException e) {
			log.error("The server encountered a fatal error while listening for more connections. Shutting down after error log.", e);
		}
	}
	
	public ClientHandler createClientHandler(Socket socket) throws IOException {
		ClientHandler handler = new ClientHandler();

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		handler.setReader(reader);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		handler.setWriter(writer);

		return handler;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public FilesDao getFilesDao() {
		return filesDao;
	}

	public void setFilesDao(FilesDao filesDao) {
		this.filesDao = filesDao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((executor == null) ? 0 : executor.hashCode());
		result = prime * result + ((serverSocket == null) ? 0 : serverSocket.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		if (executor == null) {
			if (other.executor != null)
				return false;
		} else if (!executor.equals(other.executor))
			return false;
		if (serverSocket == null) {
			if (other.serverSocket != null)
				return false;
		} else if (!serverSocket.equals(other.serverSocket))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Server [executor=" + executor + ", serverSocket=" + serverSocket + "]";
	}

}
