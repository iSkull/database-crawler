package com.pokemon.go.database.interfaces;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pokemon.go.database.model.Pokemon;

@Path("/pokemondb/pokemons")
@Produces(MediaType.APPLICATION_JSON)
public interface IPokemonsController {

	@GET
	@Path("/pokemon")
	Response get(@QueryParam("name") String name) throws WebApplicationException;

	@POST
	Response create(Pokemon pokemon) throws WebApplicationException;

	@PUT
	@Path("/pokemon")
	Response update(@QueryParam("id") String name, Pokemon pokemon) throws WebApplicationException;

	@DELETE
	@Path("/pokemon")
	Response delete(@QueryParam("id") String name) throws WebApplicationException;

	@GET
	Response getAll() throws WebApplicationException;
}
