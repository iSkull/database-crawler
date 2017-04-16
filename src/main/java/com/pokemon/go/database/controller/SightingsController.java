package com.pokemon.go.database.controller;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.pokemon.go.database.exception.InvalidInputException;
import com.pokemon.go.database.impl.MongoSightingImpl;
import com.pokemon.go.database.interfaces.IMongoConfiguration;
import com.pokemon.go.database.interfaces.ISightingsController;
import com.pokemon.go.database.interfaces.ITableNonQueryStatement;
import com.pokemon.go.database.model.Location;
import com.pokemon.go.database.model.PokemonCount;
import com.pokemon.go.database.model.Sighting;

@Path("/pokemondb/sightings")
@Produces(MediaType.APPLICATION_JSON)
public class SightingsController implements ISightingsController {
	private final ITableNonQueryStatement<Sighting> databaseTable;

	@Inject
	public SightingsController(@Named("mongoSightingImpl") ITableNonQueryStatement<Sighting> databaseTable,
			@Named("mongoTableSightings") IMongoConfiguration mongoConfig) {
		// TODO Auto-generated constructor stub
		this.databaseTable = databaseTable;
		((MongoSightingImpl<Sighting>) databaseTable).initMongo(mongoConfig);
	}

	@Override
	public Response get(String name) throws WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response create(Sighting sighting) throws WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response update(String name, Sighting sighting) throws WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response delete(String name) throws WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response getAll() throws WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAllGroupByType() throws WebApplicationException {

		List<PokemonCount> resp = null;
		try {
			resp = ((MongoSightingImpl<Sighting>) databaseTable).getCountGroupByType(false);

		} catch (Exception e) {
			throw e;
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response getAllGroupByType(List<Location> locations) throws WebApplicationException {
		List<PokemonCount> resp = null;
		try {
			resp = ((MongoSightingImpl<Sighting>) databaseTable).getCountGroupByType(locations, false);

		} catch (Exception e) {
			throw e;
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response getAllNearbyGroupByType(Location currentLocation) throws WebApplicationException {
		List<PokemonCount> resp = null;
		try {
			resp = ((MongoSightingImpl<Sighting>) databaseTable).getNearbyCountGroupByType(currentLocation, false);

		} catch (Exception e) {

		}

		return Response.ok(resp).build();
	}

	@Override
	public Response getAllLocationFilterBy(String id) throws WebApplicationException {
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid id.");

		List<Location> resp = null;

		try {
			resp = ((MongoSightingImpl<Sighting>) databaseTable).getLocationFilterBy(id);
		} catch (Exception e) {
			throw e;
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response getAllLocationFilterBy(String id, List<Location> locations) throws WebApplicationException {
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid id.");

		List<Location> resp = null;

		try {
			resp = ((MongoSightingImpl<Sighting>) databaseTable).getLocationFilterBy(id, locations);
		} catch (Exception e) {
			throw e;
		}

		return Response.ok(resp).build();
	}

	@Override
	public Response getAllNearbyLocationFilterBy(String id, Location currentLocation) throws WebApplicationException {
		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid id.");

		List<Location> resp = null;

		try {
			resp = ((MongoSightingImpl<Sighting>) databaseTable).getNearbyLocationFilterBy(id, currentLocation);
		} catch (Exception e) {
			throw e;
		}

		return Response.ok(resp).build();
	}
}
