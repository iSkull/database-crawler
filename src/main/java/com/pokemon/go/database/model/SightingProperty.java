package com.pokemon.go.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SightingProperty {
	private String id;
	
	@JsonProperty("WillDisappear")
	private String WillDisappear;
	private String type;
	
	@JsonProperty("TimeTillHiddenMs")
	private String TimeTillHiddenMs;
	private String pokemonNumber;
	private String title;
	
	@JsonProperty("marker-color")
	private String markerColor;
	
	@JsonProperty("marker-symbol")
	private String markerSymbol;

}