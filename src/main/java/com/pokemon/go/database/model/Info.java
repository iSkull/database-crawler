package com.pokemon.go.database.model;

import java.util.List;

public class Info {

	private String devNumberValue;
	private List<String> pokemonType;
	private String pokemonSpeciesType;
	private String pokemonRarityType;
	private String evolveCandyValue;
	private String pokemonEggType;

	public String getDevNumberValue() {
		return devNumberValue;
	}

	public void setDevNumberValue(String devNumberValue) {
		this.devNumberValue = devNumberValue;
	}

	public List<String> getPokemonType() {
		return pokemonType;
	}

	public void setPokemonType(List<String> pokemonType) {
		this.pokemonType = pokemonType;
	}

	public String getPokemonSpeciesType() {
		return pokemonSpeciesType;
	}

	public void setPokemonSpeciesType(String pokemonSpeciesType) {
		this.pokemonSpeciesType = pokemonSpeciesType;
	}

	public String getPokemonRarityType() {
		return pokemonRarityType;
	}

	public void setPokemonRarityType(String pokemonRarityType) {
		this.pokemonRarityType = pokemonRarityType;
	}

	public String getEvolveCandyValue() {
		return evolveCandyValue;
	}

	public void setEvolveCandyValue(String evolveCandyValue) {
		this.evolveCandyValue = evolveCandyValue;
	}

	public String getPokemonEggType() {
		return pokemonEggType;
	}

	public void setPokemonEggType(String pokemonEggType) {
		this.pokemonEggType = pokemonEggType;
	}

	@Override
	public String toString() {
		return "Info [devNumberValue=" + devNumberValue + ", pokemonType=" + pokemonType + ", pokemonSpeciesType="
				+ pokemonSpeciesType + ", pokemonRarityType=" + pokemonRarityType + ", evolveCandyValue="
				+ evolveCandyValue + ", pokemonEggType=" + pokemonEggType + "]";
	}
}
