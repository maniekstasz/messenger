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

public class LoginRequestHandler implements RequestHandler<LoginRequest> {

	private UserService userService = ApplicationContext.userService;
	private MessageService messageService = ApplicationContext.messageService;
	
	@Override
	public void handleRequest(LoginRequest request, Client sender) {
		User user = userService.getWithMessages(request.getUsername());
		if (user == null || !user.getPassword().equals(request.getPassword())) {
			onInvalidCredentials(sender, request);
			return;
		}
		onValidCredentials(user, sender);
	}

	public void onInvalidCredentials(Client sender, LoginRequest request) {
		LoginStatusRequest statusRequest = new LoginStatusRequest(false);
		statusRequest.setUsername(request.getUsername());
		ApplicationContext.clientSocketHandler.sendRequest(statusRequest, sender);
	}

	public void onValidCredentials(User user, Client sender) {
		sender.setId(user.getId());
		ServerContext.connectedClients.add(sender);
		LoginStatusRequest statusRequest = new LoginStatusRequest(true);
		statusRequest.setSender(user.getId());
		if (!ApplicationContext.clientSocketHandler.sendRequest(statusRequest, sender)) {
			ServerContext.connectedClients.remove(sender);
		}else{
			sendPendingMessages(user);
		}
	}
	
	private void sendPendingMessages(User user){
		List<Message> messages = user.getPendingMessages();
		Client client = ServerContext.getClient(user.getId());
		if(messages != null){
			List<Long> ids = new ArrayList<Long>();
			for(Message message: messages){
				MessageRequest request = new MessageRequest();
				request.setSender(message.getSender());
				request.setReceiver(user.getId());
				request.setMessage(message.getMessage());
				request.setDate(message.getDate());
				if(ApplicationContext.clientSocketHandler.sendRequest(request, client)){
					ids.add(message.getId());
				}
			}
			if(ids.size()>0){
				messageService.deleteAll(ids);
			}
				
		}
	}

}
