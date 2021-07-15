package com.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Orphan;
import com.util.QueryParameters;


@Repository
public class OrphanDao extends GenericDaoImpl<Orphan> {
	public static class QUERY {
		public static final String findOrphans="From Orphan where orphanage.orphanageId =:orphanage";
		public static final String fromGuardian = "From Orphan where malaika.mID =:malaika";
	}
	public static class QUERY_NAME{
		public static final String findOrphans = "Orphan.findOrphans";
		public static final String fromGuardian = "Orphan.fromGuardian";
	}
	
	public List<Orphan>findOrphans(final String orphanage){
		QueryParameters query = new QueryParameters().add("orphanage", orphanage);
		return this.executeNamedQueryMultiple(QUERY_NAME.findOrphans,query);
	}
	public List<Orphan>fromGuardian(final String malaika){
		QueryParameters q = new QueryParameters().add("malaika", malaika);
		return this.executeNamedQueryMultiple(QUERY_NAME.fromGuardian, q);
	}

	@SuppressWarnings("unchecked")
	public List<Orphan>orphanList(){
		Query q = sessionfactory().createQuery("from Orphan where Status ='Available'");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Orphan>allorphanList(){
		Query q = sessionfactory().createQuery("from Orphan");
		return q.list();
	}
	@SuppressWarnings("unchecked")
	public List<Orphan>selectByStatus(String Status){
		Query q = sessionfactory().createQuery("from Orphan where Status =:Status");
		q.setString("Status", Status);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Orphan> findRange(Date fromdate, Date todate) {
		Query q = sessionfactory().createQuery("from Orphan c where c.dob between ? and ?");
		q.setDate(0, fromdate);
		q.setDate(1, todate);
		
		return q.list();
	}

	@Override
	public void remove(Orphan t) {
		sessionfactory().delete(t);
		
	}
	
}
