package com.pokemon.go.database.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

	@JsonProperty("coordinates")
	private List<Double> coordinates;

	@JsonProperty("type")
	private String type;
}