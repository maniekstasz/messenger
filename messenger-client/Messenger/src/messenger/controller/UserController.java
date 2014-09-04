package messenger.controller;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import messenger.model.Message;
import messenger.model.User;
import messenger.views.ConversationView;
import messenger.views.MainView;
import messenger.views.RegisterView;
import messenger.views.SearchView;
import messenger.views.StartView;

public class UserController {

	private User user;
	private Controller controller;

	private MainView mainView;
	private SearchView searchView;
	private Map<Long, ConversationView> conversationMap;

	public UserController(User user, Controller controller) {
		this.user = user;
		this.controller = controller;
	}

	public void onLoggedIn() {
		startClient();
	}

	public void onMessageIn(Message message) {
		Long interlocutorId = message.getInterlocutor();
		User interlocutor = getContact(interlocutorId);
		if (interlocutor == null) {
			interlocutor = new User();
			interlocutor.setId(interlocutorId);
			interlocutor.setUsername(interlocutorId.toString());
		}
		if (conversationMap.containsKey(message.getInterlocutor())) {
			conversationMap.get(message.getInterlocutor()).addMessage(
					message.getMessage(), interlocutor.getUsername());
			conversationMap.get(message.getInterlocutor()).setVisible(true);
		} else {
			ConversationView cView = new ConversationView(this, interlocutor);
			cView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cView.setVisible(true);
			cView.setResizable(false);
			conversationMap.put(message.getInterlocutor(), cView);
			conversationMap.get(message.getInterlocutor()).addMessage(
					message.getMessage(), interlocutor.getUsername());
		}
	}

	public void sendMessage(String msg, User interlocutor) {
		Message message = new Message(user, interlocutor.getId(), msg,
				new Date(), Message.Type.SENDED);
		controller.onMessageOut(message);
	}

	public User getUser() {
		return user;
	}

	public static String arrayToString(char[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; ++i) {
			str.append(array[i]);
		}
		return str.toString();
	}

	public void removeAllViews() {
		removeSearchView();
		removeConversationWindows();
		mainView.dispose();
	}

	/**
	 * Po poprawnym zalogowaniu wykonuje sie start wlasciwej aplikacji
	 * klienckiej
	 */
	public void startClient() {
		conversationMap = new ConcurrentHashMap<Long, ConversationView>();
		mainView = new MainView(this);
		mainView.setVisible(true);
		mainView.setResizable(false);
		mainView.contactList.setListData(user.getContacts().toArray());
		mainView.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				logout();
			}
		});
	}

	public void showConversationFrame() {
		createConversationFrame();
	}

	private void createConversationFrame() {
		int selected = mainView.contactList.getSelectedIndex();
		if (selected != -1) {
			User interlocutor = user.getContacts().get(selected);
			if (conversationMap.containsKey(interlocutor.getId())) {
				conversationMap.get(interlocutor.getId()).setVisible(true);
			} else {
				ConversationView cView = new ConversationView(this,
						interlocutor);
				cView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				cView.setVisible(true);
				cView.setResizable(false);
				conversationMap.put(interlocutor.getId(), cView);
			}
		}
	}

	public void reportError(String error) {
		JOptionPane.showMessageDialog(null, error, "Błąd",
				JOptionPane.ERROR_MESSAGE);
	}

	public void loginFailed(String message) {
		JOptionPane.showMessageDialog(null, "Logowanie nieudane\n" + message);
	}

	public void find(String name) {
		controller.find(name, user);
	}

	public void showSearchFrame() {
		if (searchView != null) {
			searchView.setVisible(true);
		} else {
			createSearchView();
		}
	}

	public void createSearchView() {
		searchView = new SearchView(this);
		searchView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchView.setVisible(true);
		searchView.setResizable(false);
	}

	public void hideSearchFrame() {
		removeSearchView();
	}

	private void removeSearchView() {
		if (searchView != null) {
			searchView.dispose();
			searchView = null;
		}
	}

	private void removeConversationWindows() {
		List<ConversationView> views = new ArrayList<ConversationView>(
				conversationMap.values());
		for (int i = 0; i < views.size(); i++) {
			views.get(i).dispose();
		}
		conversationMap.clear();
	}

	public void queryFound(User contact) {
		int result = JOptionPane.showConfirmDialog(null,
				"Znaleziono użytkownika spełaniającego dane kryteria:\n"
						+ contact.getUsername() + " "
						+ "Dodać go do listy kontaktów?",
				"Wyniki wyszukiwania", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			if(!contact.equals(user)){
				user.addContact(contact);
				mainView.contactList.setListData(user.getContacts().toArray());
			}else{
				JOptionPane.showMessageDialog(null,"Nie możesz dodać siebie");
			}
		}

		hideSearchFrame();
	}

	public void queryNotFound() {
		JOptionPane.showMessageDialog(null,
				"Nie znaleziono takiego użytkownika");
		hideSearchFrame();
	}

	public void deleteContact() {
		int selected = mainView.contactList.getSelectedIndex();
		if (selected != -1) {
			user.getContacts().remove(selected);
			// TODO
			mainView.contactList.setListData(user.getContacts().toArray());
		}

		// TODO remove conversation window if exists
	}

	public void close() {
		saveContacts();
		removeAllViews();
	}

	private void saveContacts() {
		controller.updateUser(user);
	}

	public void logout() {
		controller.logout(user);
	}

	private User getContact(Long id) {
		for (int i = 0; i < user.getContacts().size(); i++) {
			if (user.getContacts().get(i).getId().equals(id)) {
				return user.getContacts().get(i);
			}
		}
		return null;
	}

}
