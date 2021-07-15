package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Request;

@Repository
public class RequestDao extends GenericDaoImpl<Request> {

	
	@SuppressWarnings("unchecked")
	public List<Request>allRequest(){
		Query q = sessionfactory().createQuery("from Request where status ='Applying'");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request>findWaitingrequest(){
		Query q = sessionfactory().createQuery("from Request where status ='Waiting'");
		return q.list();
	}
	@SuppressWarnings("unchecked")
	public List<Request>findByStatus(String status){
		Query q = sessionfactory().createQuery("From Request where status =:status");
		q.setString("status", status);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Request>findAll(){
		Query q = sessionfactory().createQuery("from Request");
		return q.list();
	}  

	@Override
	public void remove(Request t) {
		sessionfactory().delete(t);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Request>findMyRequest(String employee){
		Query q = sessionfactory().createQuery("From Request where employee.empId =:employee");
		q.setString("employee", employee);
		return q.list();
	}
	@SuppressWarnings("unchecked")
	public List<Request>findUserReq(String applicant){
		Query q = sessionfactory().createQuery("From Request where applicant.appId =:applicant");
		q.setString("applicant", applicant);
		return q.list();
	}
}
