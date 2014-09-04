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
import messenger.server.Client;
import messenger.server.ServerContext;

public class RequestController implements Runnable {

	private static Logger log = Logger.getLogger(RequestController.class);
	
	private Socket clientSocket;
	private static Map<Type, RequestHandler> requestHandlers;
	static {
		requestHandlers = new HashMap<Type, RequestHandler>(2);
		requestHandlers.put(Type.LOGIN, ApplicationContext.loginRequestHandler);
		requestHandlers.put(Type.REGISTER, ApplicationContext.registerRequestHandler);
		requestHandlers.put(Type.LOGOUT, ApplicationContext.logoutRequestHandler);
		requestHandlers.put(Type.FIND_CONTACT, ApplicationContext.findContactRequestHandler);
		requestHandlers.put(Type.MESSAGE,
				ApplicationContext.messageRequestHandler);
	}

	public RequestController(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void closeSocket() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			RequestHandler requestHandler = requestHandlers.get(request
					.getType());
			if(requestHandler != null){
				String[] strings = clientSocket.getRemoteSocketAddress().toString().split(":");
				String remoteAddress = strings[0].substring(1);
				Client sender = ServerContext.getClient(request.getSender(), remoteAddress);
				if(sender == null){
					sender = new Client(new Long(0),remoteAddress);
				}
				requestHandler.handleRequest(request, sender);
			}
			else
				log.debug("There is no request handler for " + request.getType().toString());
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
