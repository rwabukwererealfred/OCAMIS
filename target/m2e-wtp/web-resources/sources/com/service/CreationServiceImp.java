package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApplicantDao;
import com.dao.DistrictDao;
import com.dao.DocumentDao;
import com.dao.EmployeeDao;
import com.dao.MalaikaDao;
import com.dao.OrphanDao;
import com.dao.OrphanageDao;
import com.dao.ProvinceDao;
import com.dao.RequestDao;
import com.dao.UserDao;
import com.model.Applicant;
import com.model.District;
import com.model.Document;
import com.model.Employee;
import com.model.Malaika;
import com.model.Orphan;
import com.model.Orphanage;
import com.model.Province;
import com.model.Request;
import com.model.User;

@Service
public class CreationServiceImp extends Transaction implements CreationService{

	@Autowired
	private ApplicantDao applicantdao;
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private ProvinceDao provincedao;
	@Autowired
	private DistrictDao districtdao;
	@Autowired
	private OrphanDao orphandao;
	@Autowired
	private OrphanageDao orphanagedao;
	@Autowired
	private MalaikaDao malaikadao;
	@Autowired
	private RequestDao requestdao;
	@Autowired
	private DocumentDao documentdao;
	
	@Override
	public String createApplicant(Applicant applicant) {
		applicantdao.save(applicant);
		return "";
	}

	@Override
	public String createEmployee(Employee employee) {
		employeedao.save(employee);
		return "new employee is well created";
	}

	@Override
	public String createUser(User user) {
		userdao.save(user);
		return "account is well created";
	}

	@Override
	public List<Province> provinceList() {
		return provincedao.provinceList();
	}

	@Override
	public List<District> allDistrict(String province) {
		
		return districtdao.getDistrict(province);
	}

	@Override
	public District getOne(String district) {
		return districtdao.findOne(district);
	}

	@Override
	public Employee getOneEmp(String nationalityID) {
		
		return(Employee) employeedao.getOneem(nationalityID);
	}

	@Override
	public String addOrphan(Orphan orphan) {
		orphandao.save(orphan);
		return "";
	}

	@Override
	public String addOrphanage(Orphanage orphanage) {
		orphanagedao.save(orphanage);
		return "";
	}

	@Override
	public String addMalaika(Malaika malaika) {
		malaikadao.save(malaika);
		return "";
	}

	@Override
	public List<Orphanage> orphanageList() {
		return orphanagedao.orphanageList();
	}

	@Override
	public List<Malaika> malaikaList() {
		return malaikadao.MalaikaList();
	}

	@Override
	public Orphanage findOrphage(String id) {
		
		return orphanagedao.findOne(id);
	}

	@Override
	public Malaika findMalaika(String id) {
		
		return malaikadao.findOne(id);
	}

	@Override
	public List<Request> allRequest() {
		return requestdao.allRequest();
	}

	@Override
	public List<Employee> empList() {
		return employeedao.findAllEmp();
	}

	@Override
	public Request findRequest(String id) {
		
		return requestdao.findOne(id);
	}

	@Override
	public Employee findID(String id) {
		
		return employeedao.findOne(id);
	}

	@Override
	public void update(Request request) {
		requestdao.update(request);
		
	}

	@Override
	public void updateOrphanage(Orphanage orphanage) {
		orphanagedao.update(orphanage);
		
	}

	@Override
	public List<Orphan> orphanList() {
		return orphandao.orphanList();
	}

	@Override
	public List<Request> findAllRequest() {
		
		return requestdao.findAll();
	}

	@Override
	public List<Request> findWaiting() {
		
		return requestdao.findWaitingrequest();
	}

	@Override
	public List<Document> findDocument(String id) {
		return documentdao.findList(id);
	}

	@Override
	public void updateMalaika(Malaika malaika) {
	malaikadao.update(malaika);
		
	}

	@Override
	public Employee findCode(String code) {
		
		return employeedao.getcode(code);
	}

	@Override
	public List<District> allDistrict() {
		return districtdao.getAll();
	}

	

	

}
