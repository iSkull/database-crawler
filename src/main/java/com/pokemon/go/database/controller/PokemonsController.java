package com.pokemon.go.database.controller;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.pokemon.go.database.exception.InvalidInputException;
import com.pokemon.go.database.impl.MongoTableNonQueryImpl;
import com.pokemon.go.database.interfaces.IMongoConfiguration;
import com.pokemon.go.database.interfaces.IPokemonsController;
import com.pokemon.go.database.interfaces.ITableNonQueryStatement;
import com.pokemon.go.database.model.Pokemon;
import com.pokemon.go.database.model.PokemonDeleteResponse;
import com.pokemon.go.database.model.PokemonPostResponse;
import com.pokemon.go.database.model.PokemonPutResponse;

public class PokemonsController implements IPokemonsController {

	private final ITableNonQueryStatement<Pokemon> databaseTable;

	@Inject
	public PokemonsController(@Named("mongoPokemonImpl") ITableNonQueryStatement<Pokemon> databaseTable,
			@Named("mongoTablePokemons") IMongoConfiguration mongoConfig) {
		// TODO Auto-generated constructor stub
		this.databaseTable = databaseTable;
		((MongoTableNonQueryImpl<Pokemon>) databaseTable).initMongo(mongoConfig);
	}

	@Override
	public Response get(String name) throws WebApplicationException {
		if (Strings.isNullOrEmpty(name))
			throw new InvalidInputException("Invalid name.");

		Pokemon resp;
		try {
			resp = databaseTable.get("pokemonName", name);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

		// TODO Auto-generated method stub
		return Response.ok(resp).build();
	}

	@Override
	public Response create(Pokemon pokemon) throws WebApplicationException {
		if (pokemon == null)
			throw new InvalidInputException("Invalid entry. No object found.");
		// TODO Auto-generated method stub
		PokemonPostResponse resp = new PokemonPostResponse();
		resp.setSuccess(true);

		try {
			databaseTable.create(pokemon);
		} catch (Exception e) {
			resp.setSuccess(false);
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response update(String id, Pokemon pokemon) throws WebApplicationException {
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid identity.");

		if (pokemon == null)
			throw new InvalidInputException("Invalid entry. No object found.");

		PokemonPutResponse resp = new PokemonPutResponse();
		resp.setSuccess(true);

		try {
			databaseTable.update(id, pokemon);
		} catch (Exception e) {
			resp.setSuccess(false);
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response delete(String id) throws WebApplicationException {
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid identity.");
		// TODO Auto-generated method stub

		PokemonDeleteResponse resp = new PokemonDeleteResponse();
		resp.setSuccess(true);

		try {
			databaseTable.delete(id);
		} catch (Exception e) {
			resp.setSuccess(false);
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response getAll() throws WebApplicationException {

		// TODO Auto-generated method stub
		List<Pokemon> resp = null;
		try {
			resp = databaseTable.getAll();
		} catch (Exception e) {

		}

		return Response.ok(resp).build();
	}

}