package com.model;

import java.io.Serializable;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name="ORPHANAGE")
public class Orphanage implements Serializable {
	@Id
	@Column(length =38)
	private String orphanageId;
	@Column
	private String name;
	@Column
	private String lastName;
	@Column
	private String director;
	@Column(length=10)
	private String phoneNumber;
	@Column
	@Email
	private String email;
	
	@Transient
	private List<Orphan>orphan;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="district")
	private District district;
	
	
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
	public String getOrphanageId() {
		return orphanageId;
	}
	public void setOrphanageId(String orphanageId) {
		this.orphanageId = orphanageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Orphanage(String orphanageId, String name, String lastName, String director, String phoneNumber,
			String email, List<Orphan> orphan, District district) {
		super();
		this.orphanageId = orphanageId;
		this.name = name;
		this.lastName = lastName;
		this.director = director;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.orphan = orphan;
		this.district = district;
	}
	public Orphanage() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
