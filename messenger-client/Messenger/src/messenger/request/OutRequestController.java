package messenger.request;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import messenger.handler.ApplicationContext;
import messenger.request.Request.Type;

public class OutRequestController implements Runnable {

	private static Logger log = Logger.getLogger(OutRequestController.class);

	private Request request;

	private static Map<Type, OutRequestHandler> outRequestHandlers;
	static {
		outRequestHandlers = new HashMap<Type, OutRequestHandler>(2);
		outRequestHandlers.put(Type.LOGIN,
				ApplicationContext.loginRequestHandler);
		outRequestHandlers.put(Type.MESSAGE,
				ApplicationContext.messageRequestHandler);
		outRequestHandlers.put(Type.REGISTER,
				ApplicationContext.registerRequestHandler);
		outRequestHandlers.put(Type.LOGOUT, ApplicationContext.logoutRequestHandler);
		outRequestHandlers.put(Type.FIND_CONTACT, ApplicationContext.findContactRequestHandler);
	}

	public OutRequestController(Request request) {
		this.request = request;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void run() {
		OutRequestHandler requestHandler = outRequestHandlers.get(request
				.getType());
		requestHandler.handleOutRequest(request);
	}
}
