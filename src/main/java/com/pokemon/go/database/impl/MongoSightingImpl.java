package com.pokemon.go.database.impl;

import java.util.ArrayList;
import java.util.List;

import org.mongojack.Aggregation;
import org.mongojack.AggregationResult;
import org.mongojack.DBCursor;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pokemon.go.database.exception.ConnectionNotFoundException;
import com.pokemon.go.database.exception.InvalidInputException;
import com.pokemon.go.database.model.FilterLocation;
import com.pokemon.go.database.model.Location;
import com.pokemon.go.database.model.PokemonCount;
import com.pokemon.go.database.model.Sighting;

public class MongoSightingImpl<T> extends MongoTableNonQueryImpl<T> {

	@Inject
	public MongoSightingImpl(TypeLiteral<T> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public List<PokemonCount> getCountGroupByType(boolean sortAsc) {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		int sortValue = sortAsc ? 1 : -1;

		DBObject group = getGroup();

		DBObject sort = getSort(sortValue);

		AggregationResult<PokemonCount> res = (AggregationResult<PokemonCount>) jacksonCollecton
				.aggregate(new Aggregation<>(PokemonCount.class, group, sort));

		return res.results();
	}

	public List<PokemonCount> getCountGroupByType(List<Location> locations, boolean sortAsc) {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (locations == null)
			throw new InvalidInputException("Invalid locations.");

		int sortValue = sortAsc ? 1 : -1;

		DBObject within = getWithin(locations);

		DBObject group = getGroup();

		DBObject sort = getSort(sortValue);

		AggregationResult<PokemonCount> res = (AggregationResult<PokemonCount>) jacksonCollecton
				.aggregate(new Aggregation<>(PokemonCount.class, within, group, sort));

		return res.results();
	}

	public List<PokemonCount> getNearbyCountGroupByType(Location currentLocation, boolean sortAsc) {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (currentLocation == null)
			throw new InvalidInputException("Invalid current location.");

		int sortValue = sortAsc ? 1 : -1;

		DBObject nearby = getNearby(currentLocation);

		DBObject group = getGroup();

		DBObject sort = getSort(sortValue);

		AggregationResult<PokemonCount> res = (AggregationResult<PokemonCount>) jacksonCollecton
				.aggregate(new Aggregation<>(PokemonCount.class, nearby, group, sort));

		return res.results();
	}

	public List<Location> getLocationFilterBy(String id) {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid id.");

		DBObject findQuery = new BasicDBObject("properties.pokemonNumber", Integer.parseInt(id));

		DBCursor<T> cursor = jacksonCollecton.find(findQuery);
		List<Location> items = new ArrayList<Location>();
		while (cursor.hasNext()) {
			items.add(((Sighting) cursor.next()).getLocation());
		}

		return items;
	}

	public List<Location> getLocationFilterBy(String id, List<Location> locations) {
		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid id.");

		if (locations == null)
			throw new InvalidInputException("Invalid locations.");

		DBObject withinAndMatch = getWithinAndMatch(locations, id);

		DBObject projection = getLocationProjection();

		AggregationResult<FilterLocation> res = (AggregationResult<FilterLocation>) jacksonCollecton
				.aggregate(new Aggregation<>(FilterLocation.class, withinAndMatch, projection));

		List<Location> items = new ArrayList<Location>();
		for (FilterLocation item : res.results()) {
			items.add(item.getLocation());
		}

		return items;
	}

	public List<Location> getNearbyLocationFilterBy(String id, Location currentLocation) {

		if (!isMongoConnected)
			throw new ConnectionNotFoundException(mongoConfig.getDatabase() + " not connected.");

		if (Strings.isNullOrEmpty(id))
			throw new InvalidInputException("Invalid id.");

		if (currentLocation == null)
			throw new InvalidInputException("Invalid current location.");

		DBObject nearby = getNearby(currentLocation);

		DBObject match = getMatch(id);

		DBObject projection = getLocationProjection();

		AggregationResult<FilterLocation> res = (AggregationResult<FilterLocation>) jacksonCollecton
				.aggregate(new Aggregation<>(FilterLocation.class, nearby, match, projection));

		List<Location> items = new ArrayList<Location>();
		for (FilterLocation item : res.results()) {
			items.add(item.getLocation());
		}

		return items;
	}

	private DBObject getGroup() {
		return new BasicDBObject("$group",
				new BasicDBObject("_id", "$properties.pokemonNumber").append("count", new BasicDBObject("$sum", 1)));
	}

	private DBObject getSort(int sortValue) {
		return new BasicDBObject("$sort", new BasicDBObject("count", sortValue));
	}

	private DBObject getNearby(Location location) {

		List<Double> center = getCoordinate(location.getCoordinates());

		return new BasicDBObject("$geoNear",
				new BasicDBObject("near", new BasicDBObject("type", "Point").append("coordinates", center))
						.append("spherical", true).append("distanceField", "distance")
						.append("includeLocs", "location")
						.append("maxDistance", 5000));
	}

	private DBObject getWithin(List<Location> locations) {

		BasicDBList coordinates = new BasicDBList();
		coordinates.add(getCoordinate(locations.get(0).getCoordinates()));
		coordinates.add(getCoordinate(locations.get(1).getCoordinates()));

		return new BasicDBObject("$match",
				new BasicDBObject("location", new BasicDBObject("$geoWithin", new BasicDBObject("$box", coordinates))));
	}

	private DBObject getWithinAndMatch(List<Location> locations, String id) {

		BasicDBList coordinates = new BasicDBList();
		coordinates.add(getCoordinate(locations.get(0).getCoordinates()));
		coordinates.add(getCoordinate(locations.get(1).getCoordinates()));

		BasicDBList expressions = new BasicDBList();
		expressions.add(new BasicDBObject("properties.pokemonNumber", Integer.parseInt(id)));
		expressions.add(
				new BasicDBObject("location", new BasicDBObject("$geoWithin", new BasicDBObject("$box", coordinates))));

		return new BasicDBObject("$match", new BasicDBObject("$and", expressions));
	}

	private DBObject getLocationProjection() {
		return new BasicDBObject("$project", new BasicDBObject("location", 1));
	}

	private DBObject getMatch(String id) {
		return new BasicDBObject("$match", new BasicDBObject("properties.pokemonNumber", Integer.parseInt(id)));
	}

	private List<Double> getCoordinate(List<Double> coordinate) {
		List<Double> item = new ArrayList<Double>();
		item.add(coordinate.get(0));
		item.add(coordinate.get(1));
		return item;
	}
}
