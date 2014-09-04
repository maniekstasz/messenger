package messenger.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


import messenger.handler.ApplicationContext;
import messenger.request.Request;

public class ClientSocketHandler {
	
	private final String serverIp;
	private static final int port = ApplicationContext.appPort;
	
	public ClientSocketHandler(){
		serverIp = getServerIp();
	}
	static int a;
	public boolean sendRequest(Request request){
		Socket socket = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			socket = new Socket(serverIp, port);
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
	public String getServerIp(){
		BufferedReader bf = null;
		try {
			FileInputStream in = new FileInputStream("server_conf.txt");
			DataInputStream input = new DataInputStream(in);
			bf = new BufferedReader(new InputStreamReader(input));
			String serverIp = null;
			while ((serverIp = bf.readLine()) != null) {
				return serverIp;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		} finally {
			try {
				if (bf != null) {
					bf.close();
				}
				bf = null;
			} catch (IOException e) {
				// ignore
			}
		}
	}
}
