package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Orphanage;

@Repository
public class OrphanageDao extends GenericDaoImpl<Orphanage> {

	@SuppressWarnings("unchecked")
	public List<Orphanage>orphanageList(){
		Query q = sessionfactory().createQuery("from Orphanage");
		return q.list();
	}

	@Override
	public void remove(Orphanage t) {
		sessionfactory().delete(t);
		
	}
	
}
