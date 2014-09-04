package messenger.model.dao;

import java.util.List;

import org.hibernate.Session;

import messenger.model.Message;

public class MessageDao extends AbstractDao<Message> {
	
	public void deleteAll(List<Long> messages){
		Session session = currentSession();
		session.createQuery("delete from Message where id in (:ids)").setParameterList("ids", messages).executeUpdate();
	}
	@Override
	public Class<?> getType() {
		return Message.class;
	}
	
}
