package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.Province;

@Repository
public class ProvinceDao extends GenericDaoImpl<Province>{
	
	@SuppressWarnings("unchecked")
	public List<Province>provinceList(){
		Query q = sessionfactory().createQuery("from Province");
		return q.list();
	}

	@Override
	public void remove(Province t) {
		sessionfactory().delete(t);
		
	}

}
