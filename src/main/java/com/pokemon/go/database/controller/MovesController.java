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
import com.pokemon.go.database.interfaces.IMovesController;
import com.pokemon.go.database.interfaces.ITableNonQueryStatement;
import com.pokemon.go.database.model.Move;
import com.pokemon.go.database.model.MoveDeleteResponse;
import com.pokemon.go.database.model.MovePostResponse;
import com.pokemon.go.database.model.MovePutResponse;

public class MovesController implements IMovesController {

	private final ITableNonQueryStatement<Move> databaseTable;

	@Inject
	public MovesController(@Named("mongoMoveImpl") ITableNonQueryStatement<Move> databaseTable,
			@Named("mongoTableMoves") IMongoConfiguration mongoConfig) {
		// TODO Auto-generated constructor stub
		this.databaseTable = databaseTable;
		((MongoTableNonQueryImpl<Move>) databaseTable).initMongo(mongoConfig);
	}

	@Override
	public Response get(String name) throws WebApplicationException {
		if (Strings.isNullOrEmpty(name))
			throw new InvalidInputException("Invalid name.");

		Move resp;
		try {
			resp = databaseTable.get("moveName", name);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

		// TODO Auto-generated method stub
		return Response.ok(resp).build();
	}

	@Override
	public Response create(Move move) throws WebApplicationException {
		if (move == null)
			throw new InvalidInputException("Invalid entry. No object found.");
		// TODO Auto-generated method stub
		MovePostResponse resp = new MovePostResponse();
		resp.setSuccess(true);

		try {
			databaseTable.create(move);
		} catch (Exception e) {
			resp.setSuccess(false);
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response update(String id, Move move) throws WebApplicationException {
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid identity.");

		if (move == null)
			throw new InvalidInputException("Invalid entry. No object found.");

		MovePutResponse resp = new MovePutResponse();
		resp.setSuccess(true);

		try {
			databaseTable.update(id, move);
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

		MoveDeleteResponse resp = new MoveDeleteResponse();
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
		List<Move> resp = null;
		try {
			resp = databaseTable.getAll();
		} catch (Exception e) {

		}

		return Response.ok(resp).build();
	}

}