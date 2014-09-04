package messenger.model.service;

import java.util.List;

import messenger.handler.ApplicationContext;
import messenger.handler.HibernateUtil;
import messenger.model.Message;
import messenger.model.User;
import messenger.model.dao.MessageDao;
import messenger.model.dao.UserDao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MessageService {

	private UserDao userDao = ApplicationContext.userDao;
	private MessageDao messageDao = ApplicationContext.messageDao;

	public void add(Message msg) {
		HibernateUtil.beginTransaction();
		User owner = userDao.get(msg.getOwner().getId());
		if (owner == null)
			return;
		msg.setInterlocutor(msg.getInterlocutor());
		msg.setOwner(owner);
		messageDao.add(msg);
		HibernateUtil.commit();
	}

//	public void delete(Long id) {
//		HibernateUtil.beginTransaction();
//		messageDao.delete(id);
//		HibernateUtil.commit();
//	}
//
//	public void deleteAll(List<Long> messages) {
//		HibernateUtil.beginTransaction();
//		messageDao.deleteAll(messages);
//		HibernateUtil.commit();
//	}

}
