package com.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

@SuppressWarnings("serial")
@Entity
@Table(name="FAMILYMEMBER")
public class FamilyMember extends SuperClass implements Serializable{
	
	@Id
	@Column(length =38)
	private String familyId;
	
	@Column
	private String relationship;
	@Column
	private String phoneNumber;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="applicant")
	private Applicant applicant;
	@Column
	@Past(message="please provide good date of birth")
	private LocalDate dob;
	
	
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	
	
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public FamilyMember(String familyId, String relationship, String phoneNumber, Applicant applicant, LocalDate dob) {
		super();
		this.familyId = familyId;
		this.relationship = relationship;
		this.phoneNumber = phoneNumber;
		this.applicant = applicant;
		this.dob = dob;
	}
	public FamilyMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
