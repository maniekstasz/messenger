package messenger.model.dao;

import org.hibernate.Session;

import messenger.model.User;

public class UserDao extends AbstractDao<User> {

	public User get(String username){
		Session session = currentSession();
		User user = (User) session.createQuery("from User where username =:username").setString("username", username).uniqueResult();
		return user;
	}
	
	
	@Override
	public Class<?> getType() {
		return User.class;
	}

}
