package messanger.main;

import java.awt.EventQueue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import messenger.handler.ApplicationContext;
import messenger.request.LoginRequest;
import messenger.request.MessageRequest;
import messenger.request.OutRequestController;
import messenger.server.Server;

public class Main {

	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					BasicConfigurator.configure();
//					log.info("Start Application");
					ApplicationContext appContext = new ApplicationContext();
					appContext.controller.startApp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
