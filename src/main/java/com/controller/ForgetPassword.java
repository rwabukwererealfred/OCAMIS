package com.controller;

import java.security.MessageDigest;
import java.util.Properties;

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

import com.model.User;
import com.service.LoginService;

@Component
@ManagedBean
@SessionScoped
public class ForgetPassword {
	@Autowired
	private LoginService log;
	private final String sender = "fredalpha20@gmail.com";
	private final String password = "rwabukwerere18404";
	private User user = new User();
	private String code = "";
	private String checkCode = "";
	private String password1 = new String();
	private String confPassword = new String();
	
	
	
	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String getPassword1() {
		return password1;
	}


	public void setPassword1(String password1) {
		this.password1 = password1;
	}


	public String getConfPassword() {
		return confPassword;
	}


	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getSender() {
		return sender;
	}


	public String getPassword() {
		return password;
	}
	public void test() {
		if(password1.equalsIgnoreCase(confPassword)) {
			String cod = checkCode.substring(4, checkCode.length());
			User user1 = log.getUsername(cod);
			user1.setPassword(password1);
			System.out.print("username"+cod+"password"+password1);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"success", "your password is well updated"));
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"error", "your password does not matches,please try again"));
			}
	}
	public String check() {
		String name = checkCode.substring(4, checkCode.length());
		return name;
	}
	public void renewPassword() {
		if(password1.equalsIgnoreCase(confPassword)) {
		User user1 = log.getUsername(check());
		user1.setPassword(md55(password1));
		log.updateUser(user1);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"success", "your password is well updated"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"error", "your password does not matches,please try again"));
		}
	}


	public void resetcode() {
		code = "";
		User us = log.getUsername(user.getUsername());
		if(us != null) {
				try {
					if(us.getApplicant()!= null) {
						 code = us.getApplicant().getAppId().substring(0,4)+us.getUsername();
					}else if(us.getEmployee() != null) {
						 code = us.getEmployee().getEmpId().substring(0, 4)+us.getUsername();
					}
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(
							"<h1 style=font-weight: bold; color: maroon;><center>National Commission For Childrens</center></h1></center><br />");
					stringBuilder.append("Hello ,"+us.getUsername() + "<br />");
					stringBuilder.append("THE CODE THAT ALLOW YOU TO CREATE NEW PASSWORD IN ONLINE CHILDREN'S ADOPTION IS :<br /><br />");
					stringBuilder.append("<b>CODE:</b>&nbsp;&nbsp;"+code+"&nbsp;&nbsp;&nbsp;&nbsp;" +"<a href=\"http://localhost:8080/OCAMIS/newPassword.jsf\" target=\"_blank\">click here</a>"+" to create new password "+ "<br />");

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
					if(us.getEmployee() !=null) {
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmployee().getEmail()));
					}else if(us.getApplicant() != null) {
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getApplicant().getEmail()));
					}

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
			
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"error" , "username does not exist"));
			}
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
