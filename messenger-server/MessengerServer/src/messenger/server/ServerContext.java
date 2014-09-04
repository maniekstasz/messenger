package messenger.server;

import java.util.Vector;

public class ServerContext {
	
	public static Vector<Client> connectedClients = new Vector<Client>();

	public static Client getClient(Long id){
		for(Client client: connectedClients){
			if(client.getId().equals(id)){
				return client;
			}
		}
		return null;
	}
	public static Client getClient(Long id, String ip){
		for(Client client: connectedClients){
			if(client.getIp().equals(ip) && client.getId().equals(id)){
				return client;
			}
		}
		return null;
	}
	
}
