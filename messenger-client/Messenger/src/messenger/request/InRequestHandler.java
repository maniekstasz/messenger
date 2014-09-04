package messenger.request;


public interface InRequestHandler<T> {
	
	public void handleInRequest(T request);
}
