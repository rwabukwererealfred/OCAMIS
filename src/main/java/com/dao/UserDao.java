package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.User;

@Repository
public class UserDao extends GenericDaoImpl<User> {
	
	public User logged(String username) {
		Query q = sessionfactory().createQuery("from User where username =:username");
		q.setString("username", username);
		return(User)q.uniqueResult();
	}

	@Override
	public void remove(User t) {
		sessionfactory().delete(t);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<User>userList(){
		Query q = sessionfactory().createQuery("from User");
		return q.list();
	}
	public User checkUser(String employee) {
		Query q = sessionfactory().createQuery("from User where employee.code=:employee");
		q.setString("employee", employee);
		return(User)q.uniqueResult();
	}

}
