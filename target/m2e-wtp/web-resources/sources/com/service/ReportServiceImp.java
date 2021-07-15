package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrphanDao;
import com.dao.ProvinceDao;
import com.dao.RequestDao;
import com.dao.UserDao;
import com.model.Orphan;
import com.model.Province;
import com.model.Request;
import com.model.User;
import com.util.DataManipulationException;

@Service
public class ReportServiceImp extends Transaction implements ReportService {

	@Autowired
	private RequestDao requestdao;
	@Autowired
	private OrphanDao orphandao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private ProvinceDao provincedao;
	
	@Override
	public List<Request> selectByStatus(String status) {
		return requestdao.findByStatus(status);
	}

	@Override
	public List<Request> allRequest() {
		
		return requestdao.findAll();
	}

	@Override
	public List<Orphan> allOrphan() {
		
		return orphandao.allorphanList();
	}

	@Override
	public List<Orphan> SelectByStatus(String status) {
		
		return orphandao.selectByStatus(status);
	}

	@Override
	public List<User> userList() {
		return userdao.userList();
	}

	@Override
	public List<Orphan> orphanList() {
		return orphandao.allorphanList();
	}

	@Override
	public String updateOrphan(Orphan orphan) {
		try {	
		orphandao.update(orphan);
		return "orphan is well updated";
		} catch (Exception e) {
			e.getStackTrace();
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public Orphan findID(String id) {
		return orphandao.findOne(id);
	}

	@Override
	public List<Orphan> findBydob( Date startDate, Date endDate) {
		return orphandao.findRange(startDate, endDate);
	}

	@Override
	public Province getProvince(String id) {
		
		return provincedao.findOne(id);
	}

	

}
