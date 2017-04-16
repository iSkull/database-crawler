package com.pokemon.go.database.interfaces;

import java.util.List;

public interface ITableNonQueryStatement<T> {
	T get(String id) throws Exception;

	T get(String col, String val) throws Exception;

	void create(T obj) throws Exception;

	void update(String id, T obj) throws Exception;

	void delete(String id) throws Exception;

	List<T> getAll() throws Exception;
}
