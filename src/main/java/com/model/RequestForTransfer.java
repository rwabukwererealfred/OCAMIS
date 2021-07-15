package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class RequestForTransfer implements Serializable {
	@Id
	@Column(length =38)
	private String transUUID;
	@Column
	private String status;
	@Column
	private Date requestDate;
	
	@ManyToOne
	@JoinColumn(name="employee")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="malaika")
	private Malaika malaika;
	
	@ManyToOne
	@JoinColumn(name="orphanage")
	private Orphanage orphanage;
	
	
	
	
	public Orphanage getOrphanage() {
		return orphanage;
	}

	public void setOrphanage(Orphanage orphanage) {
		this.orphanage = orphanage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getTransUUID() {
		return transUUID;
	}

	public void setTransUUID(String transUUID) {
		this.transUUID = transUUID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Malaika getMalaika() {
		return malaika;
	}

	public void setMalaika(Malaika malaika) {
		this.malaika = malaika;
	}

	public RequestForTransfer(String transUUID, String status, Date requestDate, Employee employee, Malaika malaika,
			Orphanage orphanage) {
		super();
		this.transUUID = transUUID;
		this.status = status;
		this.requestDate = requestDate;
		this.employee = employee;
		this.malaika = malaika;
		this.orphanage = orphanage;
	}

	public RequestForTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
