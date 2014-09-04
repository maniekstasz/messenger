package messenger.request;


import messenger.handler.ApplicationContext;

public class MessageRequestHandler implements InRequestHandler<MessageRequest>, OutRequestHandler<MessageRequest> {
	
	@Override
	public void handleOutRequest(MessageRequest request) {
		ApplicationContext.clientSocketHandler.sendRequest(request);
	}

	@Override
	public void handleInRequest(MessageRequest request) {
		ApplicationContext.controller.onMessageIn(request);
	}

	
}
