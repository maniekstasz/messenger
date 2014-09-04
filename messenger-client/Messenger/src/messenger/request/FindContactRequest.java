package messenger.request;

public class FindContactRequest extends Request {

	public FindContactRequest(String username) {
		super(Type.FIND_CONTACT);
		this.username = username;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4989437002603975423L;
	
	private String username;

	public String getUsername() {
		return username;
	}
	

}
