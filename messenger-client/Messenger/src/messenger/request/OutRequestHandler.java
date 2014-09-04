package messenger.request;

public interface OutRequestHandler<T> {
	public void handleOutRequest(T request);
}
