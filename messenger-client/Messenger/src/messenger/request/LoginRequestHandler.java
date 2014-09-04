package messenger.request;

import messenger.client.ClientSocketHandler;
import messenger.handler.ApplicationContext;

public class LoginRequestHandler implements OutRequestHandler<LoginRequest>, InRequestHandler<LoginStatusRequest> {
	 

	@Override
	public void handleOutRequest(LoginRequest request) {
		ApplicationContext.clientSocketHandler.sendRequest(request);
	}

	@Override
	public void handleInRequest(LoginStatusRequest request) {
		ApplicationContext.controller.onLoggedIn(request);
		
	}
	
}
