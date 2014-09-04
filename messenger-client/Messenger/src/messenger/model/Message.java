package messenger.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

	public enum Type {
		SENDED, RECEIVED
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
//	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
//	@JoinColumn(name = "interlocutor")
//	private User interlocutor;
//
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "owner")
	private User owner;
	private Long interlocutor;
	private String message;
	private Date date;
	private Type type;

	public Message(User owner, Long interlocutor, String message,
			Date date, Type type) {
		this.owner = owner;
		this.interlocutor = interlocutor;
		this.message = message;
		this.date = date;
		this.type = type;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInterlocutor() {
		return interlocutor;
	}

	public void setInterlocutor(Long interlocutor) {
		this.interlocutor = interlocutor;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
