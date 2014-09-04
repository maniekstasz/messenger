package messenger.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import messenger.request.Request;

public class ClientSocketHandler {
	
	private static final int port = 8678;
	static int a = 0, b=0;
	public boolean sendRequest(Request request, Client client){
		Socket socket = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			socket = new Socket(client.getIp(), port);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			oos.writeObject(request);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (os != null) {
					os.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
