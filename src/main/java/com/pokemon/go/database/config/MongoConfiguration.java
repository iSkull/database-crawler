package com.pokemon.go.database.config;

import com.pokemon.go.database.interfaces.IMongoConfiguration;

import lombok.Getter;

@Getter
public class MongoConfiguration implements IMongoConfiguration {
	private String url;
	private String database;
	private String table;

	public MongoConfiguration(String url, String database, String table) {
		this.database = database;
		this.url = url;
		this.table = table;
	}
}
