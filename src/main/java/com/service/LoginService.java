package com.service;

import java.util.List;

import com.model.Applicant;
import com.model.District;
import com.model.Document;
import com.model.Employee;
import com.model.FamilyMember;
import com.model.Orphan;
import com.model.Request;
import com.model.RequestForTransfer;
import com.model.User;

public interface LoginService {

	String createRequest(Request request);
	String createDocument(Document document);
	User getUsername(String username);
	String createFamily(FamilyMember family);
	Applicant getId(String id);
	District getDistrictID(String id);
	void updateApplicant(Applicant applicant);
	Orphan findID(String id);
	void delDocument(Document document);
    void delFamily(FamilyMember family);
    List<Request>findMyRequest(String employee);
    List<Request> findUserRequest(String id);
    List<FamilyMember>familyList(String family);
    void update(Orphan orphan);
    List<Employee>empId(String id);
    Employee empID (String id);
    List<Document>myDocument(String document);
    Document finddocID(String id);
    FamilyMember findFid(String id);
    void updateUser(User user);
    String sendRequest(RequestForTransfer request);
    void updateSendrequest(RequestForTransfer request);
    RequestForTransfer findidRequest(String id);
    List<RequestForTransfer>requestforTransferList();
   
    
}
