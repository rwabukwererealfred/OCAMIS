package com.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.RequestForTransfer;

@Repository
public class RequestForTransferDao extends GenericDaoImpl<RequestForTransfer> {

	@SuppressWarnings("unchecked")
	public List<RequestForTransfer>findAll(){
		Query q = sessionfactory().createQuery("from RequestForTransfer where status = 'askForOrphan'");
		return q.list();
	}  
	@Override
	public void remove(RequestForTransfer t) {
		sessionfactory().delete(t);
		
	}

}
