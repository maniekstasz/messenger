package messenger.request;

import java.util.Date;

public class MessageRequest extends Request {

	private static final long serialVersionUID = -2833617758199412704L;
	
	private Long receiver;
	private String message;
	private Date date;

	public MessageRequest() {
		super(Type.MESSAGE);
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
