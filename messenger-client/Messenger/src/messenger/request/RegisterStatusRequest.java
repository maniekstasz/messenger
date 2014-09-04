package messenger.request;

public class RegisterStatusRequest extends Request {

	private static final long serialVersionUID = -3830293485596368722L;

	private boolean status;
	private String username;
	public RegisterStatusRequest(boolean status, String username) {
		super(Type.REGISTER_STATUS);
		this.status = status;
		this.username = username;
		// TODO Auto-generated constructor stub
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
