package messenger.server;

import org.hibernate.Session;

import messenger.handler.HibernateUtil;

public class SessionInitializer implements Runnable{

	@Override
	public void run() {
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
	}

}
