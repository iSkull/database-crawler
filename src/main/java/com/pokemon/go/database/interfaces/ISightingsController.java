package com.pokemon.go.database.interfaces;

import java.util.List;

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

import com.pokemon.go.database.model.Location;
import com.pokemon.go.database.model.Sighting;

@Path("/pokemondb/sightings")
@Produces(MediaType.APPLICATION_JSON)
public interface ISightingsController {

	@GET
	@Path("/sighting")
	Response get(@QueryParam("name") String name) throws WebApplicationException;

	@POST
	Response create(Sighting sighting) throws WebApplicationException;

	@PUT
	@Path("/sighting")
	Response update(@QueryParam("id") String name, Sighting sighting) throws WebApplicationException;

	@DELETE
	@Path("/sighting")
	Response delete(@QueryParam("id") String name) throws WebApplicationException;

	@GET
	Response getAll() throws WebApplicationException;
	
	@GET
	@Path("/type/group")
	Response getAllGroupByType() throws WebApplicationException;

	@POST
	@Path("/type/group")
	Response getAllGroupByType(List<Location> locations) throws WebApplicationException;

	@POST
	@Path("/type/group/nearby")
	Response getAllNearbyGroupByType(Location currentLocation) throws WebApplicationException;

	@GET
	@Path("/location/filter")
	Response getAllLocationFilterBy(@QueryParam("id") String id) throws WebApplicationException;

	@POST
	@Path("/location/filter")
	Response getAllLocationFilterBy(@QueryParam("id") String id, List<Location> locations)
			throws WebApplicationException;

	@POST
	@Path("/location/filter/nearby")
	Response getAllNearbyLocationFilterBy(@QueryParam("id") String id, Location currentLocation)
			throws WebApplicationException;
}
