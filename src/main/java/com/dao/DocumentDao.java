package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Document;
import com.util.QueryParameters;

@Repository
public class DocumentDao extends GenericDaoImpl<Document> {
	public static class QUERY{
		public static final String findList = "From Document  where request.reqId = :request";
	}
	public static class QUERY_NAME{
		public static final String findList = "Document.findList";
	}
	
	public List<Document>findList(final String request){
	QueryParameters q = new QueryParameters().add("request", request);
	return this.executeNamedQueryMultiple(QUERY_NAME.findList,q);
	}

	@SuppressWarnings("unchecked")
	public List<Document> findbyRequest(String request) {
		Query q = sessionfactory().createQuery("from Document where request =:request");
		q.setString("request ", request );
		return q.list();
	}

	@Override
	public void remove(Document t) {
		sessionfactory().delete(t);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Document>mydocumentList(String document){
		Query q = sessionfactory().createQuery("From Document where request.applicant.appId =:document");
		q.setString("document", document);
		return q.list();
		
	}

}
