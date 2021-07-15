package com.dao;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basicdao.GenericDaoImpl;
import com.model.District;

@Repository
public class DistrictDao  extends GenericDaoImpl<District>{

	@SuppressWarnings("unchecked")
	public List<District>getDistrict(String province){
		Query q = sessionfactory().createQuery("from District where province =:province");
		q.setString("province", province);
		return q.list();
	}

	@Override
	public void remove(District t) {
		sessionfactory().delete(t);
		
	}

}
