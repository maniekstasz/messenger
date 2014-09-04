package messenger.request;

import messenger.handler.ApplicationContext;

public class FindContactRequestHandler implements InRequestHandler<FindContactStatusRequest>, OutRequestHandler<FindContactRequest> {

	@Override
	public void handleOutRequest(FindContactRequest request) {
		ApplicationContext.clientSocketHandler.sendRequest(request);
		
	}

	@Override
	public void handleInRequest(FindContactStatusRequest request) {
		ApplicationContext.controller.onFoundContact(request);
	}


}
