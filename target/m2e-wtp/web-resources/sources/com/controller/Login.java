package com.controller;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Applicant;
import com.model.District;
import com.model.Document;
import com.model.Employee;
import com.model.FamilyMember;
import com.model.Message;
import com.model.Orphan;
import com.model.Province;
import com.model.Request;
import com.model.User;
import com.service.CreationService;
import com.service.LoginService;

@Component
@ManagedBean
@SessionScoped
public class Login extends Validation {

	@Autowired
	private CreationService create;

	@Autowired
	private LoginService logService;

	private Applicant applicant;
	private Document document;
	private Request request;
	private District district;
	private Province province;
	private String provinceID = new String();
	private List<Province> provinceList = new ArrayList<Province>();
	private List<District> districtList = new ArrayList<District>();
	private String filename = new String();
	private List<Document> ducList = new ArrayList<Document>();
	private User user;
	private FamilyMember family;
	private List<FamilyMember> familyList = new ArrayList<FamilyMember>();
	private Date dob = null;
	private Date dob1 = null;
	private List<Orphan> orphanList;
	private Orphan orphan;
	private Employee employee;
	private List<Request> requestList;
	private List<Document> documentList;
	private List<Employee> empList;
	private List<Request> findMyRequest;
	private Message message;
	private Date comingDate;
	private List<Request> userRequest;
	private String msg;
	private String relationship = new String();
	private String date = new String();
	private String validate = new String();

	public Login() {
		familyList = new ArrayList<FamilyMember>();
		message = new Message();
		userRequest = new ArrayList<Request>();
		requestList = new ArrayList<Request>();
		empList = new ArrayList<Employee>();
		documentList = new ArrayList<Document>();
		employee = new Employee();
		applicant = new Applicant();
		document = new Document();
		request = new Request();
		user = new User();
		family = new FamilyMember();
		district = new District();
		orphanList = new ArrayList<Orphan>();
		province = new Province();
		orphan = new Orphan();
		findMyRequest = new ArrayList<Request>();
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Request> getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(List<Request> userRequest) {
		this.userRequest = userRequest;
	}

	public Date getComingDate() {
		return comingDate;
	}

	public void setComingDate(Date comingDate) {
		this.comingDate = comingDate;
	}

	public List<Request> getFindMyRequest() {
		return findMyRequest;
	}

	public void setFindMyRequest(List<Request> findMyRequest) {
		this.findMyRequest = findMyRequest;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Orphan getOrphan() {
		return orphan;
	}

	public void setOrphan(Orphan orphan) {
		this.orphan = orphan;
	}

	public List<Orphan> getOrphanList() {
		return orphanList;
	}

	public void setOrphanList(List<Orphan> orphanList) {
		this.orphanList = orphanList;
	}

	public Date getDob1() {
		return dob1;
	}

	public void setDob1(Date dob1) {
		this.dob1 = dob1;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<FamilyMember> getFamilyList() {
		return familyList;
	}

	public void setFamilyList(List<FamilyMember> familyList) {
		this.familyList = familyList;
	}

	public FamilyMember getFamily() {
		return family;
	}

	public void setFamily(FamilyMember family) {
		this.family = family;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Document> getDucList() {
		return ducList;
	}

	public void setDucList(List<Document> ducList) {
		this.ducList = ducList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public List<District> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<District> districtList) {
		this.districtList = districtList;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void select() {
		districtList = create.allDistrict(province.getProvinceId());
	}

	public void selectRequest1(Request request) {
		this.request = request;
	}

	@PostConstruct
	public void init() {
		for (Province d : create.provinceList()) {
			provinceList.add(d);
		}
	}

	@PostConstruct
	public void init5() {
		for (Orphan o : create.orphanList()) {
			orphanList.add(o);
		}
	}

	@PostConstruct
	public void init4() {
		userRequest = new ArrayList<Request>();
		for (Request r : logService.findUserRequest(applicant.getAppId())) {
			userRequest.add(r);
		}
	}

	@PostConstruct
	public void init3() {
		for (Request r : logService.findMyRequest(employee.getEmpId())) {
			findMyRequest.add(r);
		}
	}

	public void add() {
		Document d = new Document();
		// d.setDocId(UUID.randomUUID().toString());
		d.setDocumentType(document.getDocumentType());
		d.setImage(filename);
		ducList.add(d);
		document = new Document();
		filename = new String();
	}

	@PostConstruct
	public void init2() {
		for (Request r : create.allRequest()) {
			requestList.add(r);
		}
	}

	public void addFamily() {
		FamilyMember f = new FamilyMember();
		f = family;
		f.setFamilyId(UUID.randomUUID().toString());
		f.setDob(new java.sql.Date(dob1.getTime()).toLocalDate());
		if (family.getRelationship().equalsIgnoreCase("Other")) {
			f.setRelationship(relationship);
		} else {
			f.setRelationship(family.getRelationship());

		}
		familyList.add(f);
		family = new FamilyMember();
		dob1 = null;
	}

	public boolean updateApplicant() {
		Applicant app = logService.getId(user.getApplicant().getAppId());
		District di = logService.getDistrictID(district.getDistrictID());
		app.setNationalityId(applicant.getNationalityId());
		app.setIncome(applicant.getIncome());
		app.setMartialStatus(applicant.getMartialStatus());
		app.setMotivation(applicant.getMotivation());
		app.setDob(new java.sql.Date(dob.getTime()).toLocalDate());
		app.setDistrict(di);
		logService.updateApplicant(app);
		return true;
	}

	public Date retransform(LocalDateTime ldt) {
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	public LocalDateTime transfer(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	}

	public boolean createRequest() {
		if (familyList.isEmpty() == false) {
			if (familyList.isEmpty() == false) {
				request.setReqId(UUID.randomUUID().toString());
				request.setMessage(message.getApplyingMessage());
				request.setStatus("Applying");
				request.setEmployee(null);
				request.setOrphan(null);
				request.setStartDate(new Date());
				request.setApplicant(applicant);
				logService.createRequest(request);
				return true;
			} else {
				return false;
			}
		} else {

			return false;
		}
	}

	public boolean createFamily() {
		if (familyList.isEmpty() == false) {
			for (FamilyMember f : familyList) {
				//
				// FamilyMember fami = logService.findFid(f.getFamilyId());
				// if (fami == null) {
				f.setFamilyId(UUID.randomUUID().toString());
				f.setApplicant(applicant);
				logService.createFamily(f);

			}
		} else {
			
			return false;
		}
		return true;
	}

	public boolean createDocument() {
		
			for (Document dd : ducList) {
				document = dd;
				document.setDocId(UUID.randomUUID().toString());
				document.setRequest(request);
				logService.createDocument(document);

			}
		

		return true;
	}

	public void test() {
		int date = age(new java.sql.Date(dob.getTime()).toLocalDate());
		System.out.print(date);
	}

	public void validateDocument() {
		validate = new String();
		if (applicant.getMartialStatus().equalsIgnoreCase("Single")) {
			validate = "Single";

		} else if (applicant.getMartialStatus().equalsIgnoreCase("Married")) {
			validate = "Married";
		} else if (applicant.getMartialStatus().equalsIgnoreCase("Divorced")) {
			validate = "Divorced";
		} else if (applicant.getMartialStatus().equalsIgnoreCase("Widow")) {
			validate = "Widow";
		}
	}

	public void apply() {

		try {
			if (familyList.isEmpty() == false || ducList.isEmpty() == false) {
			int date = age(new java.sql.Date(dob.getTime()).toLocalDate());
			if (date >= 35) {
				if (validateId(applicant.getNationalityId(), new SimpleDateFormat("dd/MM/yyyy").format(dob))) {
					updateApplicant();
					createRequest();
					createDocument();
					createFamily();
					successMessage("Success", "you applicant is well sending");
					requestList = create.allRequest();
					family = new FamilyMember();
					document = new Document();
					request = new Request();
				} else {
					errorMessage("error", "Incorect birthdate and nationality id");
				}
			} else {
				errorMessage("error", "Sorry sir, you age is bellow than needs for adoption");
			}
			}else {
				errorMessage("error", "empty list");
			}
		} catch (Exception e) {
			errorMessage("error", e.getMessage());
		}
	}

	public void uploadFile(FileUploadEvent eventUpload) throws IOException, Exception {
		FacesMessage msg = new FacesMessage("Success! ", eventUpload.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// System.out.println("Okay well done");
		try {
			copyFile(eventUpload.getFile().getFileName(), eventUpload.getFile().getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {

			String destination = "E:\\alfred\\eclipse\\work\\OCAMIS\\src\\main\\webapp\\resources\\File\\";
			OutputStream out = new FileOutputStream(new File(destination + fileName));
			filename = fileName;
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deletedoc() {
		ducList = new ArrayList<Document>();
	}

	public void deletefami() {
		familyList = new ArrayList<FamilyMember>();

	}

	public void readFile(Orphan orphan) {
		this.orphan = orphan;
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(
						"E:\\alfred\\eclipse\\work\\OCAMIS\\src\\main\\webapp\\resources\\File\\" + this.orphan.getFile());
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				ex.getMessage();
			}
		}
	}

	public void readdoc(Document document) {
		this.document = document;
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(
						"E:\\alfred\\eclipse\\work\\OCAMIS\\src\\main\\webapp\\resources\\File\\" + this.document.getImage());
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				ex.getMessage();
			}
		}
	}

	public void successMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, details, msg));
	}

	public void errorMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, details, msg));
	}

	public void warnMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, details, msg));
	}

	public boolean checkDocument() {
		// List<Document>list = new ArrayList<Document>();
		List<String> list1 = new ArrayList<String>();
		ducList = new ArrayList<Document>();
		for (Document d : logService.myDocument(applicant.getAppId())) {
			if (!list1.contains(d.getDocumentType())) {
				ducList.add(d);
				list1.add(d.getDocumentType());
			}

		}
		return true;
	}

	public String log() {
		HttpSession session = Util.getSession();
		if (!user.getUsername().equalsIgnoreCase("admin")) {
			User us = logService.getUsername(user.getUsername());
			if (us != null) {
				if (us.isState()) {
					String pass = md55(user.getPassword());
					if (us.getPassword().matches(pass)) {
						if (us.getApplicant() != null) {

							user = us;
							session.setAttribute("username", user.getAccess());
							applicant = us.getApplicant();
							if (applicant.getDob() != null) {
								dob = asDate(applicant.getDob());
							}
							if (applicant.getDistrict() != null) {
								district = applicant.getDistrict();
								province = applicant.getDistrict().getProvince();
							}
							checkDocument();
							// ducList = logService.myDocument(applicant.getAppId());
							familyList = logService.familyList(applicant.getAppId());
							userRequest = logService.findUserRequest(applicant.getAppId());
							// FacesContext.getCurrentInstance().getExternalContext().redirect("ApplicantApply.xhtml");
							return "ApplicantApply.jsf?faces-redirect=true";

						} else if (us.getEmployee() != null) {
							user = us;
							session.setAttribute("username", user.getAccess());
							employee = us.getEmployee();
							orphanList = create.orphanList();
							findMyRequest = logService.findMyRequest(employee.getEmpId());
							return "Orphanage.jsf?faces-redirect=true";
						} else {
							errorMessage("error", "You are not allowed to log in! please sign up");
							return "login";
						}
					}
					errorMessage("error", "invalid username and password");
					return "login";
				} else {
					errorMessage("error", "your account is blocked,please call administrator");
					return "login";
				}
			} else {
				errorMessage("error", "username and password does not exist");
				return "login";
			}
		} else {
			session.setAttribute("username", user.getUsername());
			requestList = create.allRequest();
			return "Dashboard.jsf?faces-redirect=true";
		}

	}

	public void selectReq(Request request) throws IOException {
		this.request = request;
		System.out.println(this.request.getReqId());
		documentList = new ArrayList<Document>();
		List<Document> list = create.findDocument(this.request.getReqId());
		for (Document d : list) {
			documentList.add(d);
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("DocumentList.jsf");
	}

	public void selectReq1(Request request) throws IOException {
		this.request = request;
		System.out.println(this.request.getReqId());
		documentList = new ArrayList<Document>();
		List<Document> list = create.findDocument(this.request.getReqId());
		for (Document d : list) {
			documentList.add(d);
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("DocumentListemp.jsf");
	}

	public void signout() throws IOException {
		user = new User();
		province = new Province();
		district = new District();
		applicant = new Applicant();
		employee = new Employee();
		familyList = new ArrayList<FamilyMember>();
		ducList = new ArrayList<Document>();
		HttpSession session = Util.getSession();
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
	}

	public void selectRequest(Request request) throws IOException {
		empList = create.empList();
		this.request = request;
		FacesContext.getCurrentInstance().getExternalContext().redirect("EmployeList.jsf");
	}

	public void chooseOrphan(Request request) throws IOException {
		this.request = request;
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanUser.jsf");
	}

	public void assign() {
		Employee em = create.findID(employee.getEmpId());
		Request r = create.findRequest(request.getReqId());
		r.setEmployee(em);
		r.setStatus("Appending");
		create.update(r);
		successMessage("The request is well assigned well to:" + em.getFirstName(), ".");
		requestList = create.allRequest();
		em = new Employee();
		r = new Request();
	}

	@PostConstruct
	public void init1() {
		for (Employee em : create.empList()) {
			empList.add(em);
		}
	}

	public void approve() {
		Request r = create.findRequest(request.getReqId());
		if (r.getStatus().equalsIgnoreCase("Appending")) {
			r.setMessage(message.getNextStepMessage());
			r.setStatus("Select Child");
			create.update(r);
			successMessage("Request is well Accepted", ".");
			findMyRequest = logService.findMyRequest(employee.getEmpId());
		} else if (r.getStatus().equalsIgnoreCase("Proceed")) {
			r.setStatus("Waiting");
			create.update(r);
			successMessage("Request is well Accepted", ".");
			findMyRequest = logService.findMyRequest(employee.getEmpId());
		}
	}

	public void reject() {
		Request rr = create.findRequest(request.getReqId());
		if (rr.getStatus().equalsIgnoreCase("Appending")) {
			rr.setStatus("rejected");
			rr.setMessage(msg);
			create.update(rr);
			warnMessage("success", "Request is rejected");
			findMyRequest = logService.findMyRequest(employee.getEmpId());
		}
		if (rr.getStatus().equalsIgnoreCase("Proceed")) {
			rr.setMessage(msg);
			rr.setStatus("rejected");
			create.update(rr);
			warnMessage("success", "Request is rejected");
			findMyRequest = logService.findMyRequest(employee.getEmpId());
		}
	}

	public void myRequest() throws IOException {
		userRequest = logService.findUserRequest(applicant.getAppId());
		FacesContext.getCurrentInstance().getExternalContext().redirect("MyRequest.jsf");
	}

	public void choosenOrphan(Request request) throws IOException {
		this.request = request;
		FacesContext.getCurrentInstance().getExternalContext().redirect("ChoosenOrphan.jsf");
	}

	public void chooseOrphan1(Orphan orphan) {
		this.orphan = orphan;
		Request r = create.findRequest(request.getReqId());
		Orphan o = logService.findID(this.orphan.getOrphanId());
		r.setMessage("mr/madam" + r.getApplicant().getFirstName()
				+ "thank you for the next step you apply for.you will get confirmation message quick");
		r.setStatus("Proceed");
		r.setOrphan(o);
		create.update(r);
		successMessage("Success", "Your request is sent! thank for the request");
		userRequest = logService.findUserRequest(applicant.getAppId());
	}

	public StreamedContent getProfilePic() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String image = context.getExternalContext().getRequestParameterMap().get("image");
			Orphan user = logService.findID(image);
			return new DefaultStreamedContent((new ByteArrayInputStream(user.getProfilePic())));
		}

	}

	public void getFamily(Request request) throws IOException {
		this.request = request;
		familyList = new ArrayList<FamilyMember>();
		for (FamilyMember m : logService.familyList(request.getApplicant().getAppId())) {
			familyList.add(m);
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("FamilyMember.jsf");
	}

	public void searchByProvince() {
		List<Employee> list = new ArrayList<Employee>();
		for (Employee e : create.empList()) {
			if (e.getDistrict().getProvince().getProvinceId().equalsIgnoreCase(province.getProvinceId())) {
				list.add(e);
			} else if (province.getProvinceId().equalsIgnoreCase("")) {
				list.add(e);
			}

		}
		empList = list;
	}

	public void refresh() {
		empList = create.empList();
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public String md55(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte bytData[] = md.digest();
			StringBuffer hextString = new StringBuffer();
			for (int i = 0; i < bytData.length; i++) {
				String hex = Integer.toHexString(0xff & bytData[i]);
				if (hex.length() == 1) {
					hextString.append('0');
				}
				hextString.append(hex);
			}
			return hextString.toString();

		} catch (Exception e) {
			return "Error" + e.getMessage();
		}

	}

}
