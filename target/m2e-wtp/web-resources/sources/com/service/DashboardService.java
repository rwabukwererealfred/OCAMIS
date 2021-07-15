package com.service;

import java.util.List;

import com.model.Applicant;
import com.model.Employee;
import com.model.Orphan;
import com.model.User;

public interface DashboardService {

	List<Employee>empList();
	List<User>userList();
	List<Applicant>applicantList();
	List<Orphan>orphanList();
	String UpdateUser(User user);
	User findId(String user);
	User findCode(String code);
	Applicant findID(String id);
	Employee findEmID(String id);
	void updateEmployee(Employee employee);
	void updateApplicant(Applicant applicant);
	List<Orphan>orphanfROM(String orphan);
	List<Orphan>fromGuardian(String orphan);
	
	
}
