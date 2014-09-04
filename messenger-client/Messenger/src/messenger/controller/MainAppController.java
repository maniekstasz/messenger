package messenger.controller;

import javax.swing.JFrame;

import messenger.views.RegisterView;
import messenger.views.StartView;

public class MainAppController {
	private StartView startView;
	private RegisterView registerView;
	private Controller controller;

	public MainAppController(Controller controller) {
		this.controller = controller;
	}

	public void logIn(String username, String password) {
		controller.logIn(username, password);
		hideStartView();
	}

	private void createStartView() {
		if (startView == null) {
			startView = new StartView(this);
			startView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			startView.setVisible(false);
			startView.setResizable(false);
		}
	}

	private void createRegisterView() {
		if (registerView == null) {
			registerView = new RegisterView(this);
			registerView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			registerView.setVisible(false);
			registerView.setResizable(false);
		}
	}

	private void removeRegisterView() {
		if (registerView != null) {
			registerView.dispose();
			registerView = null;
		}
	}
	private void removeStartView() {
		if (startView != null) {
			startView.dispose();
			startView = null;
		}
	}

	public void register(String name, String password) {
		controller.register(name, password);
		hideRegisterFrame();
	}

	public void showRegisterFrame() {
		createRegisterView();
		registerView.setVisible(true);
	}

	public void hideRegisterFrame() {
		registerView.setVisible(false);
	}

	public void showStartView() {
		createStartView();
		startView.setVisible(true);
	}

	public void hideStartView() {
		removeRegisterView();
	}
	
	public void close(){
		removeRegisterView();
		removeStartView();
	}

}
