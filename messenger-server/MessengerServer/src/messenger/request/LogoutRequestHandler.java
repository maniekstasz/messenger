package messenger.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import messenger.handler.ApplicationContext;
import messenger.model.Message;
import messenger.model.User;
import messenger.model.service.MessageService;
import messenger.model.service.UserService;
import messenger.server.Client;
import messenger.server.ClientSocketHandler;
import messenger.server.ServerContext;

public class LogoutRequestHandler implements RequestHandler<LogoutRequest> {

	@Override
	public void handleRequest(LogoutRequest request, Client sender) {
		boolean removed = ServerContext.connectedClients.remove(sender);
		LogoutStatusRequest status = new LogoutStatusRequest(removed);
		status.setSender(request.getSender());
		ApplicationContext.clientSocketHandler.sendRequest(status, sender);
	}



}
