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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Past;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.dao.MalaikaDao;

@SuppressWarnings("serial")
@Entity
@Table(name="MALAIKA")

public class Malaika extends SuperClass implements Serializable {
	
	@Id
	@Column(length =38)
	private String mID;
	@Column(length=10)
	private String phoneNumber;
	@Column
	@Past(message="please provide good date of birth")
	private LocalDate dob;
	
	@Transient
	private List<Orphan>orphan;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="district")
	private District district;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="malaika")
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
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public List<Orphan> getOrphan() {
		return orphan;
	}
	public void setOrphan(List<Orphan> orphan) {
		this.orphan = orphan;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Malaika(String mID, String phoneNumber, LocalDate dob, List<Orphan> orphan, District district) {
		super();
		this.mID = mID;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.orphan = orphan;
		this.district = district;
	}
	public Malaika() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
