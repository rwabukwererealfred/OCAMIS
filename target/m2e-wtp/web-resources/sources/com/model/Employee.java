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
@Table(name="EMPLOYEE")
public class Employee extends SuperClass implements Serializable {

	@Id
	@Column(length =38)
	private String empId;
	@Column
	@Email(message="please provide correct email")
	private String email;
	@Column(length=10)
	private String phoneNumber;
	@Column
	private String code;
	@Column
	@Past(message="please provide good date of birth")
	private LocalDate dob;
	
	@Transient
	private List<Request>request;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="district")
	private District district;
	
	@Transient
	private List<RequestForTransfer>requestfortransfer;
	
	
	public List<RequestForTransfer> getRequestfortransfer() {
		return requestfortransfer;
	}
	public void setRequestfortransfer(List<RequestForTransfer> requestfortransfer) {
		this.requestfortransfer = requestfortransfer;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Employee(String empId, String email, String phoneNumber, String code, LocalDate dob, List<Request> request,
			District district) {
		super();
		this.empId = empId;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.code = code;
		this.dob = dob;
		this.request = request;
		this.district = district;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
