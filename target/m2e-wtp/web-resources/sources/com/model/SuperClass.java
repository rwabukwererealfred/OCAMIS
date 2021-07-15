package com.model;



import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class SuperClass {
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(length=8)
	private String gender;
	@Column
	private String nationalityId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationalityId() {
		return nationalityId;
	}
	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}
	public SuperClass(String firstName, String lastName, String gender, String nationalityId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationalityId = nationalityId;
	}
	public SuperClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
