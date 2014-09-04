package messenger.request;

public class FindContactStatusRequest extends Request {

	private static final long serialVersionUID = -3830293485596368722L;

	private boolean status;
	private Long contactId;
	private String username;
	
	public FindContactStatusRequest(boolean status, Long sender, Long contactId, String username) {
		super(Type.FIND_CONTACT_STATUS);
		this.status = status;
		this.contactId = contactId;
		this.username = username;
		setSender(sender);
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

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
}
