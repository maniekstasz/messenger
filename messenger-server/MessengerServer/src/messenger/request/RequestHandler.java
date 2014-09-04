package messenger.request;


import messenger.server.Client;

public interface RequestHandler<T> {
	
	public void handleRequest(T request, Client sender);
}
