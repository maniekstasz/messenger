package messanger.main;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import messenger.handler.ApplicationContext;
import messenger.handler.HibernateUtil;
import messenger.server.Server;

public class Main {

	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
//		BasicConfigurator.configure();
		log.info("Start Application");
		ApplicationContext appContext = new ApplicationContext();
		HibernateUtil.initialize();
		Server server = new Server(15);
		server.initServer();
		server.startListening();
	}

}
