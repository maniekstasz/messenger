package messenger.handler;

import messenger.client.ClientSocketHandler;
import messenger.controller.Controller;
import messenger.model.dao.MessageDao;
import messenger.model.dao.UserDao;
import messenger.model.service.MessageService;
import messenger.model.service.UserService;
import messenger.request.FindContactRequestHandler;
import messenger.request.LoginRequestHandler;
import messenger.request.LogoutRequestHandler;
import messenger.request.MessageRequestHandler;
import messenger.request.RegisterRequestHandler;
import messenger.server.Server;

public class ApplicationContext {
	public final static UserDao userDao = new UserDao();
	public final static MessageDao messageDao = new MessageDao();
	public final static UserService userService = new UserService();
	public final static MessageService messageService = new MessageService();
	public final static MessageRequestHandler messageRequestHandler = new MessageRequestHandler();
	public final static LoginRequestHandler loginRequestHandler = new LoginRequestHandler();
	public final static ClientSocketHandler clientSocketHandler = new ClientSocketHandler();
	public final static RegisterRequestHandler registerRequestHandler = new RegisterRequestHandler();
	public final static LogoutRequestHandler logoutRequestHandler = new LogoutRequestHandler();
	public final static Controller controller = new Controller();
	public final static Server server =  new Server(15);
	public final static FindContactRequestHandler findContactRequestHandler = new FindContactRequestHandler();
	public static final String serverIp = "localhost";
	public static final int appPort = 8679;
}
