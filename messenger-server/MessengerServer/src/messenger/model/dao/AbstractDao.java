package messenger.model.dao;

import messenger.handler.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("unchecked")
public abstract class AbstractDao<EntityT> {


	protected Session currentSession() {
		return HibernateUtil.currentSession();
	}

	public void add(EntityT object){
		currentSession().saveOrUpdate(object);
	}
	public void save(EntityT object){
		add(object);
	}
	public void update(EntityT object){
		currentSession().update(object);
	}
	public EntityT get(Long id){
		return (EntityT) currentSession().load(getType(), id);
	}
	public EntityT getInitialized(Long id){
		EntityT object  = get(id);
		Hibernate.initialize(object);
		return object;
	}
	
	public void delete(Long id){
		EntityT object = (EntityT) currentSession().load(getType(), id);
		if(object != null){ // TODO wyrzucic wyjatek
			currentSession().delete(object);
		}
	}

	public abstract Class<?> getType();

}
