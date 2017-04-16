package com.pokemon.go.database.model;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonCount {
	@Id
	@JsonProperty("_id")
	private String id;
	private String count;
}
