package com.basicdao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.util.DataManipulationException;
import com.util.QueryParameters;



public abstract class GenericDao<T extends Serializable> implements IGenericDao<T> {

	private Class<T> type;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	protected abstract Session sessionfactory();

	@Override
	public T save(T t) {
		sessionfactory().save(t);
		sessionfactory().flush();
		return t;
	}

	@Override
	public T delete(T t) {
		sessionfactory().delete(t);
		sessionfactory().flush();
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return sessionfactory().createCriteria(this.type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(String id) {
		return (T) sessionfactory().get(this.type, id);
	}

	@Override
	public Long count() {

		return null;
	}

	@Override
	public T update(T t) {
		sessionfactory().update(t);
		sessionfactory().flush();
		return t;
	}

	@SuppressWarnings("unchecked")
	public final T executeNamedQuerySingle(final String name, final QueryParameters props) {
		T result = (T) getMappedQueryWithArgs(name, props).uniqueResult();

		if (result == null) {
			throw new DataManipulationException("Object not found");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public final T executeSQLQuerySingle(final String queryString, final Map<String, Object> props) {

		Query query = sessionfactory().createQuery(queryString);

		for (String key : props.keySet()) {
			query.setParameter(key, props.get(key));
		}

		T result = (T) query.uniqueResult();

		if (result == null) {
			throw new DataManipulationException("Object not found");
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public final T executeSQLQuerySingle(final String queryString, final Class<?> type,
			final Map<String, Object> props) {
		Query query = sessionfactory().createSQLQuery(queryString).addEntity(type);
		for (String key : props.keySet()) {
			query.setParameter(key, props.get(key));
		}
		T result = (T) query.uniqueResult();

		if (result == null) {
			throw new DataManipulationException("Object not found");
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> executeNamedQueryMultiple(final String name, final QueryParameters props) {
		return getMappedQueryWithArgs(name, props).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeNamedQueryMultiple(final String name) {
		return sessionfactory().getNamedQuery(name).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeSQLQueryForMultipleResultSet(final String queryString, final Map<String, Object> props) {
		SQLQuery query = sessionfactory().createSQLQuery(queryString);
		query.addEntity(type);

		for (String key : props.keySet()) {
			query.setParameter(key, props.get(key));
		}
		return query.list();
	}

	private Query getMappedQueryWithArgs(final String name, final QueryParameters props) {
		// Assign all the parameters
		Query query = sessionfactory().getNamedQuery(name);
		Map<String, Object> args = props.getArgs();
		for (String key : args.keySet()) {
			query.setParameter(key, args.get(key));
		}
		// Now assign the lists if there are any
		Map<String, Collection<?>> lists = props.getLists();
		for (String key : lists.keySet()) {
			query.setParameterList(key, (Collection<?>) lists.get(key));
		}
		return query;
	}
}
