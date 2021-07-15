package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Employee;

@Repository
public class EmployeeDao extends GenericDaoImpl<Employee> {

	public Employee getOneem(String nationalityId) {
		Query q = sessionfactory().createQuery("from Employee where nationalityId =:nationalityId");
		q.setString("nationalityId", nationalityId);
		return(Employee) q.uniqueResult();
	}
	
	public Employee getcode(String code) {
		Query q = sessionfactory().createQuery("from Employee where code =:code");
		q.setString("code", code);
		return(Employee) q.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	public List<Employee>findAllEmp(){
		Query q = sessionfactory().createQuery("from Employee");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee>findById(String province){
		Query q = sessionfactory().createQuery("From Employee where district.province.provinceId =:province");
		q.setString("province", province);
		return q.list();
		
	}


	@Override
	public void remove(Employee t) {
		sessionfactory().delete(t);
		
	}
}
