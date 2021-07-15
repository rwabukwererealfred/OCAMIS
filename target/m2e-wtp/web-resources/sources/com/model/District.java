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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name="DISTRICT")
public class District implements Serializable {

	@Id
	@Column(length =8)
	private String districtID;
	@Column
	private String name;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="province")
	private Province province;
	
	@Transient
	private List<Employee>employee;
	
	@Transient
	private List<Applicant>applicant;
	
	@Transient
	private List<Orphanage>orphanage;
	
	@Transient
	private List<Malaika>malaika;
	
	
	
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public List<Applicant> getApplicant() {
		return applicant;
	}
	public void setApplicant(List<Applicant> applicant) {
		this.applicant = applicant;
	}
	public List<Orphanage> getOrphanage() {
		return orphanage;
	}
	public void setOrphanage(List<Orphanage> orphanage) {
		this.orphanage = orphanage;
	}
	public List<Malaika> getMalaika() {
		return malaika;
	}
	public void setMalaika(List<Malaika> malaika) {
		this.malaika = malaika;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public District(String districtID, String name, Province province, List<Employee> employee,
			List<Applicant> applicant, List<Orphanage> orphanage, List<Malaika> malaika) {
		super();
		this.districtID = districtID;
		this.name = name;
		this.province = province;
		this.employee = employee;
		this.applicant = applicant;
		this.orphanage = orphanage;
		this.malaika = malaika;
	}
	public District() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
