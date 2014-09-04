package messenger.request;

public class LogoutStatusRequest extends Request {

	private static final long serialVersionUID = -3830293485596368722L;

	private boolean status;

	public LogoutStatusRequest(boolean status) {
		super(Type.LOGOUT_STATUS);
		this.status = status;
		// TODO Auto-generated constructor stub
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
