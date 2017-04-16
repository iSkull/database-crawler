package com.pokemon.go.database.model;

import org.mongojack.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sighting {

	@Id
	private String id;
	private String uid;
	private String updatedAt;
	private boolean stale;
	private String updatedBy;
	private SightingProperty properties;
	private Location location;
	private String objectType;
	private String __v;
	private String createdAt;
	
}
