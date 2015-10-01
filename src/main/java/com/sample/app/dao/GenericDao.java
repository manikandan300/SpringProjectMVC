package com.sample.app.dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

	long countAll(Map<String, Object> params);

	T create(T t);

	void delete(Object id);

	T find(Object id);

	T update(T t);

	ResultSetMetaData nativeFilterMetaData(String query) throws SQLException;

	Object executeNativeQuerySingleResult(String query,
			Map<String, Object> filterparam);

	List<T> findall();

}
