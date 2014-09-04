package messenger.request;

public class LoginRequest extends Request{

	private static final long serialVersionUID = 1848180767803598351L;
	
	private String Username;
	private String Password;
	
	public LoginRequest() {
		super(Type.LOGIN);
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
