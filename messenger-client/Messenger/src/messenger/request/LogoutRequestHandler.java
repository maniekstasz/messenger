package messenger.request;

import messenger.handler.ApplicationContext;

public class LogoutRequestHandler implements InRequestHandler<LogoutStatusRequest>, OutRequestHandler<LogoutRequest> {

	@Override
	public void handleOutRequest(LogoutRequest request) {
		ApplicationContext.clientSocketHandler.sendRequest(request);		
	}

	@Override
	public void handleInRequest(LogoutStatusRequest request) {
		ApplicationContext.controller.onLogoutStatus(request);
		
	}

}
