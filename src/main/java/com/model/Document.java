package com.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.dao.DocumentDao;

@SuppressWarnings("serial")
@Entity
@Table(name="DOCUMENT")
@NamedQueries({@NamedQuery(name = DocumentDao.QUERY_NAME.findList, query = DocumentDao.QUERY.findList) })
public class Document implements Serializable {
	
	@Id
	@Column(length =38)
	private String docId;
	@Column
	private String documentType;
	@Column
	private String image;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="request")
	private Request request;
	
	
	
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Document(String docId, String documentType, String image, Request request) {
		super();
		this.docId = docId;
		this.documentType = documentType;
		this.image = image;
		this.request = request;
	}
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
