package messenger.request;

import messenger.handler.ApplicationContext;

public class RegisterRequestHandler implements InRequestHandler<RegisterStatusRequest>, OutRequestHandler<RegisterRequest> {

	@Override
	public void handleOutRequest(RegisterRequest request) {
		ApplicationContext.clientSocketHandler.sendRequest(request);
	}

	@Override
	public void handleInRequest(RegisterStatusRequest request) {
		ApplicationContext.controller.onRegisterStatus(request);
	}

}
