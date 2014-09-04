package messenger.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	private Long id;
	private String username;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
	private List<Message> messages;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "contact1_ID") }, inverseJoinColumns = { @JoinColumn(name = "contact2_id") })
	private List<User> contacts = Collections
			.synchronizedList(new ArrayList<User>());

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<User> getContacts() {
		return contacts;
	}

	public void setContacts(List<User> contacts) {
		this.contacts = Collections.synchronizedList(contacts);
	}

	public void addContact(User contact) {
		if (!contacts.contains(contact))
			this.contacts.add(contact);
	}

	@Override
	public String toString() {
		return username;
	}

	@Override
	public boolean equals(Object user) {
		return id.equals(((User) user).getId());
	}
}
