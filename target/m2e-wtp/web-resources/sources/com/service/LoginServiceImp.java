package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApplicantDao;
import com.dao.DistrictDao;
import com.dao.DocumentDao;
import com.dao.EmployeeDao;
import com.dao.FamilyMemberDao;
import com.dao.OrphanDao;
import com.dao.RequestDao;
import com.dao.RequestForTransferDao;
import com.dao.UserDao;
import com.model.Applicant;
import com.model.District;
import com.model.Document;
import com.model.Employee;
import com.model.FamilyMember;
import com.model.Orphan;
import com.model.Request;
import com.model.RequestForTransfer;
import com.model.User;

@Service
public class LoginServiceImp extends Transaction implements LoginService {
	@Autowired
	private RequestDao requestdao;
	@Autowired
	private DocumentDao documentdao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private FamilyMemberDao familydao;
	@Autowired
	private ApplicantDao applicantdao;
	@Autowired
	private DistrictDao districtdao;
	@Autowired
	private OrphanDao orphandao;
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private RequestForTransferDao requestfortransferdao;

	@Override
	public String createRequest(Request request) {
		requestdao.save(request);
		return "";
	}

	@Override
	public String createDocument(Document document) {
		documentdao.save(document);
		return "";
	}

	@Override
	public User getUsername(String username) {

		return userdao.logged(username);
	}

	@Override
	public String createFamily(FamilyMember family) {
		familydao.save(family);
		return "";
	}

	@Override
	public Applicant getId(String id) {
		return applicantdao.findOne(id);
	}

	@Override
	public District getDistrictID(String id) {

		return districtdao.findOne(id);
	}

	@Override
	public void updateApplicant(Applicant applicant) {
		applicantdao.update(applicant);

	}

	@Override
	public Orphan findID(String id) {

		return orphandao.findOne(id);
	}

	@Override
	public void delDocument(Document document) {
		documentdao.remove(document);

	}

	@Override
	public void delFamily(FamilyMember family) {
		familydao.remove(family);
	}

	@Override
	public List<Request> findMyRequest(String employee) {

		return requestdao.findMyRequest(employee);
	}

	@Override
	public List<Request> findUserRequest(String id) {

		return requestdao.findUserReq(id);
	}

	@Override
	public List<FamilyMember> familyList(String family) {
		return familydao.MyFamily(family);
	}

	@Override
	public void update(Orphan orphan) {
		orphandao.update(orphan);
	}

	@Override
	public List<Employee> empId(String id) {
		return employeedao.findById(id);
	}

	@Override
	public Employee empID(String id) {

		return employeedao.findOne(id);
	}

	@Override
	public List<Document> myDocument(String document) {
		return documentdao.mydocumentList(document);
	}

	@Override
	public Document finddocID(String id) {

		return documentdao.findOne(id);
	}

	@Override
	public FamilyMember findFid(String id) {
		return familydao.findOne(id);
	}

	@Override
	public void updateUser(User user) {
		userdao.update(user);

	}

	@Override
	public String sendRequest(RequestForTransfer request) {
		try {
			requestfortransferdao.save(request);
			return "request has been send";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public void updateSendrequest(RequestForTransfer request) {

		requestfortransferdao.update(request);

	}

	@Override
	public RequestForTransfer findidRequest(String id) {
		return requestfortransferdao.findOne(id);
	}

	@Override
	public List<RequestForTransfer> requestforTransferList() {
		return requestfortransferdao.findAll();
	}

	

}
