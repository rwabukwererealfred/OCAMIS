package com.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.District;
import com.model.Employee;
import com.model.Province;
import com.model.User;
import com.service.CreationService;
import com.service.LoginService;

@Component
@ManagedBean
@SessionScoped
public class Password extends Validation {
	@Autowired
	private CreationService create;
	@Autowired
	private LoginService log;
	private final String sender = "fredalpha20@gmail.com";
	private final String password = "rwabukwerere18404";

	private Employee employee;
	private District district;
	private Province province;
	private Date dob = null;
	private List<District> DistrictList;
	private String code;
	private List<Province>provinceList;
	public Password() {
		provinceList = new ArrayList<Province>();
		employee = new Employee();
		province = new Province();
		district = new District();
		DistrictList = new ArrayList<District>();
		code = new String();
	}

	@PostConstruct
	public void init() {
		provinceList = create.provinceList();
	}
	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<District> getDistrictList() {
		return DistrictList;
	}

	public void setDistrictList(List<District> districtList) {
		DistrictList = districtList;
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

	public Employee getEmployee() {
		return employee;
	}

	public void select() {
		DistrictList = create.allDistrict(province.getProvinceId());
	}

	public void test() {
		code = UUID.randomUUID().toString().substring(0, 5);
		System.out.println(code);
	}

	public boolean check() {
		Employee emp = create.getOneEmp(employee.getNationalityId());
		if (emp == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean createEmployee() {
		try {
			if(validateId(employee.getNationalityId(), new SimpleDateFormat("dd/MM/yyyy").format(dob))) {
				if(validatePhone(employee.getPhoneNumber())) {
					
			code = UUID.randomUUID().toString().substring(0, 4);
			employee.setEmpId(UUID.randomUUID().toString());
			District di = create.getOne(district.getDistrictID());
			employee.setCode(code);
			employee.setDistrict(di);
			employee.setDob(new java.sql.Date(dob.getTime()).toLocalDate());
			String msg = create.createEmployee(employee);
			successMessage(msg, ".");
			di = new District();
			return true;
				}else {
					errorMessage("error", "incorect phone number");
					return false;
				}
			}else {
				errorMessage("error", "incorect date of birth and nationality id");
				return false;
			}
		} catch (Exception e) {
			errorMessage(e.getMessage(), ".");
			e.printStackTrace();
			return false;
		}

	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void code() {
		if (check()) {
			if (createEmployee()) {
				try {

					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(
							"<h1 style=font-weight: bold; color: maroon;><center>National Commission For Childrens</center></h1></center><br />");
					stringBuilder.append("Hello ,"+employee.getFirstName()+" "+employee.getLastName() + "<br />");
					stringBuilder.append("THE CODE THAT ALLOW YOU TO CREATE NEW ACCOUNT IN ONLINE CHILDREN'S ADOPTION IS :<br /><br />");
					stringBuilder.append("<b>CODE:</b>&nbsp;&nbsp;" + code + "<br />");

					stringBuilder.append("<br /><br /> Thanks");
					String emailMessage = stringBuilder.toString();

					// Assuming you are sending email through relay.jangosmtp.net
					String host = "smtp.gmail.com";

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", "587");

					// Get the Session object.
					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(sender, password);
						}
					});

					// Create a default MimeMessage object.
					Message message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(sender));

					// Set To: header field of the header.
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employee.getEmail()));

					// Set Subject: header field
					message.setSubject("Email Code");

					// Now set the actual message

					message.setContent(emailMessage, "text/html");
					// Send message
					Transport.send(message);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Codes send to your email,please check your email", "."));
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "."));
					e.printStackTrace();
				}
				district = new District();
				employee = new Employee();
				code = new String();
				province = new Province();

			}
		} else {
			errorMessage("ERROR", "Nationality ID exist");
		}
	}
	public void test(User user) {
		System.out.print("out put: "+user.getEmployee().getFirstName());
	}

	public void resetcode(Employee employee) {
		
		//User us = log.getUsername(user.getUsername());
				try {

					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(
							"<h1 style=font-weight: bold; color: maroon;><center>National Commission For Childrens</center></h1></center><br />");
					stringBuilder.append("Hello ,"+employee.getFirstName()+" "+employee.getLastName() + "<br />");
					stringBuilder.append("THE CODE THAT ALLOW YOU TO CREATE NEW ACCOUNT IN ONLINE CHILDREN'S ADOPTION IS :<br /><br />");
					stringBuilder.append("<b>CODE:</b>&nbsp;&nbsp;" + employee.getCode() + "<br />");

					stringBuilder.append("<br /><br /> Thanks");
					String emailMessage = stringBuilder.toString();

					// Assuming you are sending email through relay.jangosmtp.net
					String host = "smtp.gmail.com";

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", "587");

					// Get the Session object.
					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(sender, password);
						}
					});

					// Create a default MimeMessage object.
					Message message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(sender));

					// Set To: header field of the header.
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employee.getEmail()));

					// Set Subject: header field
					message.setSubject("Email Code");

					// Now set the actual message

					message.setContent(emailMessage, "text/html");
					// Send message
					Transport.send(message);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"success", "Codes send to your email,please check your email"));
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,"error" , e.getMessage()));
					e.printStackTrace();
				}
			
			}
		
	


	public void errorMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, details, msg));
	}

	public void successMessage(String details, String msg) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, details, msg));
	}

}
