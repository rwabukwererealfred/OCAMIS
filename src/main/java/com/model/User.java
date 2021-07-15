package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="USER")
public class User implements Serializable {

	@Id
	@Column(length =38)
	private String userID;
	@Column(unique=true)
	private String username;
	@Column(length=33)
	private String password;
	@Column
	private String access;
	@Column
	private boolean state;
	@OneToOne
	private Employee employee;
	
	@OneToOne
	private Applicant applicant;
	
	
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public User(String userID, String username, String password, String access, boolean state, Employee employee,
			Applicant applicant) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.access = access;
		this.state = state;
		this.employee = employee;
		this.applicant = applicant;
	}
	public User() {
		super();
		
	}
	
	
}
