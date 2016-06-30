package com.cooksys.ftd.assessment.filesharing;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assesment.dao.AbstractDao;
import com.cooksys.ftd.assesment.dao.FilesDao;
import com.cooksys.ftd.assesment.dao.UserDao;
import com.cooksys.ftd.assessment.server.Server;

public class Main {
	private static Logger log = LoggerFactory.getLogger(Main.class);

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/assessment";
	private static String username = "root";
	private static String password = "bondstone";

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Class.forName(driver); // register jdbc driver class
		ExecutorService executor = Executors.newCachedThreadPool(); // initialize
																	// thread
																	// pool

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			Server server = new Server(); // init server

			server.setExecutor(executor);

			UserDao userDao = new UserDao();
			server.setUserDao(userDao);

			FilesDao filesDao = new FilesDao();
			server.setFilesDao(filesDao);
			
			AbstractDao abstractDao = new AbstractDao();
			abstractDao.setConn(conn);
			
			ServerSocket serversocket = new ServerSocket(667);
			server.setServerSocket(serversocket);

			Future<?> serverFuture = executor.submit(server); // start server
																// (asynchronously)
			log.info("Started up the server!");
			serverFuture.get(); // blocks until server's #run() method is done
								// (aka the server shuts down)
			log.info("Shut down server?!?");

		} catch (SQLException | InterruptedException | ExecutionException e) {
			log.error("An error occurred during server startup. Shutting down after error log.", e);
		} finally {
			executor.shutdown(); // shutdown thread pool (see inside of try
									// block for blocking call)
		}
	}
}
