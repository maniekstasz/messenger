package messenger.model.dao;

import messenger.model.Message;

public class MessageDao extends AbstractDao<Message> {
	
	
	@Override
	public Class<?> getType() {
		return Message.class;
	}
	
}
