package com.sample.app.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.internal.SessionImpl;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> type;

	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public long countAll(final Map<String, Object> params) {

		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");

		queryString.append(type.getSimpleName()).append(" o ");

		final Query query = this.em.createQuery(queryString.toString());

		return (Long) query.getSingleResult();

	}

	@Override
	public T create(final T t) {

		this.em.persist(t);
		return t;
	}

	@Override
	public void delete(final Object id) {
		this.em.remove(this.em.getReference(type, id));
	}

	@Override
	public T find(final Object id) {
		return (T) this.em.find(type, id);
	}

	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}

	@Override
	public Object executeNativeQuerySingleResult(String query,
			Map<String, Object> filterparam) {

		Query qry = em.createNativeQuery(query);

		if (filterparam != null && filterparam.size() > 0) {
			for (Map.Entry<String, Object> param : filterparam.entrySet()) {
				qry.setParameter(param.getKey(), param.getValue());
			}
		}

		return qry.getSingleResult();
	}

	@Override
	public ResultSetMetaData nativeFilterMetaData(String query)
			throws SQLException {
		// TODO Auto-generated method stub

		java.sql.Connection con = em.unwrap(SessionImpl.class).connection();
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(query + "  limit 0");
		return rs.getMetaData();
	}

	@Override
	public List<T> findall() {
		// TODO Auto-generated method stub
		String query = "select t from " + type.getSimpleName() + " t";

		Query qry = em.createQuery(query);

		return qry.getResultList();
	}

}
