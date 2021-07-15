package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApplicantDao;
import com.dao.EmployeeDao;
import com.dao.OrphanDao;
import com.dao.UserDao;
import com.model.Applicant;
import com.model.Employee;
import com.model.Orphan;
import com.model.User;

@Service
public class DashboardServiceImp extends Transaction implements DashboardService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private ApplicantDao applicantdao;
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private OrphanDao orphandao;
	
	
	@Override
	public List<Employee> empList() {
		
		return employeedao.findAllEmp();
	}

	@Override
	public List<User> userList() {
		return userdao.userList();
	}

	@Override
	public List<Applicant> applicantList() {
		return applicantdao.appList();
	}

	@Override
	public List<Orphan> orphanList() {
		return orphandao.allorphanList();
	}

	@Override
	public String UpdateUser(User user) {
		userdao.update(user);
		return "";
	}

	@Override
	public User findId(String user) {
		return userdao.findOne(user);
	}

	@Override
	public User findCode(String code) {
		
		return userdao.checkUser(code);
	}

	@Override
	public Applicant findID(String id) {
		
		return applicantdao.findOne(id);
	}

	@Override
	public Employee findEmID(String id) {
		
		return employeedao.findOne(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeedao.update(employee);
		
	}

	@Override
	public void updateApplicant(Applicant applicant) {
		applicantdao.update(applicant);
		
	}

	@Override
	public List<Orphan> orphanfROM(String orphan) {
		return orphandao.findOrphans(orphan);
	}

	@Override
	public List<Orphan> fromGuardian(String orphan) {
		
		return orphandao.fromGuardian(orphan);
	}

}
