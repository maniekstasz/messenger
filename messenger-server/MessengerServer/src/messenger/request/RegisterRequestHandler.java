package messenger.request;

import messenger.handler.ApplicationContext;
import messenger.model.User;
import messenger.model.service.UserService;
import messenger.server.Client;

public class RegisterRequestHandler implements RequestHandler<RegisterRequest> {

	private UserService userService = ApplicationContext.userService;

	@Override
	public void handleRequest(RegisterRequest request, Client sender) {
		User user = new User();
		user.setPassword(request.getPassword());
		user.setUsername(request.getUsername());
		userService.add(user);
		request.setSender(user.getId());
		onSuccess(request, sender);
	}

	private void onSuccess(RegisterRequest request, Client sender){
		RegisterStatusRequest statusRequest = new RegisterStatusRequest(true, request.getUsername());
		statusRequest.setSender(request.getSender());
		ApplicationContext.clientSocketHandler.sendRequest(statusRequest, sender);
	}
}
