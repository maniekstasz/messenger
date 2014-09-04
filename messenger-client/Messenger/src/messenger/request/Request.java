package messenger.request;

import java.io.DataInputStream;
import java.io.Serializable;

public abstract class Request implements Serializable {
	
	private static final long serialVersionUID = -2869929561134748182L;

	public enum Type {
		LOGIN,
		MESSAGE,
		LOGIN_STATUS,
		REGISTER,
		REGISTER_STATUS, LOGOUT, LOGOUT_STATUS,
		FIND_CONTACT, FIND_CONTACT_STATUS;
	}
	
	private final Type type;
	private Long sender;
	public Request(Type type){
		this.type = type;
	}
	public Type getType() {
		return type;
	}
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}

	
}
