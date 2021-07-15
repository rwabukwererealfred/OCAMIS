package com.model;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name="APPLICANT")
public class Applicant extends SuperClass implements Serializable{
	@Id
	@Column(length =38)
	private String appId;
	@Column(length=10)
	private String phoneNumber;
	@Column
	@Email(message="please provide good email")
	private String email;
	@Column
	private String martialStatus;
	@Column
	private String motivation;
	@Column
	private String income;
	@Column
	@Past(message="please provide good date of birth")
	private LocalDate dob;
	
	@Transient
	private List<FamilyMember>family;
	
	@Transient
	private List<Request>request;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="district")
	private District district;
	
	
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
	}
	public List<FamilyMember> getFamily() {
		return family;
	}
	public void setFamily(List<FamilyMember> family) {
		this.family = family;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMartialStatus() {
		return martialStatus;
	}
	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public Applicant(String appId, String phoneNumber, String email, String martialStatus, String motivation,
			String income, LocalDate dob, List<FamilyMember> family, List<Request> request, District district) {
		super();
		this.appId = appId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.martialStatus = martialStatus;
		this.motivation = motivation;
		this.income = income;
		this.dob = dob;
		this.family = family;
		this.request = request;
		this.district = district;
	}
	public Applicant() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
