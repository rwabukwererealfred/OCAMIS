package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name="REQUEST")
public class Request implements Serializable{
	@Id
	@Column(length =38)
	private String reqId;
	@Column
	private String status;
	@Column
	private Date startDate;
	@Column(length=500)
	private String message;
	
	@Transient
	private List<Document>document;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="applicant")
	private Applicant applicant;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="orphan")
	private Orphan orphan;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="employee")
	private Employee employee;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Orphan getOrphan() {
		return orphan;
	}
	public void setOrphan(Orphan orphan) {
		this.orphan = orphan;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public List<Document> getDocument() {
		return document;
	}
	public void setDocument(List<Document> document) {
		this.document = document;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public Request(String reqId, String status, Date startDate, String message, List<Document> document,
			Applicant applicant, Orphan orphan, Employee employee) {
		super();
		this.reqId = reqId;
		this.status = status;
		this.startDate = startDate;
		this.message = message;
		this.document = document;
		this.applicant = applicant;
		this.orphan = orphan;
		this.employee = employee;
	}
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
