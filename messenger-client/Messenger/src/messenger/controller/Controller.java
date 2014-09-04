package messenger.controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import messenger.handler.ApplicationContext;
import messenger.model.Message;
import messenger.model.User;
import messenger.model.service.MessageService;
import messenger.model.service.UserService;
import messenger.request.FindContactRequest;
import messenger.request.FindContactStatusRequest;
import messenger.request.LoginRequest;
import messenger.request.LoginStatusRequest;
import messenger.request.LogoutRequest;
import messenger.request.LogoutStatusRequest;
import messenger.request.MessageRequest;
import messenger.request.OutRequestController;
import messenger.request.RegisterRequest;
import messenger.request.RegisterStatusRequest;
import messenger.views.RegisterView;
import messenger.views.StartView;

public class Controller {

	private Map<Long, UserController> controllers = new ConcurrentHashMap<Long, UserController>();

	private UserService userService = ApplicationContext.userService;
	private MessageService messageService = ApplicationContext.messageService;

	private ExecutorService threadPool = Executors.newFixedThreadPool(5);
	private MainAppController mainAppController = new MainAppController(this);

	public void onLoggedIn(LoginStatusRequest request) {
		if (request.isStatus()) {
			if (!controllers.containsKey(request.getSender())) {
				User user = userService.getWithContacts(request.getSender());
				if (user == null) {
					user = new User();
					user.setId(request.getSender());
					user.setUsername(request.getUsername());
					userService.add(user);
				}
				UserController subController = new UserController(user,
						this);
				controllers.put(request.getSender(), subController);
				subController.onLoggedIn();
			}else{
			}
		} else {
			JOptionPane.showMessageDialog(null, "Błąd przy logowaniu");
		}
	}

	public void onMessageIn(MessageRequest request) {
		User user = userService.get(request.getReceiver());
		if (user != null) {
			Message msg = new Message(user, request.getSender(),
					request.getMessage(), request.getDate(),
					Message.Type.RECEIVED);
			messageService.add(msg);
			controllers.get(user.getId()).onMessageIn(msg);
		}

	}

	public void onRegisterStatus(RegisterStatusRequest request) {
		if (request.isStatus()) {
			User user = new User();
			user.setId(request.getSender());
			user.setUsername(request.getUsername());
			userService.add(user);
			mainAppController.showStartView();
		} else {
			JOptionPane.showMessageDialog(null, "Błąd przy rejestracji");
		}

	}

	public void onFoundContact(FindContactStatusRequest request) {
		UserController userController = controllers.get(request.getSender());
		if (request.isStatus()) {
			User user = new User();
			user.setId(request.getContactId());
			user.setUsername(request.getUsername());
			userService.add(user);
			userController.queryFound(user);
		} else {
			userController.queryNotFound();
		}
	}

	public void logIn(String username, String password) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(username);
		loginRequest.setPassword(password);
		threadPool.execute(new OutRequestController(loginRequest));
	}

	public void onLogoutStatus(LogoutStatusRequest request) {
		UserController userController = controllers.get(request.getSender());
		if (request.isStatus()) {
			if (userController != null) {
				userController.close();
				controllers.remove(request.getSender());
				if (controllers.size() == 0) {
					closeApp();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Błąd przy wylogowaniu");
		}
	}

	public void logout(User user) {
		LogoutRequest request = new LogoutRequest();
		request.setSender(user.getId());
		threadPool.execute(new OutRequestController(request));
	}

	public void register(String username, String password) {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setPassword(password);
		registerRequest.setUsername(username);
		threadPool.execute(new OutRequestController(registerRequest));
	}

	public void find(String username, User user) {
		FindContactRequest request = new FindContactRequest(username);
		request.setSender(user.getId());
		threadPool.execute(new OutRequestController(request));
	}

	public void onMessageOut(Message msg) {

		MessageRequest msgRequest = new MessageRequest();
		msgRequest.setMessage(msg.getMessage());
		msgRequest.setReceiver(msg.getInterlocutor());
		msgRequest.setSender(msg.getOwner().getId());
		messageService.add(msg);
		threadPool.execute(new OutRequestController(msgRequest));
	}

	public void updateUser(User user) {
		userService.add(user);
	}

	private void closeApp() {
		mainAppController.close();
		ApplicationContext.server.stopListening();
		System.exit(-1);
	}

	public void startApp() {
		ApplicationContext.server.initServer();
		ApplicationContext.server.startListening();
		mainAppController.showStartView();
	}

}
