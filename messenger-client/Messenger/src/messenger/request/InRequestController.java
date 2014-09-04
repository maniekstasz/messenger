package messenger.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import messenger.handler.ApplicationContext;
import messenger.request.Request.Type;

public class InRequestController implements Runnable {

	private static Logger log = Logger.getLogger(InRequestController.class);
	
	private Socket clientSocket;

	private static Map<Type, InRequestHandler> inRequestHandlers;
	static {
		inRequestHandlers = new HashMap<Type, InRequestHandler>(2);
		inRequestHandlers.put(Type.LOGIN_STATUS, ApplicationContext.loginRequestHandler);
		inRequestHandlers.put(Type.LOGOUT_STATUS, ApplicationContext.logoutRequestHandler);
		inRequestHandlers.put(Type.REGISTER_STATUS, ApplicationContext.registerRequestHandler);
		inRequestHandlers.put(Type.MESSAGE,
				ApplicationContext.messageRequestHandler);
		inRequestHandlers.put(Type.REGISTER_STATUS,
				ApplicationContext.registerRequestHandler);
		inRequestHandlers.put(Type.FIND_CONTACT_STATUS, ApplicationContext.findContactRequestHandler);
	}

	public InRequestController(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void run() {
		if(clientSocket == null){
			return;
		}
		InputStream is = null;
		ObjectInputStream ois = null;
		try {

			is = clientSocket.getInputStream();
			ois = new ObjectInputStream(is);
			Request request = (Request) ois.readObject();
			log.debug("Received request of type " + request.getType().toString());
			InRequestHandler requestHandler = inRequestHandlers.get(request
					.getType());
			requestHandler.handleInRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (is != null) {
					is.close();
				}
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
