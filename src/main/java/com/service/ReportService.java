package com.service;

import java.util.Date;
import java.util.List;

import com.model.Orphan;
import com.model.Province;
import com.model.Request;
import com.model.User;

public interface ReportService {

	List<Request>selectByStatus(String status);
	List<Request>allRequest();
	List<Orphan>allOrphan();
	List<Orphan>SelectByStatus(String status);
	List<User>userList();
	List<Orphan>orphanList();
	String updateOrphan(Orphan orphan);
	Orphan findID(String id);
	List<Orphan>findBydob(Date startDate,Date endDate);
	Province getProvince(String id);
}
