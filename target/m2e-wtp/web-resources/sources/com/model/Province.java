package com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@SuppressWarnings("serial")
@Entity
@Table(name="PROVINCE")
public class Province implements Serializable {
	
	@Id
	@Column(length =8)
	private String provinceId;
	private String name;

	@Transient
	private List<District>district;

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<District> getDistrict() {
		return district;
	}

	public void setDistrict(List<District> district) {
		this.district = district;
	}

	public Province(String provinceId, String name, List<District> district) {
		super();
		this.provinceId = provinceId;
		this.name = name;
		this.district = district;
	}

	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
