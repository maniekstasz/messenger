package messenger.request;


import messenger.handler.ApplicationContext;
import messenger.model.User;
import messenger.model.service.UserService;
import messenger.server.Client;

public class FindContactRequestHandler implements RequestHandler<FindContactRequest> {

	private UserService userService = ApplicationContext.userService;
	
	@Override
	public void handleRequest(FindContactRequest request, Client sender) {
		User user = userService.get(request.getUsername());
		FindContactStatusRequest status = null;
		if (user != null) {
			 status = new FindContactStatusRequest(true, request.getSender(), user.getId(),user.getUsername());
		}else{
			status = new FindContactStatusRequest(false, request.getSender(),null, null);
		}
		ApplicationContext.clientSocketHandler.sendRequest(status, sender);
	}


}
