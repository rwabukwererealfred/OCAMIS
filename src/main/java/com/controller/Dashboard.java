package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Applicant;
import com.model.Employee;
import com.model.Orphan;
import com.model.User;
import com.service.DashboardService;
import com.service.ReportService;

@Component
@ManagedBean
@SessionScoped
public class Dashboard {

	@Autowired
	private DashboardService service;
	@Autowired
	private ReportService report;
	private User user;
	private List<User> userList;
	private List<String> images;
	private List<Orphan> orphanList1;
	private Employee employee;
	private Applicant applicant;

	public Dashboard() {
		applicant = new Applicant();
		employee = new Employee();
		orphanList1 = new ArrayList<Orphan>();
		user = new User();
		userList = new ArrayList<User>();
		images = new ArrayList<String>();

	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public List<Orphan> getOrphanList1() {
		return orphanList1;
	}

	public void setOrphanList1(List<Orphan> orphanList1) {
		this.orphanList1 = orphanList1;
	}

	public void userpage1() throws IOException {
		userList = report.userList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Users.jsf");
	}

	@PostConstruct
	public void init1() {
		for (int i = 1; i <= 5; i++) {
			images.add("nature" + i + ".jpg");
		}
	}

	@PostConstruct
	public void init() {
		for (User u : report.userList()) {
			userList.add(u);
		}
	}

	public void activateUser(User user) {
		this.user = user;
		User us = service.findId(this.user.getUserID());
		if (us.isState()) {
			us.setState(false);
			service.UpdateUser(us);
			successMessage("success", "user is disabled");
		} else {
			us.setState(true);
			successMessage("success", "user is unabled");
			service.UpdateUser(us);
		}
		userList = report.userList();
	}

	@PostConstruct
	public List<User> userList() {
		return service.userList();
	}

	@PostConstruct
	public List<Employee> empList() {
		return service.empList();
	}

	@PostConstruct
	public List<Applicant> applicantList() {
		return service.applicantList();
	}

	public List<Orphan> orphanList() {
		return service.orphanList();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<String> getImages() {
		return images;
	}

	public void successMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, details, msg));
	}
	public void errorMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, details, msg));
	}

	public Double available() {
		orphanList1 = new ArrayList<Orphan>();
		for (Orphan or : service.orphanList()) {
			if (or.getStatus().equalsIgnoreCase("available")) {
				orphanList1.add(or);
			}
		}
		Double orpList = Double.valueOf(orphanList1.size());
		Double allOrp = Double.valueOf(service.orphanList().size());
		Double resu = (orpList * 100) / allOrp;
		return (double) Math.round(resu);
	}

	public Double adopted() {
		orphanList1 = new ArrayList<Orphan>();
		for (Orphan or : service.orphanList()) {
			if (or.getStatus().equalsIgnoreCase("adopted")) {
				orphanList1.add(or);
			}
		}
		Double orpList = Double.valueOf(orphanList1.size());
		Double allOrp = Double.valueOf(service.orphanList().size());
		Double resu = (orpList * 100) / allOrp;

		return (double) Math.round(resu);
	}

	public void updateEmp(Employee em) {
		this.employee = em;
	}

	public void updateApplicant(Applicant m) {
		this.applicant = m;
	}

	public void updateEmployee() {
		try {
		Employee em = service.findEmID(this.employee.getEmpId());
		em.setFirstName(employee.getFirstName());
		em.setLastName(employee.getLastName());
		em.setEmail(employee.getEmail());
		em.setPhoneNumber(employee.getPhoneNumber());
		service.updateEmployee(em);
		successMessage("Success", "update is well complete");
		} catch (Exception e) {
			errorMessage("error", e.getMessage());
		}
		
	}
	public void updateApp() {
		try {
		
		Applicant app = service.findID(this.applicant.getAppId());
		app.setFirstName(this.applicant.getFirstName());
		app.setLastName(this.applicant.getLastName());
		app.setEmail(this.applicant.getEmail());
		app.setPhoneNumber(this.applicant.getPhoneNumber());
		service.updateApplicant(app);
		successMessage("Success", "update is well complete");
		
		} catch (Exception e) {
			errorMessage("error", e.getMessage());
		}
	}
	
}
