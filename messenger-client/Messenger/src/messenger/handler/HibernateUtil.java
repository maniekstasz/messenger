package messenger.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	public static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void initialize(){
		currentSession();
	}
	
	public static Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static Session beginTransaction() {
		Transaction tx = null;
		Session session = HibernateUtil.currentSession();
		tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		return session;
	}

	public static void commit() {
		Transaction tx = null;
		try {
			Session session = HibernateUtil.currentSession();
			tx = session.getTransaction();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e; // or display error message
		}
	}
}
