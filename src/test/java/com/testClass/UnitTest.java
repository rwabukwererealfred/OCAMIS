package com.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.ApplicantDao;
import com.model.Applicant;
import com.service.DashboardService;

public class UnitTest {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static DashboardService dash;
	
	private Applicant applicant;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		dash = (DashboardService)context.getBean("applicantDao");
	}
	
	@Test
	public void getById() {
		applicant = dash.findID("1");
		assertEquals("alfred", applicant.getLastName());
	}
}
