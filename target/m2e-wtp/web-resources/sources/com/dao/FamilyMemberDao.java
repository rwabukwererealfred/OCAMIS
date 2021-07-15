package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.FamilyMember;

@Repository
public class FamilyMemberDao extends GenericDaoImpl<FamilyMember> {

	@Override
	public void remove(FamilyMember t) {
		FamilyMember mm = (FamilyMember) sessionfactory().merge(t);
		sessionfactory().delete(mm);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<FamilyMember>MyFamily(String applicant){
		Query q = sessionfactory().createQuery("From FamilyMember where applicant.appId =:applicant");
		q.setString("applicant", applicant);
		return q.list();
	}

	
}
