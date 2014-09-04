package messenger.model.service;

import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import messenger.handler.ApplicationContext;
import messenger.handler.HibernateUtil;
import messenger.model.User;
import messenger.model.dao.UserDao;

public class UserService {

	private UserDao userDao = ApplicationContext.userDao;
	static int a = 0;

	public User get(String username) {
		HibernateUtil.beginTransaction();
		User user = userDao.get(username);
		Hibernate.initialize(user);
		HibernateUtil.commit();
		return user;
	}

	public User get(Long id) {
		HibernateUtil.beginTransaction();
		User user = userDao.get(id);
		Hibernate.initialize(user);
		HibernateUtil.commit();
		return user;
	}

	public User getWithContacts(Long id) {
		try {
			HibernateUtil.beginTransaction();
			User user = userDao.get(id);
			user.getContacts().size();
			HibernateUtil.commit();
			HibernateUtil.beginTransaction();
			return user;
		} catch (ObjectNotFoundException e) {
			return null;
		}

	}

	// public User getWithMessages(String username) {
	// HibernateUtil.beginTransaction();
	// User user = userDao.get(username);
	// if (user == null) {
	// return null;
	// }
	// user.getPendingMessages().size();
	// Hibernate.initialize(user);
	// HibernateUtil.commit();
	// return user;
	// }

	public void add(User user) {
		HibernateUtil.beginTransaction();
		userDao.add(user);
		HibernateUtil.commit();
	}

	// public void deleteMessages(User user) {
	// HibernateUtil.beginTransaction();
	// user.setPendingMessages(null);
	// userDao.add(user);
	// HibernateUtil.commit();
	// }

	public void registerClient(User c) {

	}
}
