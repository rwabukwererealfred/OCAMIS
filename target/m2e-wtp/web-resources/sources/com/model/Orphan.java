package com.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Past;


import com.dao.OrphanDao;

@SuppressWarnings("serial")
@Entity
@Table(name="ORPHAN")
@NamedQueries({@NamedQuery(name = OrphanDao.QUERY_NAME.findOrphans,query = OrphanDao.QUERY.findOrphans),
	@NamedQuery(name=OrphanDao.QUERY_NAME.fromGuardian,query = OrphanDao.QUERY.fromGuardian)})
public class Orphan extends SuperClass implements Serializable{
	
	@Id
	@Column(length =38)
	private String orphanId;
	@Column
	private String height;
	@Column
	private String weight;
	@Column
	private String hobbit;
	@Column
	private String Status;
	@Column
	private String motherName;
	@Column
	private String fatherName;
	@Column
	private String religion;
	@Column
	private String education;
	@Column
	private String file;
	@Column
	@Past(message="please provide good date of birth")
	private Date dob;
	@Column
	private Date transferDate;
	@Column
	private String comment;
	@Column
	@Lob
	private byte[]profilePic;
	@Transient
	private String filename;
	@Transient
	private String filetype;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="malaika")
	private Malaika malaika;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="orphanage")
	private Orphanage orphanage;
	
	@Transient
	private List<Request>request;
	
	
	
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
	}
	public Orphanage getOrphanage() {
		return orphanage;
	}
	public void setOrphanage(Orphanage orphanage) {
		this.orphanage = orphanage;
	}
	public Malaika getMalaika() {
		return malaika;
	}
	public void setMalaika(Malaika malaika) {
		this.malaika = malaika;
	}
	public String getOrphanId() {
		return orphanId;
	}
	public void setOrphanId(String orphanId) {
		this.orphanId = orphanId;
	}
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHobbit() {
		return hobbit;
	}
	public void setHobbit(String hobbit) {
		this.hobbit = hobbit;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public Orphan(String orphanId, String height, String weight, String hobbit, String status, String motherName,
			String fatherName, String religion, String education, String file, Date dob, String comment,
			byte[] profilePic, String filename, String filetype, Malaika malaika, Orphanage orphanage,
			List<Request> request) {
		super();
		this.orphanId = orphanId;
		this.height = height;
		this.weight = weight;
		this.hobbit = hobbit;
		Status = status;
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.religion = religion;
		this.education = education;
		this.file = file;
		this.dob = dob;
		this.comment = comment;
		this.profilePic = profilePic;
		this.filename = filename;
		this.filetype = filetype;
		this.malaika = malaika;
		this.orphanage = orphanage;
		this.request = request;
	}
	public Orphan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
