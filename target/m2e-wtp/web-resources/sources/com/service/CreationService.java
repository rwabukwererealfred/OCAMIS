package com.service;

import java.util.List;

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

public interface CreationService {

	String createApplicant(Applicant applicant);
	String createEmployee(Employee employee);
	String createUser(User user);
	List<Province>provinceList();
	List<District>allDistrict(String province);
	District getOne(String district);
	Employee getOneEmp(String nationalityID);
	String addOrphan(Orphan orphan);
	String addOrphanage(Orphanage orphanage);
	String addMalaika(Malaika malaika);
	List<Orphanage>orphanageList();
	List<Malaika>malaikaList();
	Orphanage findOrphage(String id);
	Malaika findMalaika(String id);
	List<Request>allRequest();
	List<Employee>empList();
	Request findRequest(String id);
	Employee findID(String id);
	void update (Request request);
	void updateOrphanage(Orphanage orphanage);
	List<Orphan>orphanList();
	List<Request>findAllRequest();
	List<Request>findWaiting();
	List<Document>findDocument(String id);
	void updateMalaika(Malaika malaika);
	Employee findCode(String code);
	List<District>allDistrict();
	
	}
