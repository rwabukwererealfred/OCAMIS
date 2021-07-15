package com.test;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
	
	 public static String md5(String password) {
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
	
	public static void main(String args[]) {
		
		System.out.println(md5("edy"));
		System.out.println(md5("1234"));
		System.out.println(md5("123"));
	}
	
}