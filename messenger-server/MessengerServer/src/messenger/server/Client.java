package messenger.server;

public class Client {
	private Long id;
	private String ip;

	public Client(Long id, String ip) {
		super();
		this.id = id;
		this.ip = ip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
