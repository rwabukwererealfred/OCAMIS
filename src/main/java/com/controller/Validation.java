package com.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.User;
import com.service.DashboardService;

public class Validation {
	@Autowired
	private DashboardService dash;
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@SuppressWarnings("unused")
	public boolean validateId(final String id, final String dob) {
		if (!id.isEmpty() || id.length() > 0 || id != null || id != "") {
			if (id.startsWith("1")) {
				if (id.trim().replace(" ", "").length() == 16) {
					String check = id.trim().replace(" ", "").substring(1, 5);
					String val[] = dob.split("/");
					if (check.equals(val[2])) {
						return true;
					} else {
						return false;
					}

				} else {
					return false;
				}
			} else {
				return false;
			}
		} else
			return false;
	}
	
	public boolean validatePhone(String phone) {
		if (phone.length() == 10) {
			if (phone.startsWith("078") || phone.startsWith("072") || phone.startsWith("073")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public static Integer age(final LocalDate birthday) {
		if (birthday == null) {
			return 0;
		}
		LocalDate today = LocalDate.now();
		Period period = Period.between(birthday, today);
		return period.getYears();
	}
	public boolean checkUser(String code) {
		User us = dash.findCode(code);
		if(us == null) {
			return true;
		}
		return false;
	}
	 public static String MD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] msg = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, msg);
	            String hash = number.toString(16);
	            while (hash.length() < 32) {
	                hash = "0" + hash;

	            }
	            return hash;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }

	    }
	 public String md5(String password) {
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
