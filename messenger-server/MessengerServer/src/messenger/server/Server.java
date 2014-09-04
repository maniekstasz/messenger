package messenger.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import messenger.request.RequestController;

import org.apache.log4j.Logger;

public class Server implements Runnable {

	private static Logger log = Logger.getLogger(Server.class);
	
	private ServerSocket serverSocket = null;
	private Thread listeningThread;
	private boolean listening = false;
	private ExecutorService threadPool;
	static int a = 0;
	private final int port = 8679;
	public Server(int threadCount){
		listeningThread = new Thread(this);
		threadPool = Executors.newFixedThreadPool(threadCount);
	}
	
	public void initServer() {
		try {
			serverSocket = new ServerSocket(port);
			log.info("Server initialized on port " + port);
		} catch (IOException e) {
			log.fatal("Could not listen on port: " + port);
			log.error(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void startListening(){
		listening = true;
		listeningThread.start();
	}
	
	public void stopListening(){
		listening = false;
	}


	@Override
	public void run() {
		Socket clientSocket = null;
		while(listening){
			try {
				clientSocket = serverSocket.accept();
				RequestController requestController = new RequestController(clientSocket);
				threadPool.execute(requestController);
				
			} catch (IOException e) {
				log.error("Accept failed: " + port);
				e.printStackTrace();
			}
		}
	}
}
