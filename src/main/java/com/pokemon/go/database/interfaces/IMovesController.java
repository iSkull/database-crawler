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

import com.pokemon.go.database.model.Move;

@Path("/pokemondb/moves")
@Produces(MediaType.APPLICATION_JSON)
public interface IMovesController {

	@GET
	@Path("/move")
	Response get(@QueryParam("name") String name) throws WebApplicationException;

	@POST
	Response create(Move move) throws WebApplicationException;

	@PUT
	@Path("/move")
	Response update(@QueryParam("id") String name, Move move) throws WebApplicationException;

	@DELETE
	@Path("/move")
	Response delete(@QueryParam("id") String name) throws WebApplicationException;

	@GET
	Response getAll() throws WebApplicationException;
}
