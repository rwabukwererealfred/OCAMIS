package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Malaika;

@Repository
public class MalaikaDao extends GenericDaoImpl<Malaika>{

	
	@SuppressWarnings("unchecked")
	public List<Malaika>MalaikaList(){
		Query q = sessionfactory().createQuery("from Malaika");
		return q.list();
	}

	@Override
	public void remove(Malaika t) {
		sessionfactory().delete(t);
		
	}
}
