package com.controller;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.validation.constraints.FutureOrPresent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Applicant;
import com.model.District;
import com.model.Document;
import com.model.Employee;
import com.model.Malaika;
import com.model.Message;
import com.model.Orphan;
import com.model.Orphanage;
import com.model.Province;
import com.model.Request;
import com.model.User;
import com.model.Usertype;
import com.service.CreationService;
import com.service.LoginService;
import com.service.ReportService;

@Component
@ManagedBean
@SessionScoped
public class Creation extends Validation {

	@Autowired
	private CreationService create;
	@Autowired
	private LoginService logService;
	@Autowired
	private ReportService report;

	private User user;
	private Applicant applicant;
	private Employee employee;
	private String confirm;
	private List<Province> ProvinceList = new ArrayList<Province>();
	private List<District> DistrictList = new ArrayList<District>();
	private String provinceID;
	private District district;
	private Province province;
	private Date dob = null;
	private Date dob1 = null;
	private String value = null;
	private Orphan orphan;
	private Orphanage orphanage;
	private Malaika malaika;
	private List<Malaika> malaikaList;
	private List<Orphanage> orphanageList;
	private List<Employee> empList;
	private Request request;
	private List<Request> requestList;
	private List<Employee> selectedEmp;
	private List<Orphan> orphanList;
	private String location;
	private boolean sel;
	private String filename;
	private List<Request> waitingRequest;
	private List<Request> allRequest;
	private Document document;
	private List<Document> documentList;
	private Message message;
	@FutureOrPresent(message = "please provide coming date")
	private Date comingDate = null;
	private String key = new String();
	private String msg = new String();
	private Malaika malaika1;
	private String orid;
	private List<Orphan> orphanList1;
	

	public Creation() {
		
		orphanList1 = new ArrayList<Orphan>();
		malaika1 = new Malaika();
		message = new Message();
		document = new Document();
		documentList = new ArrayList<Document>();
		allRequest = new ArrayList<Request>();
		waitingRequest = new ArrayList<Request>();
		orphanList = new ArrayList<Orphan>();
		filename = new String();
		malaikaList = new ArrayList<Malaika>();
		orphanageList = new ArrayList<Orphanage>();
		malaika = new Malaika();
		orphanage = new Orphanage();
		orphan = new Orphan();
		user = new User();
		applicant = new Applicant();
		employee = new Employee();
		request = new Request();
		confirm = new String();
		provinceID = new String();
		province = new Province();
		empList = new ArrayList<Employee>();
		district = new District();
		selectedEmp = new ArrayList<Employee>();
		requestList = new ArrayList<Request>();
		location = new String();
	}

	

	public List<Orphan> getOrphanList1() {
		return orphanList1;
	}

	public void setOrphanList1(List<Orphan> orphanList1) {
		this.orphanList1 = orphanList1;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Malaika getMalaika1() {
		return malaika1;
	}

	public void setMalaika1(Malaika malaika1) {
		this.malaika1 = malaika1;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Date getComingDate() {
		return comingDate;
	}

	public void setComingDate(Date comingDate) {
		this.comingDate = comingDate;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	public List<Request> getWaitingRequest() {
		return waitingRequest;
	}

	public void setWaitingRequest(List<Request> waitingRequest) {
		this.waitingRequest = waitingRequest;
	}

	public List<Request> getAllRequest() {
		return allRequest;
	}

	public void setAllRequest(List<Request> allRequest) {
		this.allRequest = allRequest;
	}

	public List<Orphan> getOrphanList() {
		return orphanList;
	}

	public void setOrphanList(List<Orphan> orphanList) {
		this.orphanList = orphanList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isSel() {
		return sel;
	}

	public void setSel(boolean sel) {
		this.sel = sel;
	}

	public List<Employee> getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedEmp(List<Employee> selectedEmp) {
		this.selectedEmp = selectedEmp;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public Orphanage getOrphanage() {
		return orphanage;
	}

	public void setOrphanage(Orphanage orphanage) {
		this.orphanage = orphanage;
	}

	public Malaika getMalaika() {
		return malaika;
	}

	public void setMalaika(Malaika malaika) {
		this.malaika = malaika;
	}

	public List<Malaika> getMalaikaList() {
		return malaikaList;
	}

	public void setMalaikaList(List<Malaika> malaikaList) {
		this.malaikaList = malaikaList;
	}

	public List<Orphanage> getOrphanageList() {
		return orphanageList;
	}

	public void setOrphanageList(List<Orphanage> orphanageList) {
		this.orphanageList = orphanageList;
	}

	public Orphan getOrphan() {
		return orphan;
	}

	public void setOrphan(Orphan orphan) {
		this.orphan = orphan;
	}

	public Date getDob1() {
		return dob1;
	}

	public void setDob1(Date dob1) {
		this.dob1 = dob1;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public CreationService getCreate() {
		return create;
	}

	public void setCreate(CreationService create) {
		this.create = create;
	}

	public List<Province> getProvinceList() {
		return ProvinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		ProvinceList = provinceList;
	}

	public List<District> getDistrictList() {
		return DistrictList;
	}

	public void setDistrictList(List<District> districtList) {
		DistrictList = districtList;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void select1() {
		value = "alpha";
	}

	public void listOrphan(Orphan orphan) {
		this.orphan = orphan;

	}

	public void updateOrphan() {

		try {
			String msg = report.updateOrphan(orphan);
			successMessage("success", msg);
			orphanList = report.allOrphan();
			System.out.println("the date");
		} catch (Exception e) {
			// String msg = report.updateOrphan(o);
			e.getStackTrace();
			errorMessage("error", e.getMessage());
		}
		orphan = new Orphan();
	}

	public void signup() {
		User us = logService.getUsername(user.getUsername());
		if (validatePhone(applicant.getPhoneNumber())) {
			if (us == null) {
				if (user.getPassword().matches(confirm)) {
					user.setUserID(UUID.randomUUID().toString());
					user.setPassword(md5(user.getPassword()));
					user.setAccess(Usertype.Applicant.toString());
					user.setState(true);
					applicant.setAppId(UUID.randomUUID().toString());
					user.setApplicant(applicant);
					String msg = create.createApplicant(applicant) + create.createUser(user);
					successMessage("Success", msg);
				} else {
					errorMessage("error", "password does not matches");
				}
			} else {
				errorMessage("error", "username arlead in used");
			}
		} else {
			errorMessage("error", "Incorect phone number");
		}
		user = new User();
		applicant = new Applicant();

	}

	public void selectEmploye(Employee employee) {
		this.employee = employee;
	}

	public void selectRequest(Request request) throws IOException {
		this.request = request;
		FacesContext.getCurrentInstance().getExternalContext().redirect("EmployeList.jsf");
	}

	public void selectReq(Request request) throws IOException {
		this.request = request;
		System.out.println(this.request.getReqId());
		List<Document> list = create.findDocument(this.request.getReqId());
		for (Document d : list) {
			documentList.add(d);
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("DocumentList.jsf");
	}

	public void selectOrphan(Orphan orphan) throws IOException {
		this.orphan = orphan;
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanDetails.jsf");
	}

//	@PostConstruct
//	public void init1() {
//		for (Employee em : create.empList()) {
//			empList.add(em);
//		}
//	}

	@PostConstruct
	public void init2() {
		for (Request r : create.allRequest()) {
			requestList.add(r);
		}
	}

	@PostConstruct
	public void init3() {
		for (Orphanage or : create.orphanageList()) {
			orphanageList.add(or);
		}
	}

//	@PostConstruct
//	public void init4() {
//		for (Malaika m : create.malaikaList()) {
//			malaikaList.add(m);
//		}
//	}

	@PostConstruct
	public void init5() throws ParseException {
		for (Orphan o : create.orphanList()) {

			orphanList.add(o);
		}
	}

//	@PostConstruct
//	public void init6() {
//		for (Request r : create.findWaiting()) {
//			waitingRequest.add(r);
//		}
//	}

	
	public void allrequest() throws IOException {
		allRequest = new ArrayList<Request>();
		for (Request rr : create.findAllRequest()) {
			allRequest.add(rr);
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("AllRequest.jsf");
	}

	public void employeSignup() {
		try {
			User us = logService.getUsername(user.getUsername());
			if (us == null) {
				if (user.getPassword().matches(confirm)) {
					Employee emp = create.findCode(employee.getCode());
					if (emp != null) {
						if (checkUser(employee.getCode())) {
							user.setUserID(UUID.randomUUID().toString());
							user.setPassword(md5(user.getPassword()));
							user.setAccess(Usertype.Employee.toString());
							user.setState(true);
							user.setEmployee(emp);
							String msg = create.createUser(user);
							successMessage(msg, ".");
						} else {
							errorMessage("Error", "Sorry sir you can't create two more account");
						}
					} else {
						errorMessage("error", "You are not allowed to create account as an employee");
					}
				} else {
					errorMessage("error", "password does not matches");
				}
			} else {
				errorMessage("error", "username is arleady in used, please try another username");
			}
		} catch (Exception e) {
			errorMessage("error", e.getMessage());
			e.printStackTrace();
		}
		user = new User();
		employee = new Employee();
	}

	@PostConstruct
	public void init() {
		for (Province p : create.provinceList()) {
			ProvinceList.add(p);
		}
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

	public void chooseone(Orphan orphan) throws IOException {
		this.orphan = orphan;
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanDetails.jsf");

	}

	public void viewProfile(Orphan orphan) throws IOException {
		this.orphan = orphan;
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanDetail.jsf");
	}

	public void assign() {
		Employee em = create.findID(employee.getEmpId());
		Request r = create.findRequest(request.getReqId());
		r.setEmployee(em);
		r.setStatus("Appending");
		create.update(r);
		successMessage("The request is well assigned well to:" + em.getFirstName(), ".");
		em = new Employee();
		r = new Request();
	}

	public void show() {
		System.out.println("out put:" + employee.getFirstName());
	}

	public void select() {
		DistrictList = create.allDistrict(provinceID);
	}

	public void selectorphanage(Orphanage orphanage) {
		this.orphanage = orphanage;
	}

	public void selectMalaika(Malaika malaika) {
		this.malaika = malaika;
	}

	public void orphanPage() throws IOException {
		orphan = new Orphan();
		dob = null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("Orphan.jsf");
	}

	public void orphanPage1() throws IOException {
		orphanList = create.orphanList();
		orphan = new Orphan();
		dob = null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanUser.jsf");
	}

	public void orphanPage2(Request request) throws IOException {
		this.request = request;
		orphan = request.getOrphan();
		FacesContext.getCurrentInstance().getExternalContext().redirect("OrphanDetail.jsf");
	}

	public void addNewOrphan() {
		int age = age(new java.sql.Date(dob.getTime()).toLocalDate());
		if (age <= 18) {
			if (location.equals("Malaika")) {
				Malaika ma = create.findMalaika(malaika.getmID());
				orphan.setOrphanId(UUID.randomUUID().toString());
				orphan.setDob(dob);
				orphan.setFile(filename);
				orphan.setStatus("Available");
				orphan.setMalaika(ma);
				create.addOrphan(orphan);
			} else if (location.equals("Orphanage")) {

				Orphanage orph = create.findOrphage(orphanage.getOrphanageId());
				orphan.setStatus("Available");
				orphan.setDob(dob);
				orphan.setFile(filename);
				orphan.setOrphanId(UUID.randomUUID().toString());
				orphan.setOrphanage(orph);
				create.addOrphan(orphan);

			} else {
				errorMessage("error: ", "Please choose where Orphan he/she is registered");
			}
			successMessage("Success", "new Orphan is well added");
			orphanList = create.orphanList();
			orphan = new Orphan();
			malaika = new Malaika();
			orphanage = new Orphanage();
		} else {
			errorMessage("error", "orphan age is greater than 18");
		}

	}

	public void addOrphanage() {
		District d = create.getOne(district.getDistrictID());
		orphanage.setOrphanageId(UUID.randomUUID().toString());
		orphanage.setDistrict(d);
		orphanage.setDirector(null);
		orphanage.setLastName(null);
		orphanage.setPhoneNumber(null);
		orphanage.setEmail(null);
		create.addOrphanage(orphanage);
		successMessage("new Orphanage is well created", ".");
		orphanageList = create.orphanageList();
		orphanage = new Orphanage();
		district = new District();
		d = new District();
	}

	public void test() {
		System.out.println("out put " + malaika1.getFirstName() + " " + malaika1.getNationalityId());
	}

	public void malnew() {
		malaika = new Malaika();
		orphanage = new Orphanage();
		dob = null;
	}

	public void addMalaika() {
		// String l[] = malaika.getDob().toString().split("/");
		try {
			if (validateId(malaika1.getNationalityId(), new SimpleDateFormat("dd/MM/yyyy").format(dob))) {
				District d = create.getOne(district.getDistrictID());
				malaika1.setmID(UUID.randomUUID().toString());
				malaika1.setDob(new java.sql.Date(dob.getTime()).toLocalDate());
				malaika1.setDistrict(d);
				create.addMalaika(malaika1);
				successMessage("Success", "Guardian Angel is well added");
				dob = null;
				malaika1 = new Malaika();
				district = new District();
				malaikaList = create.malaikaList();
			} else {
				System.out.println("out put:" + malaika1.getNationalityId() + new SimpleDateFormat().format(dob));
				errorMessage("error", "Your nationality number does not matches with your nationality id");
			}
		} catch (Exception e) {
			errorMessage("error", e.getMessage());
		}
	}

	public void addDirector() {
		if (validatePhone(orphanage.getPhoneNumber())) {
			Orphanage or = create.findOrphage(orphanage.getOrphanageId());
			or.setDirector(orphanage.getDirector());
			or.setLastName(orphanage.getLastName());
			or.setPhoneNumber(orphanage.getPhoneNumber());
			or.setEmail(orphanage.getEmail());
			create.updateOrphanage(or);
			successMessage("Success", "Director is added");
			orphanageList = create.orphanageList();
			orphanage = new Orphanage();
			or = new Orphanage();
		} else {
			
			errorMessage("error", "phone number is not correct");
		}
		orphanageList = create.orphanageList();

	}

	public void uploadProfile(FileUploadEvent eventUpload) {
		try {
			UploadedFile upload = eventUpload.getFile();
			orphan.setFilename(upload.getFileName());
			orphan.setFiletype(upload.getContentType());
			byte[] contents = upload.getContents();
			orphan.setProfilePic(contents);
		} catch (Exception e) {
			e.printStackTrace();
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

	public void applicantSignup() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("ApplicantSign.jsf");
	}

	public void employeeSignup() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("Simple.jsf");
	}

	public void uploadFile(FileUploadEvent eventUpload) throws IOException, Exception {
		FacesMessage msg = new FacesMessage("Success! ", eventUpload.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// System.out.println("Okay well done");
		try {
			copyFile(eventUpload.getFile().getFileName(), eventUpload.getFile().getInputstream());
			successMessage("success", "file is well uplaoded");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyFile(final String fileName,final InputStream in) {
		
		//ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		// String destination = ec.getRealPath(String.format("/resources/File","/"));
		// System.out.println(destination);
		try {

			String destination = "E:\\alfred\\eclipse\\work\\OCAMIS\\src\\main\\webapp\\resources\\File\\";
			OutputStream out = new FileOutputStream(new File(destination+ fileName));
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

	public void readFile(Orphan orphan) {
		this.orphan = orphan;
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("E:\\alfred\\eclipse\\work\\OCAMIS\\src\\main\\webapp\\resources\\File\\"
						+orphan.getFile());
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				errorMessage(ex.getMessage(), ".");
			}
		}
	}

	public void waitingPage() throws IOException {
		waitingRequest = create.findWaiting();
		FacesContext.getCurrentInstance().getExternalContext().redirect("WaitingRequest.jsf");
	}

	public void approveByadmin() {
		Request r = create.findRequest(request.getReqId());
		String d = new SimpleDateFormat("dd/MMM/yyyy").format(comingDate);
		if (r.getStatus().equalsIgnoreCase("Waiting")) {
			Orphan o = logService.findID(r.getOrphan().getOrphanId());
			o.setStatus("Adopted");
			logService.update(o);
			r.setStatus("Approved");
			r.setMessage(message.getApproveMessage() + r.getOrphan().getFirstName() + " " + r.getOrphan().getLastName()
					+ " " + message.getApproveMessage1() + " " + d);
			create.update(r);
			successMessage("Success", "Request is well Accepted");
			waitingRequest = create.findWaiting();
		}

	}

	public void rejectByadmin() {
		Request r = create.findRequest(request.getReqId());
		if (r.getStatus().equalsIgnoreCase("Waiting")) {
			r.setStatus("rejected");
			r.setMessage(msg);
			create.update(r);
			successMessage("Success", "request is rejected");
			waitingRequest = create.findWaiting();
		}

	}

	public void selectRequest1(Request request) {
		this.request = request;
	}

	public void searchOrphanage() {
		List<Orphanage> list = new ArrayList<Orphanage>();
		for (Orphanage o : create.orphanageList()) {
			if (key.contains(o.getName()) || key.contains(o.getDistrict().getProvince().getName())) {
				list.add(o);
			} else if (key.contains("")) {
				list.add(o);
			}
		}
		orphanageList = list;
	}

	public void orphList() {
		orphanageList = create.orphanageList();
	}

	public void update() {
		Malaika m = create.findMalaika(malaika.getmID());
		m = malaika;
		create.updateMalaika(m);
		successMessage("update is well completed", ".");
	}

	public void emp() throws IOException {
		empList = create.empList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("EmployeeList.jsf");
	}

	public void orphanageListAdmin() throws IOException {
		orphanageList = create.orphanageList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Orphanageadmin.jsf");
	}

	public void guardianListAdmin() throws IOException {
		//orphanageList = create.orphanageList();
		malaikaList = create.malaikaList();
		orphanList1 = create.orphanList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("MalaikaAdmin.jsf");
	}
	public void orphanageListemp() throws IOException {
		orphanageList = create.orphanageList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Orphanage.jsf");
	}

	public void guardianListemp() throws IOException {
		//orphanageList = create.orphanageList();
		malaikaList = create.malaikaList();
		orphanList1 = create.orphanList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("MalaikaMurinzi.jsf");
	}
	public void searchByDistrict() {
		List<Malaika> list = new ArrayList<Malaika>();
		for (Malaika e : create.malaikaList()) {
			if (e.getDistrict().getDistrictID().equalsIgnoreCase(district.getDistrictID())) {
				list.add(e);
			} else if (district.getDistrictID().equals("")) {
				list.add(e);
			}
		}
		malaikaList = list;
	}
	
	public List<District>allDistrict(){
		List<District>list = new ArrayList<District>();
		for(District d: create.allDistrict()) {
			list.add(d);
		}
		return list;
		
	}
	public void searchByDistrict1() {
		List<Orphanage> list = new ArrayList<Orphanage>();
		for (Orphanage e : create.orphanageList()) {
			if (e.getDistrict().getDistrictID().equalsIgnoreCase(district.getDistrictID())) {
				list.add(e);
			} else if (district.getDistrictID().equals("")) {
				list.add(e);
			}
		}
		orphanageList = list;
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
