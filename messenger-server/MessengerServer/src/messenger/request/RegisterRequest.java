package messenger.request;

import messenger.request.Request.Type;

public class RegisterRequest extends Request {

	private static final long serialVersionUID = 6156985617928844410L;


	private String Username;
	private String Password;
	
	public RegisterRequest() {
		super(Type.REGISTER);
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

}
