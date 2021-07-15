package com.basicdao;


import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
	
	public abstract T save(final T t);
	
	public abstract T update(final T t);
	
	public abstract T delete(final T t);
	
	public abstract List<T> getAll();
	
	public abstract T findOne(final String id);
	
	public abstract Long count();
	
	public abstract void remove(T t);
	
	
}
