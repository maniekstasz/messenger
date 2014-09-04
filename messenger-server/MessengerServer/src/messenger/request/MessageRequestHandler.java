package messenger.request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import messenger.handler.ApplicationContext;
import messenger.model.Message;
import messenger.model.service.MessageService;
import messenger.server.Client;
import messenger.server.ClientSocketHandler;
import messenger.server.ServerContext;

public class MessageRequestHandler implements RequestHandler<MessageRequest> {
	
	private MessageService messageService = ApplicationContext.messageService;
	
	@Override
	public void handleRequest(MessageRequest request, Client sender) {
		Client client = ServerContext.getClient(request.getReceiver());
		request.setDate(new Date());
		if (client != null) {
			if(!ApplicationContext.clientSocketHandler.sendRequest(request, client)){
				savePendingMessage(request);
			}
		}else{
			savePendingMessage(request);
		}
	}

	public void savePendingMessage(MessageRequest request) {
		Message message = new Message();
		message.setDate(request.getDate());
		message.setMessage(request.getMessage());
		message.setSender(request.getSender());
		messageService.add(message, request.getReceiver());
	}

	public boolean sendMessage(MessageRequest request, Client client) {
//		Socket socket = null;
//		OutputStream os = null;
//		ObjectOutputStream oos = null;
//		try {
//			socket = new Socket(client.getIp(), 6666);
//			os = socket.getOutputStream();
//			oos = new ObjectOutputStream(os);
//			oos.writeObject(request);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			try {
//				if (oos != null) {
//					oos.close();
//				}
//				if (os != null) {
//					os.close();
//				}
//				if (socket != null) {
//					socket.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}
	
}
