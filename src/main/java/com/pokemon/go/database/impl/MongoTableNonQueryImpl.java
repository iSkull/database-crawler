package com.pokemon.go.database.impl;

import java.util.ArrayList;
import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
import org.mongojack.JacksonDBCollection;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.pokemon.go.database.exception.ConnectionNotFoundException;
import com.pokemon.go.database.exception.InvalidInputException;
import com.pokemon.go.database.interfaces.IMongoConfiguration;
import com.pokemon.go.database.interfaces.ITableNonQueryStatement;

public class MongoTableNonQueryImpl<T> implements ITableNonQueryStatement<T> {

	protected final Class<T> clazz;

	protected IMongoConfiguration mongoConfig;

	protected boolean isMongoConnected;

	protected DBCollection collection;
	protected JacksonDBCollection<T, String> jacksonCollecton;

	@Inject
	@SuppressWarnings("unchecked")
	public MongoTableNonQueryImpl(TypeLiteral<T> type) {
		this.clazz = (Class<T>) type.getRawType();
	}

	@SuppressWarnings({ "deprecation", "resource" })
	public boolean initMongo(IMongoConfiguration mongoConfig) {
		if (mongoConfig == null)
			throw new InvalidInputException("Mongo config not found.");

		this.mongoConfig = mongoConfig;

		// To connect to mongodb server
		try {
			MongoClient mongoClient = new MongoClient(mongoConfig.getUrl(), 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB(mongoConfig.getDatabase());

			collection = db.getCollection(mongoConfig.getTable());

			jacksonCollecton = JacksonDBCollection.wrap(collection, clazz, String.class);

			isMongoConnected = true;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public T get(String id) throws Exception {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid identity.");

		// TODO Auto-generated method stub
		T obj = jacksonCollecton.findOneById(id);

		return obj;
	}

	@Override
	public T get(String col, String val) throws Exception {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (Strings.isNullOrEmpty(col))
			throw new InvalidInputException("Invalid column.");

		if (Strings.isNullOrEmpty(col))
			throw new InvalidInputException("Invalid value.");

		// TODO Auto-generated method stub
		Query query = DBQuery.is(col, val);
		T obj = jacksonCollecton.findOne(query);

		return obj;
	}

	@Override
	public void create(T obj) throws Exception {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		// TODO Auto-generated method stub
		if (obj == null)
			throw new InvalidInputException("Object is empty.");

		jacksonCollecton.insert(obj);
	}

	@Override
	public void update(String id, T obj) throws Exception {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		// TODO Auto-generated method stub
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid identity.");

		if (obj == null)
			throw new InvalidInputException("Object is empty.");

		jacksonCollecton.updateById(id, obj);
	}

	@Override
	public void delete(String id) throws Exception {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid identity.");
		// TODO Auto-generated method stub
		jacksonCollecton.removeById(id);
	}

	@Override
	public List<T> getAll() throws Exception {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		// TODO Auto-generated method stub

		DBCursor<T> cursor = jacksonCollecton.find();
		List<T> items = new ArrayList<T>();
		while (cursor.hasNext()) {
			items.add(cursor.next());
		}

		return items;
	}
}
