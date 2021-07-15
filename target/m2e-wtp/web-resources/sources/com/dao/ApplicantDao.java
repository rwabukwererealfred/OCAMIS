package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Applicant;

@Repository
public class ApplicantDao extends GenericDaoImpl<Applicant>{

	@Override
	public void remove(Applicant t) {
		sessionfactory().delete(t);
		
	}

	@SuppressWarnings("unchecked")
	public List<Applicant>appList(){
		Query q = sessionfactory().createQuery("From Applicant");
		return q.list();
	}

}
