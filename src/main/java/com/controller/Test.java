package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Orphanage;
import com.service.CreationService;

@Component
@ManagedBean
@SessionScoped
public class Test {

	@Autowired
	private CreationService service;
	
	private Map<Orphanage, String>map = new HashMap<Orphanage, String>();
	private List<Orphanage>orphanageList = new ArrayList<Orphanage>();
	
	public void someMethod() {
		
	}
	
}
