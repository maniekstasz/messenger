package messenger.request;

public class LoginStatusRequest extends Request {

	private static final long serialVersionUID = -3830293485596368722L;

	private boolean status;
	private String Username;
	public LoginStatusRequest(boolean status) {
		super(Type.LOGIN_STATUS);
		this.status = status;
		// TODO Auto-generated constructor stub
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

}
