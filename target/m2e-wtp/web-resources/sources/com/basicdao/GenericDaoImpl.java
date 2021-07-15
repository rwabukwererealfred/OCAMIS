package com.basicdao;


import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoImpl<T extends Serializable> extends GenericDao<T> {
 
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	protected Session sessionfactory() {
		return sessionFactory.getCurrentSession();
	}
	
	
	

}
